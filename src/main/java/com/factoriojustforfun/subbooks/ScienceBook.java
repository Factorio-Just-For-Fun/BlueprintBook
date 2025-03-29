package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.utils.BlueprintUtils;
import com.factoriojustforfun.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScienceBook {
    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Science");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.ITEM).name("automation-science-pack").build(), 1)
        ));

        // blueprints.add(new BlueprintItem(BlueprintUtils.createMainBus(MAIN_BUS)));
        List<BlueprintBookEntry> blueprints = new ArrayList<>(
                ((BlueprintBookItem) JsonUtils.fromFile("common/science/book-tileable.txt"))
                        .getBlueprintBook().getBlueprints());
        blueprints = BlueprintUtils.fitOrderToIndices(blueprints);
        blueprints.add(null);
        blueprints.add(null);
        blueprints.add(null);


        blueprints.add(JsonUtils.fromFile("common/science/late-red-dfarmer.txt"));
        blueprints.add(JsonUtils.fromFile("common/science/late-green-dfarmer.txt"));
        blueprints.add(JsonUtils.fromFile("common/science/late-blue-grey-dfarmer.txt"));
        blueprints.add(JsonUtils.fromFile("common/science/late-yellow-purple-dfarmer.txt"));
        BlueprintUtils.fitIndicesToOrder(blueprints);

        book.setBlueprints(blueprints);
        return book;
    }
}
