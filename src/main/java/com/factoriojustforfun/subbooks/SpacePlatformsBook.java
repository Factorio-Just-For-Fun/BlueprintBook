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

public class SpacePlatformsBook {

    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Vulcanus");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.ITEM).name("space-platform-starter-pack").build(), 1),
                new Icon(SignalID.builder().type(SignalID.Type.ITEM).name("asteroid-collector").build(), 2),
                new Icon(SignalID.builder().type(SignalID.Type.ITEM).name("crusher").build(), 3),
                new Icon(SignalID.builder().type(SignalID.Type.ITEM).name("space-platform").build(), 4)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("ships/stationary/science-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("ships/stationary/calcite-madkatz.txt"));
        blueprints.add(JsonUtils.fromFile("ships/hauler/early.txt"));
        blueprints.add(JsonUtils.fromFile("ships/hauler/early-aquilo.txt"));
        blueprints.add(JsonUtils.fromFile("ships/hauler/spoilable-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("ships/hauler/close-planets-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("ships/stationary/calcite-madkatz.txt"));

        book.setBlueprints(BlueprintUtils.explode(blueprints));
        return book;
    }
}
