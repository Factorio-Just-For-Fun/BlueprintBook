package com.factoriojustforfun;

import com.factoriojustforfun.books.StarterBook;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BookBuilderDev {
    private static final Logger LOGGER = LoggerFactory.getLogger("CI Builder");

    public static void main(String[] args) {
        LOGGER.info("Creating FJFF book...");
        BlueprintBookItem item = StarterBook.generateFJFFBook();

        LOGGER.info("Book created. Saving to JSON...");
        String text = JsonUtils.encode(item);

        LOGGER.info("Copying to clipboard...");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);

        LOGGER.info("Writing to File...");
        File outputFile = new File("output.txt");
        try (PrintStream stream = new PrintStream(new FileOutputStream(outputFile))) {
            stream.println(text);
        } catch (IOException e) {
            LOGGER.error("Write failed!");
            throw new RuntimeException(e);
        }
    }
}
