package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.utils.BlueprintUtils;
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
        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "fluids-universal", "coal-liquefaction-plastic-mskitty.txt"));

        if (flags.contains(BookFlags.EXPENSIVE_RECIPES)) {
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "green-chips-from-raw-kerza-start.txt"));
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "intermediates-expensive", "green-chips-from-raw-kerza-extension.txt"));
            blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "science-expensive", "all-science-from-raw-kerza.txt"));
        }

        blueprints.add(JsonUtils.fromFile("rail-designs-3-8", "modules-expensive", "modules-from-raw-kerza.txt"));

        book.setBlueprints(blueprints);
        return book;
    }
}
