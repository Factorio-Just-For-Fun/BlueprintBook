package com.factoriojustforfun.utils;

import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class JsonUtils {
    public static final long VERSION = 281479278297089L;
    public static final File BLUEPRINT_DIRECTOY = new File("blueprints");

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static final JsonSchema SCHEMA;

    static {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
        SchemaValidatorsConfig config = new SchemaValidatorsConfig();
        config.setFormatAssertionsEnabled(true);

        SCHEMA = factory.getSchema(SchemaLocation.of("classpath:schema.json"), config);

        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        MAPPER.disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);


    }

    public static String encode(String string) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try (DeflaterOutputStream deflateStream = new DeflaterOutputStream(stream, new Deflater(9))) {
            deflateStream.write(string.getBytes(StandardCharsets.UTF_8));
        }
        return '0' + Base64.getEncoder().encodeToString(stream.toByteArray());
    }

    public static String encode(BlueprintBookEntry entry) {
        try {
            return encode(MAPPER.writeValueAsString(entry));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode decodeToJson(String string) {
        if (string.charAt(0) != '0')
            throw new InputMismatchException("Blueprint does not start with 0.");
        byte[] bytes = Base64.getDecoder().decode(string.substring(1).getBytes());

        try {
            return MAPPER.readTree(new InflaterInputStream(new ByteArrayInputStream(bytes)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode decodeToJson(File file) {
        try {
            return decodeToJson(Files.readAllLines(file.toPath()).get(0));
        } catch (Exception e) {
            throw new RuntimeException("Error in file" + file.getAbsolutePath(), e);
        }
    }

    public static BlueprintBookEntry fromFile(String... path) {
        return fromFile(Paths.get(BLUEPRINT_DIRECTOY.getAbsolutePath(), path).toFile());
    }

    public static BlueprintBookEntry fromFile(File file) {
        try {
            return MAPPER.treeToValue(decodeToJson(file), BlueprintBookEntry.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error in " + String.join(" - ", file.getAbsolutePath()), e);
        }
    }

    public static BlueprintBookEntry fromText(String text) {
        try {
            return MAPPER.readValue(text, BlueprintBookEntry.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
