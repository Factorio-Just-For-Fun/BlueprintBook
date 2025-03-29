package com.factoriojustforfun.utils;

import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

@SuppressWarnings("LoggingSimilarMessage")
public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger("JSON Utils");

    public static final long VERSION = 562949956173828L;
    public static final File BLUEPRINT_DIRECTOY = new File("blueprints");

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static final JsonSchema SCHEMA;

    static {
        LOGGER.debug("Creating JSON Manager");
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
        SchemaValidatorsConfig config = new SchemaValidatorsConfig();
        config.setFormatAssertionsEnabled(true);

        SCHEMA = factory.getSchema(SchemaLocation.of("classpath:schema.json"), config);

        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        MAPPER.configure(JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION, true);
        MAPPER.disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);

        LOGGER.debug("JSON Manager Created");
    }

    public static String encode(String string) throws IOException {
        LOGGER.debug("Converting string to compressed string");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try (DeflaterOutputStream deflateStream = new DeflaterOutputStream(stream, new Deflater(9))) {
            deflateStream.write(string.getBytes(StandardCharsets.UTF_8));
        }
        return '0' + Base64.getEncoder().encodeToString(stream.toByteArray());
    }

    public static String encode(BlueprintBookEntry entry) {
        LOGGER.debug("Converting blueprint book to compressed string");
        try {
            return encode(MAPPER.writeValueAsString(entry));
        } catch (IOException e) {
            LOGGER.error("Failed to encode string");
            throw new RuntimeException(e);
        }
    }

    public static JsonNode decodeToJson(String string) {
        LOGGER.debug("Decoding string to a JsonNode");
        if (string.charAt(0) != '0')
            throw new InputMismatchException("Blueprint does not start with 0.");
        byte[] bytes = Base64.getDecoder().decode(string.substring(1).getBytes());

        try {
            return MAPPER.readTree(new InflaterInputStream(new ByteArrayInputStream(bytes)));
        } catch (IOException e) {
            LOGGER.error("Failed to decode string");
            throw new RuntimeException(e);
        }
    }

    public static JsonNode decodeToJson(File file) {
        LOGGER.debug("Reading file {} to a JsonNode", file);
        try {
            return decodeToJson(Files.readAllLines(file.toPath()).get(0));
        } catch (Exception e) {
            LOGGER.error("Failed to read file {}", file);
            throw new RuntimeException("Error in file" + file.getAbsolutePath(), e);
        }
    }

    public static BlueprintBookEntry fromFile(String... path) {
        LOGGER.debug("Reading file {} to a BlueprintBookEntry", Arrays.toString(path));
        return fromFile(Paths.get(BLUEPRINT_DIRECTOY.getAbsolutePath(), path).toFile());
    }

    public static BlueprintBookEntry fromFile(File file) {
        LOGGER.debug("Reading file {} to a BlueprintBookEntry", file);
        try {
            return MAPPER.treeToValue(decodeToJson(file), BlueprintBookEntry.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to read file {}", file);
            throw new RuntimeException("Error in " + String.join(" - ", file.getAbsolutePath()), e);
        }
    }

    public static BlueprintBookEntry fromText(String text) {
        try {
            return MAPPER.treeToValue(decodeToJson(text), BlueprintBookEntry.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
