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

public class QualityBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Quality");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.VIRTUAL).name("signal-any-quality").build(), 1)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(JsonUtils.fromFile("wip.txt"));
        blueprints.add(JsonUtils.fromFile("quality/captive-biters-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("quality/rootwyrm.txt"));
        // blueprints.add(JsonUtils.fromFile("quality/upcycler.txt"));
        blueprints.add(JsonUtils.fromFile("quality/tileable-assembler-kerza.txt"));
        blueprints.add(JsonUtils.fromFile("quality/one-assembler-kerza.txt"));
        book.setBlueprints(blueprints);
        return book;
    }
}
