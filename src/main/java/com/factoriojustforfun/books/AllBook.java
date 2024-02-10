package com.factoriojustforfun.books;

import com.factoriojustforfun.objects.BlueprintBook;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.factoriojustforfun.objects.Icon;
import com.factoriojustforfun.objects.SignalID;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.objects.bookentries.BlueprintItem;
import com.factoriojustforfun.utils.JsonUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AllBook {
    public static BlueprintBook generateBook() {
        BlueprintBook book = new BlueprintBook();
        book.setLabel("[Ashy314] All Blueprints");
        book.setDescription("All blueprints in the repository. Compiled by Ashy314, though not vetted for quality. Do not post in game blueprints.");
        book.setIcons(Arrays.asList(
                new Icon(new SignalID("signal-A", SignalID.Type.VIRTUAL), 3),
                new Icon(new SignalID("signal-S", SignalID.Type.VIRTUAL), 3),
                new Icon(new SignalID("signal-H", SignalID.Type.VIRTUAL), 3),
                new Icon(new SignalID("signal-Y", SignalID.Type.VIRTUAL), 4)
        ));

        book.setBlueprints(loadDirectory(JsonUtils.BLUEPRINT_DIRECTOY));
        return book;
    }

    private static List<BlueprintBookEntry> loadDirectory(File directory) {
        return Arrays.stream(Objects.requireNonNull(directory.listFiles())).parallel()
                .map(it -> {
                    if (it.isFile()) return JsonUtils.fromFile(it);
                    BlueprintBook book = new BlueprintBook();
                    book.setLabel(it.getName());

                    book.setBlueprints(loadDirectory(it));
                    return new BlueprintBookItem(book);
                }).sorted((a, b) -> {
                    if (a instanceof BlueprintBookItem) {
                        if (!(b instanceof BlueprintBookItem)) return -1;
                        return ((BlueprintBookItem) a).getBlueprintBook().getLabel().compareTo(((BlueprintBookItem) b).getBlueprintBook().getLabel());
                    } else {
                        if (b instanceof BlueprintBookItem) return 1;
                    }

                    if (a instanceof BlueprintItem && b instanceof BlueprintItem) {
                        String labelA = ((BlueprintItem) a).getBlueprint().getLabel();
                        String labelB = ((BlueprintItem) b).getBlueprint().getLabel();
                        if (labelA == null) labelA = "";
                        if (labelB == null) labelB = "";

                        return labelA.compareTo(labelB);
                    }
                    return 0;
                }).collect(Collectors.toList());
    }
}
