package com.factoriojustforfun;

import com.factoriojustforfun.books.StarterBook;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BookBuilderCI {
    private static final Logger LOGGER = LoggerFactory.getLogger("CI Builder");

    public static void main(String[] args) {
        LOGGER.info("Creating FJFF book...");
        BlueprintBookItem item = StarterBook.generateFJFFBook();

        LOGGER.info("Book created. Saving to JSON...");
        File outputFile = new File("output.txt");

        String text = JsonUtils.encode(item);

        LOGGER.info("Writing to File...");
        try (PrintStream stream = new PrintStream(new FileOutputStream(outputFile))) {
            stream.println(text);
        } catch (IOException e) {
            LOGGER.error("Write failed!");
            throw new RuntimeException(e);
        }
    }
}
