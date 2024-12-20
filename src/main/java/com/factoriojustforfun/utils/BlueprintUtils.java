package com.factoriojustforfun.utils;

import com.factoriojustforfun.objects.*;
import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.objects.bookentries.BlueprintItem;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Consumer;

public class BlueprintUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger("BlueprintUtils");
    public static final Map<String, Color> STATION_COLORS = new HashMap<>();

    static {
        STATION_COLORS.put("iron-ore", new Color(	70, 124, 155, 127));
        STATION_COLORS.put("copper-ore", new Color(	238, 79, 58, 127));
        STATION_COLORS.put("coal", new Color(0, 0, 0, 127));
        STATION_COLORS.put("stone", new Color(	193, 143, 53, 127));
        STATION_COLORS.put("uranium-ore", new Color(97, 205, 0, 127));

        STATION_COLORS.put("crude-oil", new Color(0, 0, 0, 127));
        STATION_COLORS.put("water", new Color(0, 104, 184, 127));
        STATION_COLORS.put("sulfuric-acid", new Color(230, 194, 0, 127));
        STATION_COLORS.put("petroleum-gas", new Color(92, 0, 92, 127));

        STATION_COLORS.put("plastic-bar", new Color(251, 251, 251, 127));

        STATION_COLORS.put("iron-plate", new Color(176, 178, 176, 127));
        STATION_COLORS.put("copper-plate", new Color(254, 98, 49, 127));
        STATION_COLORS.put("steel-plate", new Color(224, 224, 212, 127));

        STATION_COLORS.put("electronic-circuit", new Color(44, 197, 0, 127));
        STATION_COLORS.put("advanced-circuit", new Color(255, 12, 0, 127));
        STATION_COLORS.put("processing-unit", new Color(0, 71, 255, 127));

        STATION_COLORS.put("low-density-structure", new Color(161, 125, 67, 127));
    }

    public static void forEachBlueprint(BlueprintBookEntry entry, Consumer<BlueprintBookEntry> consumer) {
        if (entry instanceof BlueprintBookItem) {
            BlueprintBook book = ((BlueprintBookItem) entry).getBlueprintBook();
            book.getBlueprints().parallelStream().forEach(subentry -> forEachBlueprint(subentry, consumer));
        }
        consumer.accept(entry);
    }

    public static void patch(BlueprintBookEntry entry) {
        forEachBlueprint(entry, it -> applyFixes(it, ""));
    }
    public static void patch(BlueprintBookEntry entry, String tag) {
        forEachBlueprint(entry, it -> applyFixes(it, tag));
    }
    public static void applyFixes(BlueprintBookEntry entry, String tag) {
        if (!(entry instanceof BlueprintBookItem) && !(entry instanceof BlueprintItem)) {
            LOGGER.debug("Skipping entry as not a blueprint or blueprint book");
            return;
        }

        String label = (entry instanceof BlueprintBookItem) ? ((BlueprintBookItem) entry).getBlueprintBook().getLabel() : ((BlueprintItem) entry).getBlueprint().getLabel();
        LOGGER.debug("Applying tag and fixes to {}", label);

        String description = (entry instanceof BlueprintBookItem) ? ((BlueprintBookItem) entry).getBlueprintBook().getDescription() : ((BlueprintItem) entry).getBlueprint().getDescription();

        if (description == null || description.isEmpty()) {
            LOGGER.debug("Applying description as it does not exist or is empty.");
            description = "";
        } else {
            LOGGER.debug("Found description {}", description);
        }

        String newDescription = description.replaceAll("\\d{4}-\\d{2}-\\d{2} FJFF (Common )?Blueprints compiled by ((i_cant)|(Ashy(314)?)).\\nhttps://discord\\.gg/ehHEDDnPWA", "");

        if (!newDescription.equals(description)){
            LOGGER.warn("Blueprint {} had an outdated tag! ({})", label, description);
        }

        newDescription = (newDescription + "\n\n" + tag).replaceAll("(\\r\\n|\\r|\\n){2,}", "\n\n").trim();

        LOGGER.debug("Applying description.");
        if (entry instanceof BlueprintBookItem) ((BlueprintBookItem) entry).getBlueprintBook().setDescription(newDescription.trim());
        else {
            Blueprint blueprint = ((BlueprintItem) entry).getBlueprint();

            blueprint.setDescription(newDescription.trim());
            if (blueprint.getEntities() == null) return;

            LOGGER.debug("Applying train stop fixes.");
            blueprint.getEntities().parallelStream().filter(it -> it.getName().equals("train-stop")).forEach(BlueprintUtils::fixStation);
        }
    }

    public static void fixStation(Entity trainStop) {
        String text = trainStop.getStation();
        if (text == null || text.isEmpty()) {
            LOGGER.debug("Applying template label to tran station.");
            text = "☭ Communism";
        }

        String newText = text
                .replaceAll("\\[/?color(=((\\d{1,3},\\d{1,3},\\d{1,3})|(\\w+)))?]", "") // Remove colors
                .replaceAll("(\\s\\*)+$", "") // Remove stars
                .replaceAll("3-8", "") // Remove 3-8 indicators
                .replaceAll("\\[img=(item|fluid).([\\w\\-]+)]", "[$1=$2]") // Replace [img=item/fluid.name] with [item/fluid=name]
                .replaceAll("^\\[([UL])]\\s\\[", "[$1][") // Remove space between [U/L] and a [Tag]
                .replaceAll("^\\[([UL])](\\w)", "[$1] $2") // Add space between [U/L] and a tag that's not in brackets
                .replaceAll("\\[U]\\[virtual-signal=signal-red] Trash", "[U][virtual-signal=signal-red]Trash") // Fix trash trains
                .trim();

        if (!text.equals(newText)) LOGGER.warn("Corrected train stop {} to {}", text, newText);
        trainStop.setStation(newText);

        for (Map.Entry<String, Color> entry : STATION_COLORS.entrySet()) {
            if (newText.contains(entry.getKey())) {
                trainStop.setColor(entry.getValue());
                break;
            }
        }
    }

    public static Blueprint createMainBus(List<String> entries, String name) {
        LOGGER.debug("Creating Main Bus with entries {}", entries);

        Blueprint blueprint = new Blueprint();
        blueprint.setLabel(name);

        List<Entity> entities = new ArrayList<>();

        for (int index = 0; index < entries.size(); index++) {
            String entry = entries.get(index);

            Entity combinator = new Entity();
            combinator.setName("display-panel");
            combinator.setEntityNumber(index + 1);
            combinator.setPosition(new Position(index + Math.floorDiv(index, 4) * 2 + 0.5, 0.5));

            ObjectNode iconNode = JsonUtils.MAPPER.createObjectNode();
            String[] filter = entry.split(":");
            iconNode.put("name", filter[filter.length - 1]);
            if (filter.length > 1) {
                iconNode.put("type", filter[0]);
            }

            combinator.setOtherField("icon", iconNode);

            entities.add(combinator);
        }

        blueprint.setEntities(entities);
        return blueprint;
    }

    public static List<BlueprintBookEntry> explode(List<BlueprintBookEntry> entries) {
        LOGGER.debug("Exploding entries {}", entries);
        List<BlueprintBookEntry> explodedEntries = new ArrayList<>(entries.size());
        for (BlueprintBookEntry entry : entries) {
            if (entry instanceof BlueprintBookItem) explodedEntries.addAll(((BlueprintBookItem) entry).getBlueprintBook().getBlueprints());
            else explodedEntries.add(entry);
        }

        return explodedEntries;
    }

    public static List<BlueprintBookEntry> fitOrderToIndices(List<BlueprintBookEntry> entries) {
        List<BlueprintBookEntry> result = new ArrayList<>();
        for (BlueprintBookEntry entry : entries) {
            while (result.size() < entry.getIndex() + 1) result.add(null);
            result.set(entry.getIndex(), entry);
        }
        return result;
    }

    public static void fitIndicesToOrder(List<BlueprintBookEntry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            BlueprintBookEntry entry = entries.get(i);
            if (entry != null) entry.setIndex(i);
        }
    }
}
