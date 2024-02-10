package com.factoriojustforfun.devutils;

import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.objects.bookentries.BlueprintItem;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.*;
import com.networknt.schema.output.OutputUnit;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;

public class ValidateAllFiles {
    private static final File ERROR_DIR = new File("output");

    public static void main(String[] args) {
        System.out.println("Scan Started.");

        if (ERROR_DIR.exists()) {
            Arrays.stream(Objects.requireNonNull(ERROR_DIR.listFiles())).forEach(File::delete);
            ERROR_DIR.delete();
        }

        ERROR_DIR.mkdir();
        validateDirectory(new File("blueprints"));
        //validateFile(new File("blueprints/do-not-take-these-ash.txt"), schema);

        System.out.println("Scan Finished.");
    }

    public static void validateDirectory(File directory) {
        Arrays.stream(Objects.requireNonNull(directory.listFiles())).parallel().forEach(file -> {
            if (file.isDirectory()) validateDirectory(file);
            else validateFile(file);
        });
    }

    public static void validateFile(File file) {
        try {
            JsonNode node = JsonUtils.decodeToJson(file);

            OutputUnit unit = JsonUtils.SCHEMA.validate(node, OutputFormat.HIERARCHICAL);
            if (!unit.isValid()) {
                System.out.println("Invalid file: " + file.getAbsolutePath());

                Files.write(new File("output", file.getPath().replaceAll("\\" + File.separatorChar, "-")).toPath(), unit.toString().getBytes());
                return;
            }

            BlueprintBookEntry entry = JsonUtils.MAPPER.treeToValue(node, BlueprintBookEntry.class);
            if (entry instanceof BlueprintBookItem) {
                System.out.println("Blueprint Book at " + file.getName() + " has name " + ((BlueprintBookItem) entry).getBlueprintBook().getLabel());
            } else if (entry instanceof BlueprintItem) {
                System.out.println("Blueprint at " + file.getName() + " has name " + ((BlueprintItem) entry).getBlueprint().getLabel());
            } else {
                System.out.println("Unknown object at " + file.getName() + " is " + entry);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in " + file.getAbsolutePath(), e);
        }
    }
}