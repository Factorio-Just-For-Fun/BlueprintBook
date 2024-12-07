package com.factoriojustforfun;

import com.factoriojustforfun.books.StarterBook;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.utils.JsonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BookBuilderCI {
    public static void main(String[] args) {
        BlueprintBookItem item = StarterBook.generateFJFFBook();

        File outputFile = new File("output.txt");

        String text = JsonUtils.encode(item);
        try (PrintStream stream = new PrintStream(new FileOutputStream(outputFile))) {
            stream.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
