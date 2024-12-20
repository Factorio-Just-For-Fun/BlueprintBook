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

public class CommonBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Common Book");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.ITEM).name("iron-gear-wheel").build(), 1)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("common/intermediates/green-chips-fluid-em.txt"));
        blueprints.add(JsonUtils.fromFile("common/intermediates/red-chips-fluid-em.txt"));
        blueprints.add(JsonUtils.fromFile("common/intermediates/blue-chips-em.txt"));
        blueprints.add(JsonUtils.fromFile("common/intermediates/lds-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("common/intermediates/lds-fluid.txt"));
        blueprints.add(JsonUtils.fromFile("common/fluids/rocket-fuel-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("ships/launchpad.txt"));
        blueprints.add(JsonUtils.fromFile("common/smelting/foundry/ore-to-plates.txt"));
        blueprints.add(JsonUtils.fromFile("decoration/displays-nightmare.txt"));
        // blueprints.add(JsonUtils.fromFile("common/smelting/foundry/fluid-to-plates-kerza.txt"));

        book.setBlueprints(blueprints);
        return book;
    }
}
