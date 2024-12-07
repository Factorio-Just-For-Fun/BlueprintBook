package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.util.*;

public class MilitaryBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Military");
        book.setIcons(Arrays.asList(
                new Icon(new SignalID("rail", SignalID.Type.ITEM), 1),
                new Icon(new SignalID("beacon", SignalID.Type.ITEM), 2),
                new Icon(new SignalID("explosive-rocket", SignalID.Type.ITEM), 3),
                new Icon(new SignalID("rocket", SignalID.Type.ITEM), 4)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();

        blueprints.add(JsonUtils.fromFile("biters", "wallmoeba-segment.txt"));
        blueprints.add(JsonUtils.fromFile("biters", "wallmoeba-corner.txt"));
        blueprints.add(JsonUtils.fromFile("biters", "wallmoeba-segment-no-lasers.txt"));
        if (flags.contains(BookFlags.INCLUDE_ALTERNATES)) {
            blueprints.add(JsonUtils.fromFile("biters", "walls-pinko.txt"));
        }

        blueprints.add(JsonUtils.fromFile("malls", "military", "explosive-rockets-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("malls", "military", "artillery-shells-mskitty.txt"));
        if (flags.contains(BookFlags.INCLUDE_ALTERNATES)) {
            blueprints.add(JsonUtils.fromFile("malls", "military", "military-hub-2.txt"));
        }

        blueprints.add(JsonUtils.fromFile("biters", "war-rails-mskitty.txt"));

        book.setBlueprints(BlueprintUtils.explode(blueprints));
        return book;
    }
}
