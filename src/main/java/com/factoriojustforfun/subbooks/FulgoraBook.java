package com.factoriojustforfun.subbooks;

import com.factoriojustforfun.BookFlags;
import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.objects.bookentries.BlueprintItem;
import com.factoriojustforfun.utils.BlueprintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class FulgoraBook {
    public static final List<String> MAIN_BUS = Arrays.asList(
            "",
            "",
            "",
            ""
    );

    public static BlueprintBook generateBook(Set<BookFlags> flags) {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("Fulgora");
        book.setIcons(List.of(
                new Icon(SignalID.builder().type(SignalID.Type.SPACE_LOCATION).name("fulgora").build(), 1)
        ));

        List<BlueprintBookEntry> blueprints = new ArrayList<>();
        blueprints.add(new BlueprintItem(BlueprintUtils.createMainBus(MAIN_BUS)));

        book.setBlueprints(BlueprintUtils.explode(blueprints));
        return book;
    }
}
