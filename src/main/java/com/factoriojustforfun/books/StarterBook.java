package com.factoriojustforfun.books;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.subbooks.*;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.time.LocalDate;
import java.util.*;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class StarterBook {
    public static void generateBook(BlueprintBook book, Set<BookFlags> flags) {
        List<BlueprintBookEntry> entries = book.getBlueprints() == null ? new ArrayList<>() : book.getBlueprints();

        entries.add(JsonUtils.fromFile("balancers-raynquist.txt"));
        entries.add(new BlueprintBookItem(RailsBook.generateBook(flags)));
        entries.add(JsonUtils.fromFile("rail-misc", "construction-compendium.txt"));

        if (flags.contains(BookFlags.TRAINS_3_8)) {
            entries.add(new BlueprintBookItem(Outposts38Book.generateBook(flags)));

            if (flags.contains(BookFlags.INCLUDE_ALTERNATES)) {
                entries.add(new BlueprintBookItem(Outposts38UnbeaconedBook.generateBook(flags)));
            }
        }

        entries.add(new BlueprintBookItem(MainBaseBook.generateBook(flags)));

        if (flags.contains(BookFlags.NORMAL_RECIPES)) {
            entries.add(JsonUtils.fromFile("belt", "science", "book-tileable.txt"));
        }

        if (flags.contains(BookFlags.EXPENSIVE_RECIPES)) {
            entries.add(new BlueprintBookItem(ScienceExpensiveBook.generateBook(flags)));
            entries.add(JsonUtils.fromFile("belt", "science-expensive", "early-tileable.txt"));
        }

        entries.add(new BlueprintBookItem(SolarBook.generateBook(flags)));
        entries.add(new BlueprintBookItem(MilitaryBook.generateBook(flags)));
        entries.add(JsonUtils.fromFile("power", "uranium-processing-kerza.txt"));
        entries.add(JsonUtils.fromFile("power", "reactor-2.4gw-ferront.txt"));
        entries.add(JsonUtils.fromFile("power", "reactor-tileable-khornar.txt"));
        entries.add(JsonUtils.fromFile("power", "starter-216.txt"));

        entries.add(JsonUtils.fromFile("rail-designs-3-8", "mines", "mine-uranium.txt"));

        if (flags.contains(BookFlags.TRAINS_3_8)) {
            entries.add(JsonUtils.fromFile("rail-designs-3-8", "mines", "mines-jrz.txt"));
            entries.add(JsonUtils.fromFile("rail-designs-3-8", "mines", "crude-mskitty.txt"));
        }
        if (flags.contains(BookFlags.TRAINS_2_4)) {
            entries.add(JsonUtils.fromFile("rail-designs-2-4", "mines", "mines-ashy.txt"));
        }

        entries.add(JsonUtils.fromFile("module-upgrader-pixelcort.txt"));
        entries.add(JsonUtils.fromFile("deconstruction-ashy.txt"));

        book.setBlueprints(entries);
    }

    public static BlueprintBookItem generateFJFFBook() {
        String date = ISO_LOCAL_DATE.format(LocalDate.now());

        BlueprintBook book = new BlueprintBook();
        book.setLabel("[FJFF] " + date);
        book.setDescription("""
                Starter Game Blueprints for the Factorio Just For Fun Server. Compiled, scripted, and filtered by Ashy.
                This book is designed for an expensive mode server and uses 3-8 trains. As such, many blueprints will be incorrectly ratioed on a normal recipes server.
                https://discord.gg/ehHEDDnPWA""");
        book.setIcons(Arrays.asList(
                new Icon(new SignalID("signal-F", SignalID.Type.VIRTUAL), 1),
                new Icon(new SignalID("signal-J", SignalID.Type.VIRTUAL), 2),
                new Icon(new SignalID("signal-F", SignalID.Type.VIRTUAL), 3),
                new Icon(new SignalID("signal-F", SignalID.Type.VIRTUAL), 4)
        ));

        List<BlueprintBookEntry> entries = new ArrayList<>();
        entries.add(JsonUtils.fromFile("do-not-take-these-ash.txt"));
        entries.add(JsonUtils.fromFile("do-not-take-these-ash.txt"));
        entries.add(JsonUtils.fromFile("do-not-take-these-ash.txt"));
        entries.add(JsonUtils.fromFile("do-not-take-these-ash.txt"));
        entries.add(JsonUtils.fromFile("do-not-take-these-ash.txt"));
        entries.add(JsonUtils.fromFile("do-not-take-these-ash.txt"));
        book.setBlueprints(entries);

        generateBook(book, EnumSet.of(BookFlags.TRAINS_3_8, BookFlags.EXPENSIVE_RECIPES));

        BlueprintBookItem bookItem = new BlueprintBookItem(book);
        String tag = date + " FJFF Blueprints compiled by Ashy314.\nhttps://discord.gg/ehHEDDnPWA";

        BlueprintUtils.patch(bookItem, tag);
        return bookItem;
    }

    public static BlueprintBookItem generatePersonalBook() {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("[Ashy314] Personal Book");
        book.setDescription("Ashy314's Starter Blueprint Book");
        book.setIcons(Arrays.asList(
                new Icon(new SignalID("signal-A", SignalID.Type.VIRTUAL), 1),
                new Icon(new SignalID("signal-S", SignalID.Type.VIRTUAL), 2),
                new Icon(new SignalID("signal-H", SignalID.Type.VIRTUAL), 3),
                new Icon(new SignalID("signal-Y", SignalID.Type.VIRTUAL), 4)
        ));

        generateBook(book, EnumSet.of(BookFlags.TRAINS_3_8, BookFlags.TRAINS_2_4, BookFlags.NORMAL_RECIPES, BookFlags.EXPENSIVE_RECIPES, BookFlags.INCLUDE_ALTERNATES));

        BlueprintBookItem bookItem = new BlueprintBookItem(book);
        BlueprintUtils.patch(bookItem);
        return bookItem;
    }
}
