package com.factoriojustforfun.utils;

import com.factoriojustforfun.objects.*;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.objects.bookentries.BlueprintItem;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BlueprintUtils {
    public static final Map<String, Color> STATION_COLORS = new HashMap<>();

    static {
        STATION_COLORS.put("iron-ore", new Color(0, 0, 0, 255));
        STATION_COLORS.put("copper-ore", new Color(0, 0, 0, 255));
        STATION_COLORS.put("coal-ore", new Color(0, 0, 0, 255));
        STATION_COLORS.put("stone-ore", new Color(0, 0, 0, 255));
        STATION_COLORS.put("uranium-ore", new Color(0, 0, 0, 255));

        STATION_COLORS.put("crude-oil", new Color(0, 0, 0, 255));
        STATION_COLORS.put("water", new Color(0, 0, 0, 255));
        STATION_COLORS.put("sufuric-acid", new Color(0, 0, 0, 255));
        STATION_COLORS.put("petroleum-gas", new Color(0, 0, 0, 255));

        STATION_COLORS.put("plastic-bar", new Color(0, 0, 0, 255));

        STATION_COLORS.put("iron-plate", new Color(0, 0, 0, 255));
        STATION_COLORS.put("copper-plate", new Color(0, 0, 0, 255));
        STATION_COLORS.put("steel-plate", new Color(0, 0, 0, 255));

        STATION_COLORS.put("electronic-circuit", new Color(0, 0, 0, 255));
        STATION_COLORS.put("advanced-circuit", new Color(0, 0, 0, 255));
        STATION_COLORS.put("processing-unit", new Color(0, 0, 0, 255));

        STATION_COLORS.put("Trash", new Color(0, 0, 0, 255));
        STATION_COLORS.put("Wall Supplies", new Color(0, 0, 0, 255));
    }
    public static void forEachBlueprint(BlueprintBookEntry entry, Consumer<Blueprint> consumer) {
        if (entry instanceof BlueprintBookItem) {
            BlueprintBook book = ((BlueprintBookItem) entry).getBlueprintBook();
            book.getBlueprints().parallelStream().forEach(subentry -> forEachBlueprint(subentry, consumer));
        } else if (entry instanceof BlueprintItem) {
            Blueprint blueprint = ((BlueprintItem) entry).getBlueprint();
            consumer.accept(blueprint);
        }
    }

    public static void patch(BlueprintBookEntry entry) {
        forEachBlueprint(entry, BlueprintUtils::patch);
    }
    public static void patch(Blueprint blueprint) {
        String description = blueprint.getDescription();
        if (description == null || description.isEmpty()) description = "Unknown Blueprint."; // TODO Possibly add calculation
        String newDescription = description.replaceAll("\\d{4}-\\d{2}-\\d{2} FJFF (Common )?Blueprints compiled by ((i_cant)|(Ashy)).\\nhttps://discord\\.gg/ehHEDDnPWA", "");

        if (!newDescription.equals(description)) System.out.println("Blueprint " + blueprint.getLabel() + " had an outdated tag!");
        blueprint.setDescription(newDescription.trim());

        if (blueprint.getEntities() == null) return;
        blueprint.getEntities().parallelStream().filter(it -> it.getName().equals("train-stop")).forEach(BlueprintUtils::fixStation);
    }

    public static void fixStation(Entity trainStop) {
        String text = trainStop.getStation();
        if (text == null || text.isEmpty()) text = "☭ Communism";

        String newText = text
                .replaceAll("\\[/?color(=((\\d{1,3},\\d{1,3},\\d{1,3})|(\\w+)))?]", "") // Remove colors
                .replaceAll("(\\s\\*)+$", "") // Remove stars
                .replaceAll("3-8", "") // Remove 3-8 indicators
                .replaceAll("\\[img=(item|fluid).([\\w\\-]+)]", "[$1=$2]") // Replace [img=item/fluid.name] with [item/fluid=name]
                .replaceAll("^\\[([UL])]\\s\\[", "[$1][") // Remove space between [U/L] and a [Tag]
                .replaceAll("^\\[([UL])](\\w)", "[$1] $2") // Add space between [U/L] and a tag that's not in brackets
                .replaceAll("\\[U]\\[virtual-signal=signal-red] Trash", "[U][virtual-signal=signal-red]Trash") // Fix trash trains
                .trim();

        if (!text.equals(newText)) System.out.println("Corrected train stop " + text + " to " + newText);
        trainStop.setStation(newText);

        for (Map.Entry<String, Color> entry : STATION_COLORS.entrySet()) {
            if (newText.contains(entry.getKey())) {
                trainStop.setColor(entry.getValue());
                break;
            }
        }
    }

    public static Blueprint createMainBus(List<String> entries) {
        Blueprint blueprint = new Blueprint();
        blueprint.setLabel("Main Bus [Ashy]");

        List<Entity> entities = new ArrayList<>();

        for (int index = 0; index < entries.size(); index++) {
            String entry = entries.get(index);

            Entity combinator = new Entity();
            combinator.setName("constant-combinator");
            combinator.setEntityNumber(index + 1);
            combinator.setPosition(new Position(index + Math.floorDiv(index, 4) * 2 + 0.5, 0.5));

            ArrayNode filters = JsonUtils.MAPPER.createArrayNode();

            String[] filtersRaw = entry.split(";");
            for (int filterIndex = 0; filterIndex < filtersRaw.length; filterIndex++) {
                String[] filter = filtersRaw[filterIndex].split(":");
                ObjectNode signal = JsonUtils.MAPPER.createObjectNode();

                signal.put("name", filter[filter.length - 1]);
                signal.put("type", filter.length > 1 ? filter[0] : "item");

                ObjectNode filterNode = JsonUtils.MAPPER.createObjectNode();
                filterNode.set("signal", signal);
                filterNode.put("count", 1);
                filterNode.put("index", filterIndex + 1);

                filters.add(filterNode);
            }

            ObjectNode controlBehavior = JsonUtils.MAPPER.createObjectNode();
            controlBehavior.set("filters", filters);
            combinator.setControlBehavior(controlBehavior);

            entities.add(combinator);
        }

        blueprint.setEntities(entities);
        return blueprint;
    }

    public static List<BlueprintBookEntry> explode(List<BlueprintBookEntry> entries) {
        List<BlueprintBookEntry> explodedEntries = new ArrayList<>(entries.size());
        for (BlueprintBookEntry entry : entries) {
            if (entry instanceof BlueprintBookItem) explodedEntries.addAll(((BlueprintBookItem) entry).getBlueprintBook().getBlueprints());
            else explodedEntries.add(entry);
        }

        return explodedEntries;
    }
}
