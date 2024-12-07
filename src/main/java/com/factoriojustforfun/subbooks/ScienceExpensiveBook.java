package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ScienceExpensiveBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Science EXPENSIVE mskitty");
        book.setIcons(Collections.singletonList(new Icon(new SignalID("automation-science-pack", SignalID.Type.ITEM), 1)));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("belt", "science-expensive", "red-1kmin-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "science-expensive", "green-1kmin-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "science-expensive", "blue-1kmin-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "science-expensive", "gray-1kmin-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "science-expensive", "purple-1kmin-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "science-expensive", "yellow-1kmin-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "science-expensive", "space-1kmin-mskitty.txt"));
        blueprints.add(JsonUtils.fromFile("belt", "intermediates-expensive", "low-density-structure-fixed-337-mskitty.txt"));

        book.setBlueprints(blueprints);
        return book;
    }
}
