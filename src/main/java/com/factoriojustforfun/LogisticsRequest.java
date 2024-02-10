package com.factoriojustforfun;

import com.factoriojustforfun.objects.Blueprint;
import com.factoriojustforfun.objects.Entity;
import com.factoriojustforfun.objects.Position;
import com.factoriojustforfun.utils.JsonUtils;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class LogisticsRequest {
    private static List<List<String>> defaultOrder = new ArrayList<>();
    private Map<String, Integer> minimums = new HashMap<>();
    private Map<String, Integer> maximums = new HashMap<>();

    static {
        defaultOrder.add(Arrays.asList("wooden-chest", "iron-chest", "steel-chest", "storage-tank"));
        defaultOrder.add(Arrays.asList("transport-belt", "fast-transport-belt", "express-transport-belt", "underground-belt", "fast-underground-belt", "express-underground-belt", "splitter", "fast-splitter", "express-splitter"));
        defaultOrder.add(Arrays.asList("burner-inserter", "inserter", "long-handed-inserter", "fast-inserter", "filter-inserter", "stack-inserter", "stack-filter-inserter"));
        defaultOrder.add(Arrays.asList("small-electric-pole", "medium-electric-pole", "big-electric-pole", "substation", "pipe", "pipe-to-ground", "pump"));
        defaultOrder.add(Arrays.asList("rail", "train-stop", "rail-signal", "rail-chain-signal", "locomotive", "cargo-wagon", "fluid-wagon", "artillery-wagon"));
        defaultOrder.add(Arrays.asList("car", "tank", "spidertron"));
        defaultOrder.add(Arrays.asList("logistic-robot", "construction-robot", "logistic-chest-active-provider", "logistic-chest-passive-provider", "logistic-chest-storage", "logistic-chest-buffer", "logistic-chest-requester", "roboport"));
        defaultOrder.add(Arrays.asList("small-lamp", "red-wire", "green-wire", "arithmetic-combinator", "decider-combinator", "constant-combinator", "power-switch", "programmable-speaker"));
        defaultOrder.add(Arrays.asList("stone-brick", "concrete", "hazard-concrete", "refined-concrete", "refined-hazard-concrete", "landfill", "cliff-explosives"));
        defaultOrder.add(List.of("repair-pack"));
        defaultOrder.add(Arrays.asList("boiler", "steam-engine", "solar-panel", "accumulator", "nuclear-reactor", "heat-pipe", "heat-exchanger", "steam-turbine"));
        defaultOrder.add(Arrays.asList("burner-mining-drill", "electric-mining-drill", "offshore-pump", "pumpjack"));
        defaultOrder.add(Arrays.asList("stone-furnace", "steel-furnace", "electric-furnace"));
        defaultOrder.add(Arrays.asList("assembling-machine-1", "assembling-machine-2", "assembling-machine-3", "oil-refinery", "chemical-plant", "centrifuge", "lab"));
        defaultOrder.add(Arrays.asList("beacon", "speed-module", "speed-module-2", "speed-module-3", "effectivity-module", "effectivity-module-2", "effectivity-module-3", "productivity-module", "productivity-module-2", "productivity-module-3"));
        defaultOrder.add(Arrays.asList("rocket-silo", "satellite"));
        defaultOrder.add(Arrays.asList("wood", "coal", "stone", "iron-ore", "copper-ore", "uranium-ore", "raw-fish"));
        defaultOrder.add(Arrays.asList("iron-plate", "copper-plate", "solid-fuel", "steel-plate", "plastic-bar", "sulfur", "battery", "explosives"));
        defaultOrder.add(Arrays.asList("crude-oil-barrel", "heavy-oil-barrel", "light-oil-barrel", "lubricant-barrel", "petroleum-gas-barrel", "sulfuric-acid-barrel", "water-barrel"));
        defaultOrder.add(Arrays.asList("copper-cable", "iron-stick", "iron-gear-wheel", "empty-barrel", "electronic-circuit", "advanced-circuit", "processing-unit", "engine-unit", "electric-engine-unit", "flying-robot-frame", "rocket-control-unit", "low-density-structure", "rocket-fuel", "rocket-part", "nuclear-fuel", "uranium-235", "uranium-238", "uranium-fuel-cell", "used-up-uranium-fuel-cell"));
        defaultOrder.add(Arrays.asList("automation-science-pack", "logistic-science-pack", "military-science-pack", "chemical-science-pack", "production-science-pack", "utility-science-pack", "space-science-pack"));
        defaultOrder.add(Arrays.asList("pistol", "submachine-gun", "shotgun", "combat-shotgun", "rocket-launcher", "flamethrower", "land-mine"));
        defaultOrder.add(Arrays.asList("firearm-magazine", "piercing-rounds-magazine", "uranium-rounds-magazine", "shotgun-shell", "piercing-shotgun-shell", "cannon-shell", "explosive-cannon-shell", "uranium-cannon-shell", "explosive-uranium-cannon-shell", "artillery-shell", "rocket", "explosive-rocket", "atomic-bomb", "flamethrower-ammo"));
        defaultOrder.add(Arrays.asList("grenade", "cluster-grenade", "poison-capsule", "slowdown-capsule", "defender-capsule", "distractor-capsule", "destroyer-capsule"));
        defaultOrder.add(Arrays.asList("light-armor", "heavy-armor", "modular-armor", "power-armor", "power-armor-mk2"));
        defaultOrder.add(Arrays.asList("solar-panel-equipment", "fusion-reactor-equipment", "battery-equipment", "battery-mk2-equipment", "belt-immunity-equipment", "exoskeleton-equipment", "personal-roboport-equipment", "personal-roboport-mk2-equipment", "night-vision-equipment"));
        defaultOrder.add(Arrays.asList("energy-shield-equipment", "energy-shield-mk2-equipment", "personal-laser-defense-equipment", "discharge-defense-equipment", "discharge-defense-remote"));
        defaultOrder.add(Arrays.asList("stone-wall", "gate", "gun-turret", "laser-turret", "flamethrower-turret", "artillery-turret", "artillery-targeting-remote", "radar"));
    }

    public LogisticsRequest(Blueprint blueprint) {
        double minY = blueprint.getEntities().parallelStream().mapToDouble(it -> it.getPosition().getY()).min().getAsDouble();
        blueprint.getEntities().parallelStream().forEach(entity -> {
            entity.getControlBehavior().get("filters").forEach(filter -> {
                if (entity.getPosition().getY() == minY)
                    minimums.put(filter.get("signal").get("name").asText(), filter.get("count").asInt());
                else maximums.put(filter.get("signal").get("name").asText(), filter.get("count").asInt());
            });
        });
    }

    public Blueprint toBlueprint() {
        Blueprint blueprint = new Blueprint();

        List<Entity> entities = new ArrayList<>();
        for (int x = 0; x < defaultOrder.size(); x++) {
            Entity minimum = new Entity();
            minimum.setName("constant-combinator");
            minimum.setPosition(new Position(x + 0.5, 0.5));

            Entity maximum = new Entity();
            maximum.setName("constant-combinator");
            maximum.setPosition(new Position(x + 0.5, 4.5));

            ArrayNode minimumFilters = JsonUtils.MAPPER.createArrayNode();
            ArrayNode maximumFilters = JsonUtils.MAPPER.createArrayNode();

            List<String> items = defaultOrder.get(x);
            for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
                String item = items.get(itemIndex);
                ObjectNode signal = JsonUtils.MAPPER.createObjectNode();
                signal.put("type", "item");
                signal.put("name", item);

                if (minimums.containsKey(item)) {
                    ObjectNode filter = JsonUtils.MAPPER.createObjectNode();
                    filter.set("signal", signal);
                    filter.put("count", minimums.get(item));
                    filter.put("index", itemIndex + 1);

                    minimumFilters.add(filter);
                }
                if (maximums.containsKey(item)) {
                    ObjectNode filter = JsonUtils.MAPPER.createObjectNode();
                    filter.set("signal", signal);
                    filter.put("count", maximums.get(item));
                    filter.put("index", itemIndex + 1);

                    maximumFilters.add(filter);
                }
            }

            if (!minimumFilters.isEmpty()) {
                ObjectNode controlBehavior = JsonUtils.MAPPER.createObjectNode();
                controlBehavior.set("filters", minimumFilters);
                minimum.setControlBehavior(controlBehavior);

                minimum.setEntityNumber(entities.size() + 1);
                entities.add(minimum);
            }

            if (!maximumFilters.isEmpty()) {
                ObjectNode controlBehavior = JsonUtils.MAPPER.createObjectNode();
                controlBehavior.set("filters", maximumFilters);
                maximum.setControlBehavior(controlBehavior);

                maximum.setEntityNumber(entities.size() + 1);
                entities.add(maximum);
            }
        }

        blueprint.setEntities(entities);
        return blueprint;
    }
}