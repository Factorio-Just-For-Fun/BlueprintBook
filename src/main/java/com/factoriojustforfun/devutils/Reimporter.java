package com.factoriojustforfun.devutils;

import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.utils.JsonUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Reimporter {
    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        BlueprintBookEntry item = JsonUtils.fromText((String) clipboard.getData(DataFlavor.stringFlavor));
        String text = JsonUtils.encode(item);

        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, selection);
    }
}
