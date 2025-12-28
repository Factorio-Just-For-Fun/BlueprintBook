package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.objects.bookentries.BlueprintItem;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class VulcanusBook {
    public static final List<String> MAIN_BUS = Arrays.asList(
            "",
            "",
            "",
            "",

            "tungsten-ore",
            "tungsten-ore",
            "advanced-circuit",
            "advanced-circuit",

            "",
            "recipe:molten-copper",
            "",
            "recipe:molten-iron",

            "fluid:water",
            "fluid:lubricant",
            "fluid:light-oil",
            "fluid:sulfuric-acid",

            "fluid:lava",
            "tungsten-plate",
            "electronic-circuit",
            "electronic-circuit",

            "coal",
            "coal",
            "stone",
            "calcite",

            "metallurgic-scienec-pack",
            "processing-unit",
            "rocket-fuel",
            "low-density-structure"
    );

    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Vulcanus");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.SPACE_LOCATION).name("vulcanus").build(), 1)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(new BlueprintItem(BlueprintUtils.createMainBus(MAIN_BUS, "Main Bus [Vulcanus]")));
        blueprints.add(JsonUtils.fromFile("vulcanus/starter.txt"));
        blueprints.add(JsonUtils.fromFile("vulcanus/refinery-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("vulcanus/lava-breakdown.txt"));
        // blueprints.add(JsonUtils.fromFile("vulcanus/molten-iron-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("vulcanus/tungsten-carbide-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("vulcanus/tungsten-plate-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("vulcanus/science-metallurgic-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("vulcanus/belts-fluid-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("vulcanus/ammo.txt"));
        blueprints.add(JsonUtils.fromFile("military/worm-mine.txt"));

        book.setBlueprints(blueprints);
        return book;
    }
}
