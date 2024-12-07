package com.factoriojustforfun;

import com.factoriojustforfun.books.StarterBook;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BookBuilderDev {
    public static void main(String[] args) {
        BlueprintBookItem item = StarterBook.generatePersonalBook();
        BlueprintUtils.patch(item);
        String text = JsonUtils.encode(item);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);

        File outputFile = new File("output.txt");
        try (PrintStream stream = new PrintStream(new FileOutputStream(outputFile))) {
            stream.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
