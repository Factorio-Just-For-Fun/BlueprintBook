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

public class AquiloBook {

    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Aquilo");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.SPACE_LOCATION).name("aquilo").build(), 1)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("aquilo/kickstart-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("aquilo/early-power-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("aquilo/power-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("aquilo/quantum-processor-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("aquilo/science-cryo-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("aquilo/rockets-nightmare-kerza.txt"));

        book.setBlueprints(blueprints);
        return book;
    }
}
