package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.*;
import com.factoriojustforfun.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RailsBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("3-8 Rail Network");
        book.setIcons(Collections.singletonList(new Icon(new SignalID("signal-4", SignalID.Type.VIRTUAL), 1)));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("rail-grids-3-8", "2-3-width-spzi.txt"));
        blueprints.add(JsonUtils.fromFile("rail-grids-3-8", "4-3-width-spzi.txt"));
        blueprints.add(JsonUtils.fromFile("rail-grids-3-8", "3-8-stackers-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("rail-grids-3-8", "fuels-spzi.txt"));

        if (flags.contains(BookFlags.TRAINS_3_8)) {
            blueprints.add(JsonUtils.fromFile("rail-grids-3-8", "solid-loading-spzi-kerza.txt"));
            blueprints.add(JsonUtils.fromFile("rail-grids-3-8", "liquid-loading-spzi-mskitty-kerza.txt"));
            blueprints.add(JsonUtils.fromFile("rail-grids-3-8", "trains-mskitty.txt"));
        }

        book.setBlueprints(blueprints);
        return book;
    }
}
