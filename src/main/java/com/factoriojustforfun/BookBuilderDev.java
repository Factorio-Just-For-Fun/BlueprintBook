package com.factoriojustforfun;

import com.factoriojustforfun.books.StarterBook;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class BookBuilderDev {
    public static void main(String[] args) {
        BlueprintBookItem item = StarterBook.generatePersonalBook();
        BlueprintUtils.patch(item);
        String text = JsonUtils.encode(item);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }
}
