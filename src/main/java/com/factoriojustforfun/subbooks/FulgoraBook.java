package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FulgoraBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Fulgora");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.SPACE_LOCATION).name("fulgora").build(), 1)
        ));

        // blueprints.add(new BlueprintItem(BlueprintUtils.createMainBus(MAIN_BUS)));
        List<BlueprintBookEntry> blueprints = new ArrayList<>();
                //new ArrayList<>(
        //((BlueprintBookItem) JsonUtils.fromFile("fulgora/compendium-kerza.txt"))
          //              .getBlueprintBook().getBlueprints());
        //blueprints = BlueprintUtils.fitOrderToIndices(blueprints);
        //blueprints.add(JsonUtils.fromFile("fulgora/scrap.txt"));
        //blueprints.addAll(((BlueprintBookItem) JsonUtils.fromFile("fulgora/filters-nightmare-kerza.txt"))
        //        .getBlueprintBook().getBlueprints());
        //blueprints.add(null);
        //blueprints.add(null);
        //blueprints.add(null);
        blueprints.add(JsonUtils.fromFile("fulgora/rail-load.txt"));
        blueprints.add(JsonUtils.fromFile("fulgora/rail-unload.txt"));
        blueprints.add(JsonUtils.fromFile("fulgora/bus-offloading.txt"));
        BlueprintUtils.fitIndicesToOrder(blueprints);

        book.setBlueprints(blueprints);
        return book;
    }
}
