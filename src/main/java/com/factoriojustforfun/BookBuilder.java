package com.factoriojustforfun;

import com.factoriojustforfun.books.StarterBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.subbooks.MainBaseBook;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.util.HashSet;

public class BookBuilder {
    public static void main(String[] args) {
        BlueprintBookItem item = new BlueprintBookItem(StarterBook.generateFJFFBook());
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
