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

public class MainBaseBook {
    public static final List<String> MAIN_BUS = Arrays.asList(
            "space-science-pack",
            "production-science-pack;utility-science-pack",
            "military-science-pack;chemical-science-pack",
            "automation-science-pack;logistic-science-pack",

            "iron-plate", "iron-plate", "iron-plate", "iron-plate",
            "iron-plate", "iron-plate", "iron-plate", "iron-plate",
            "iron-plate", "iron-plate", "iron-plate", "iron-plate",

            "copper-plate", "copper-plate", "copper-plate", "copper-plate",
            "copper-plate", "copper-plate", "copper-plate", "copper-plate",
            "copper-plate", "copper-plate", "copper-plate", "copper-plate",

            "steel-plate", "steel-plate", "steel-plate", "steel-plate",

            "fluid:water", "fluid:lubricant", "fluid:light-oil", "fluid:sulfuric-acid",

            "electronic-circuit", "electronic-circuit", "electronic-circuit", "electronic-circuit",
            "electronic-circuit", "electronic-circuit", "electronic-circuit", "electronic-circuit",

            "plastic-bar", "plastic-bar", "plastic-bar", "plastic-bar",
            "plastic-bar", "plastic-bar", "plastic-bar", "plastic-bar",
            "plastic-bar", "plastic-bar", "plastic-bar", "plastic-bar",

            "advanced-circuit", "advanced-circuit", "advanced-circuit", "processing-unit",

            "stone", "stone", "stone-brick", "stone-brick",
            "coal", "coal", "battery", "concrete"
    );
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Main Base");
        book.setIcons(Arrays.asList(
                new Icon(new SignalID("electronic-circuit", SignalID.Type.ITEM), 1),
                new Icon(new SignalID("sulfur", SignalID.Type.ITEM), 2),
                new Icon(new SignalID("plastic-bar", SignalID.Type.ITEM), 3),
                new Icon(new SignalID("constant-combinator", SignalID.Type.ITEM), 4)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(new BlueprintItem(BlueprintUtils.createMainBus(MAIN_BUS)));
        blueprints.add(JsonUtils.fromFile("malls", "kos-kerza-updated.txt"));
        blueprints.add(JsonUtils.fromFile("malls", "belts.txt"));
        blueprints.add(JsonUtils.fromFile("malls", "bot-factory.txt"));

        blueprints.add(JsonUtils.fromFile("belt", "fluids-universal", "oil-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "fluids-universal", "coal-liquefaction-plastic-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "fluids-universal", "solid-fuel-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "fluids-universal", "plastic-double-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "fluids-universal", "sulfur-sulfuric-combined-mskitty.txt"));

        if (flags.contains(BookFlags.EXPENSIVE_RECIPES)) {
            blueprints.add(JsonUtils.fromFile("belt", "fluids-expensive", "batteries-mskitty.txt"));
            blueprints.add(JsonUtils.fromFile("belt", "intermediates-expensive", "gears-mskitty.txt"));
            blueprints.add(JsonUtils.fromFile("belt", "intermediates-expensive", "green-chips-drsupergood.txt"));
            blueprints.add(JsonUtils.fromFile("belt", "intermediates-expensive", "green-chips-15k-mskitty.txt"));
            blueprints.add(JsonUtils.fromFile("belt", "intermediates-expensive", "red-chips-kerza.txt"));
            blueprints.add(JsonUtils.fromFile("belt", "intermediates-expensive", "red-chips-3.6k-mskitty.txt"));
            blueprints.add(JsonUtils.fromFile("belt", "intermediates-expensive", "blue-chips-mskitty.txt"));

        }

        blueprints.add(JsonUtils.fromFile("belt", "science", "labs.txt"));

        blueprints.add(JsonUtils.fromFile("smelting", "kos-ash.txt"));
        blueprints.add(JsonUtils.fromFile("smelting", "bus-upgradeable-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("smelting", "steel-upgradeable-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("smelting", "side-loading-copper-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("smelting", "side-loading-iron-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("smelting", "steel-mskitty.txt"));

        book.setBlueprints(BlueprintUtils.explode(blueprints));
        return book;
    }
}
