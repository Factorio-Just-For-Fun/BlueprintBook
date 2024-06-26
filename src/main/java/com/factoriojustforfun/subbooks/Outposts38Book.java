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

public class Outposts38Book {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("X on Rails - Unfiltered");
        book.setIcons(Arrays.asList(
                new Icon(new SignalID("rail", SignalID.Type.ITEM), 1),
                new Icon(new SignalID("beacon", SignalID.Type.ITEM), 2),
                new Icon(new SignalID("signal-U", SignalID.Type.VIRTUAL), 3),
                new Icon(new SignalID("signal-F", SignalID.Type.VIRTUAL), 4)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "smelting", "clocked-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "fluids-universal", "plastic-advanced-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "fluids-universal", "coal-liquefaction-plastic-tileable-mskitty.txt"));

        // Green Chips
        if (flags.contains(BookFlags.EXPENSIVE_RECIPES)) {
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "green-chips-from-raw-mskitty.txt"));
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "green-chips-from-raw-cfras5.txt"));
        }

        if (flags.contains(BookFlags.NORMAL_RECIPES)) {
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates", "green-chips-from-raw-ashy.txt"));
        }

        // Other Chips
        if (flags.contains(BookFlags.EXPENSIVE_RECIPES)) {
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "red-chips-from-raw-mskitty.txt"));
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "red-chips-from-raw-kerza-start.txt"));
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "red-chips-from-raw-kerza-extension.txt"));
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "blue-chips-from-raw-mskitty.txt"));
        }

        // Modules
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "modules-expensive", "modules-productivity-from-raw-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "modules-expensive", "modules-speed-from-raw-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "modules-expensive", "modules-from-raw-kerza.txt"));
        if (flags.contains(BookFlags.INCLUDE_ALTERNATES)) {
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "modules-expensive", "modules-cfras5.txt"));
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "modules-expensive", "modules-cfras5-steel.txt"));
        }

        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "belts-mskitty-early.txt"));
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "gears-mskitty.txt"));
        if (flags.contains(BookFlags.INCLUDE_ALTERNATES)) {
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "gears-kerza.txt"));
        }

        if (flags.contains(BookFlags.EXPENSIVE_RECIPES)) {
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "science-expensive", "all-science-from-raw-kerza.txt"));
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "science-expensive", "low-density-structures-from-raw-mskitty.txt"));
        }


        book.setBlueprints(blueprints);
        return book;
    }
}
