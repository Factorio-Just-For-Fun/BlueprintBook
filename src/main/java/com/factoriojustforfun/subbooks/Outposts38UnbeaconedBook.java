package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Outposts38UnbeaconedBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("X on Rails - Unfiltered (No Beacons)");
        book.setIcons(Arrays.asList(
                new Icon(new SignalID("rail", SignalID.Type.ITEM), 1),
                new Icon(new SignalID("rail", SignalID.Type.ITEM), 2),
                new Icon(new SignalID("signal-U", SignalID.Type.VIRTUAL), 3),
                new Icon(new SignalID("signal-F", SignalID.Type.VIRTUAL), 4)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "smelting", "nobeacon-kerza-start.txt"));
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "smelting", "nobeacon-kerza-extension.txt"));
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "smelting", "steel-nobeacon-mskitty.txt"));

        if (flags.contains(BookFlags.EXPENSIVE_RECIPES)) {
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "green-chips-no-beacon-kerza.txt"));
        }

        book.setBlueprints(blueprints);
        return book;
    }
}
