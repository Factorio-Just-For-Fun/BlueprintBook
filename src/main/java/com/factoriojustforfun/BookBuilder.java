package com.factoriojustforfun;

import com.factoriojustforfun.books.AllBook;
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

public class BookBuilder {
    public static void main(String[] args) {
        BlueprintBookItem item = new BlueprintBookItem(AllBook.generateBook());
        BlueprintUtils.patch(item);
        File outputFile = new File("output.txt");

        String text = JsonUtils.encode(item);

        try (PrintStream stream = new PrintStream(new FileOutputStream(outputFile))) {
            stream.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }
}
