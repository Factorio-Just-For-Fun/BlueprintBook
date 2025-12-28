package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.*;
import com.factoriojustforfun.objects.bookentries.BlueprintItem;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class NauvisBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Nauvis");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.SPACE_LOCATION).name("nauvis").build(), 1)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("nauvis/bus.txt"));
        blueprints.add(JsonUtils.fromFile("nauvis/mall-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("malls/belts.txt"));
        blueprints.add(JsonUtils.fromFile("malls/bot-factory.txt"));

        blueprints.add(JsonUtils.fromFile("nauvis/refinery-fox.txt"));
        blueprints.add(JsonUtils.fromFile("nauvis/space-platform-dedicated.txt"));
        blueprints.add(JsonUtils.fromFile("nauvis/space-platform-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("common/fluids/sulfur-sulfuric-combined-mskitty.txt"));

        blueprints.add(JsonUtils.fromFile("common/smelting/steel-furnaces/bus-upgradeable-generic.txt"));
        blueprints.add(JsonUtils.fromFile("common/smelting/steel-furnaces/steel-upgradeable-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("common/smelting/electric-furnaces/side-loading-mskitty.txt"));
        // blueprints.add(JsonUtils.fromFile("common/smelting/electric-furnaces/steel-mskitty.txt"));

        blueprints.add(JsonUtils.fromFile("military/wallmoeba-segment.txt"));
        blueprints.add(JsonUtils.fromFile("military/wallmoeba-corner.txt"));
        blueprints.add(JsonUtils.fromFile("military/biter-egg-silo-3.txt"));
        blueprints.add(JsonUtils.fromFile("military/biter-egg-silo-mass.txt"));

        book.setBlueprints(blueprints);
        return book;
    }
}
