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
        blueprints.add(JsonUtils.fromFile("gleba/mall.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/ore.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/bioflux.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/jelly.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/yumako-mash.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/plastic.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/lubricant.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/sulfur.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/carbon.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/carbon-fiber.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/rocket-fuel.txt"));
        blueprints.add(JsonUtils.fromFile("gleba/science-agri.txt"));

        book.setBlueprints(blueprints);
        return book;
    }
}
