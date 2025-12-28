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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class StarterBook {
    private static final Logger LOGGER = LoggerFactory.getLogger("StarterBook");

    public static void generateBook(BlueprintBook book, Set<BookFlags> flags) {
        LOGGER.info("Creating Starter Book with flags {}", flags);
        List<BlueprintBookEntry> entries = book.getBlueprints() == null ? new ArrayList<>() : book.getBlueprints();

        LOGGER.info("Loading core prints...");
        entries.add(JsonUtils.fromFile("balancers-raynquist.txt"));
        entries.add(JsonUtils.fromFile("3-8-rail-network-trimmed.txt"));
        entries.add(JsonUtils.fromFile("construction-compendium-trimmed.txt"));

        LOGGER.info("Loading Custom Books...");
        entries.add(new BlueprintBookItem(CommonBook.generateBook(flags)));
        entries.add(new BlueprintBookItem(QualityBook.generateBook(flags)));
        entries.add(new BlueprintBookItem(SpacePlatformsBook.generateBook(flags)));
        entries.add(new BlueprintBookItem(NauvisBook.generateBook(flags)));
        entries.add(new BlueprintBookItem(VulcanusBook.generateBook(flags)));
        entries.add(new BlueprintBookItem(GlebaBook.generateBook(flags)));
        entries.add(new BlueprintBookItem(FulgoraBook.generateBook(flags)));
        entries.add(new BlueprintBookItem(AquiloBook.generateBook(flags)));
        // entries.add(JsonUtils.fromFile("fulgora/compendium-kerza.txt"));
        // entries.add(JsonUtils.fromFile("aquilo/compendium-kerza.txt"));

        LOGGER.info("Loading auxiliary prints...");
        entries.add(new BlueprintBookItem(ScienceBook.generateBook(flags)));
        entries.add(JsonUtils.fromFile("malls/modules-kerza.txt"));
        entries.add(JsonUtils.fromFile("power/uranium/uranium-processing-kerza.txt"));
        // entries.add(JsonUtils.fromFile("power/uranium/kovarex-kerza.txt"));
        // entries.add(JsonUtils.fromFile("power/uranium/nukes-kerza.txt"));
        entries.add(JsonUtils.fromFile("power/nuclear/reactor-tileable-new.txt"));
        entries.add(JsonUtils.fromFile("power/fusion-tileable.txt"));
        entries.add(JsonUtils.fromFile("power/starter-216.txt"));

        entries.add(JsonUtils.fromFile("deconstruction-ashy.txt"));
        entries.add(JsonUtils.fromFile("quality-upgraders.txt"));

        book.setBlueprints(entries);
    }

    public static BlueprintBookItem generateFJFFBook() {
        String date = ISO_LOCAL_DATE.format(LocalDate.now());

        LOGGER.info("Creating FJFF book at {}", date);
        BlueprintBook book = new BlueprintBook();
        book.setLabel("[FJFF] " + date);
        book.setDescription("""
                Game Blueprints for the Factorio Just For Fun Server. Compiled and filtered by Ashy314.
                https://discord.gg/ehHEDDnPWA""");
        book.setIcons(Arrays.asList(
                new Icon(SignalID.builder().type(SignalID.Type.VIRTUAL).name("signal-F").build(), 1),
                new Icon(SignalID.builder().type(SignalID.Type.VIRTUAL).name("signal-J").build(), 2),
                new Icon(SignalID.builder().type(SignalID.Type.VIRTUAL).name("signal-F").build(), 3),
                new Icon(SignalID.builder().type(SignalID.Type.VIRTUAL).name("signal-F").build(), 4)
        ));

        List<BlueprintBookEntry> entries = new ArrayList<>();
        entries.add(JsonUtils.fromFile("do-not-take-these-ashy.txt"));
        entries.add(JsonUtils.fromFile("wip.txt"));
        entries.add(JsonUtils.fromFile("do-not-take-these-ashy.txt"));
        entries.add(JsonUtils.fromFile("wip.txt"));
        entries.add(JsonUtils.fromFile("do-not-take-these-ashy.txt"));
        entries.add(JsonUtils.fromFile("wip.txt"));
        book.setBlueprints(entries);

        LOGGER.info("Loading entries");
        generateBook(book, Collections.emptySet());

        BlueprintBookItem bookItem = new BlueprintBookItem(book);

        LOGGER.info("Tagging prints");
        String tag = date + " FJFF Blueprints compiled by Ashy314.\nhttps://discord.gg/ehHEDDnPWA";
        BlueprintUtils.patch(bookItem, tag);
        return bookItem;
    }

    public static BlueprintBookItem generatePersonalBook() {
        LOGGER.info("Creating personal book");
        BlueprintBook book = new BlueprintBook();
        book.setLabel("[Ashy314] Personal Book");
        book.setDescription("Ashy314's Starter Blueprint Book");
        book.setIcons(Arrays.asList(
                new Icon(SignalID.builder().type(SignalID.Type.VIRTUAL).name("signal-A").build(), 1),
                new Icon(SignalID.builder().type(SignalID.Type.VIRTUAL).name("signal-S").build(), 2),
                new Icon(SignalID.builder().type(SignalID.Type.VIRTUAL).name("signal-H").build(), 3),
                new Icon(SignalID.builder().type(SignalID.Type.VIRTUAL).name("signal-Y").build(), 4)
        ));

        LOGGER.info("Loading entries");
        generateBook(book, EnumSet.of(BookFlags.INCLUDE_ALTERNATES));

        LOGGER.info("Tagging prints");
        BlueprintBookItem bookItem = new BlueprintBookItem(book);
        BlueprintUtils.patch(bookItem);
        return bookItem;
    }
}
