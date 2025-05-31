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

public class GlebaBook {

    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Gleba");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.SPACE_LOCATION).name("gleba").build(), 1)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("gleba/bus-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/bus-ending-kerza.txt"));

        blueprints.add(JsonUtils.fromFile("gleba/agritower-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/agritower-4-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/bioflux-tileable-kerza.txt"));
        blueprints.add(null);



        blueprints.add(JsonUtils.fromFile("gleba/ore-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("common/smelting/foundry/smelting-two-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("common/smelting/foundry/casting-plates-all-kerza.txt"));

        blueprints.add(JsonUtils.fromFile("gleba/power-burner-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/power-rocket-fuel-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/seeds-jellynut-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/seeds-yumako-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/plastic-tileable-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/lubricant-bioflux-jellynut.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/sulfuric-acid-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/carbon-fiber-tileable-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/science-agri-tileable-kerza.txt"));




        blueprints.add(JsonUtils.fromFile("gleba/space/rocket-fuel-tileable-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/space/landing-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/space/storage-bioflux-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/space/storage-scipack-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/space/overgrowth-shipment-kerza.txt"));
        blueprints.add(null);

        blueprints.add(JsonUtils.fromFile("gleba/mall-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/mall-nauvis-modified-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/explosives-tileable-rockets-kerza.txt"));

        blueprints.add(JsonUtils.fromFile("military/gleba.txt"));

        book.setBlueprints(blueprints);
        return book;
    }
}
