package com.factoriojustforfun;

import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        BlueprintBookEntry item = JsonUtils.fromText((String) clipboard.getData(DataFlavor.stringFlavor));

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tag = timestamp +
                " FJFF Blueprints compiled by Ashy314.\n" +
                "https://discord.gg/ehHEDDnPWA";
        BlueprintUtils.patch(item, tag);
        ((BlueprintBookItem) item).getBlueprintBook().setLabel("[FJFF] " + timestamp);

        String text = JsonUtils.encode(item);

        // Copy
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);

        // Write
        File outputFile = new File("output.txt");
        try (PrintStream stream = new PrintStream(new FileOutputStream(outputFile))) {
            stream.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
