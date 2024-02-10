package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SolarBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Solar mskitty");
        book.setIcons(Arrays.asList(
                new Icon(new SignalID("train-stop", SignalID.Type.ITEM), 1),
                new Icon(new SignalID("solar-panel", SignalID.Type.ITEM), 2),
                new Icon(new SignalID("accumulator", SignalID.Type.ITEM), 3),
                new Icon(new SignalID("signal-D", SignalID.Type.VIRTUAL), 4)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("power", "solar-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("malls", "solar", "solar-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("malls", "solar", "accumulator-mskitty.txt"));

        book.setBlueprints(BlueprintUtils.explode(blueprints));
        return book;
    }
}
