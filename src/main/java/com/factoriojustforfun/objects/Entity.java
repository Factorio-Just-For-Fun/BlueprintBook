package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class Entity {
    @JsonProperty("entity_number")
    private int entityNumber;

    protected String name;

    private Position position;

    private int direction;
    private int orientation;

    private Connection connections;
    private List<Integer> neighbours;

    @JsonProperty("control_behavior")
    private JsonNode controlBehavior;

    private ItemRequest items;

    private String recipe;


    private int bar;

    private Inventory inventory;

    @JsonProperty("infinity_settings")
    private InfinitySettings infinitySettings;

    private Type type;

    @JsonProperty("input_priority")
    private Priority inputPriority;

    @JsonProperty("output_priority")
    private Priority outputPriority;

    private String filter;

    private List<ItemFilter> filters;

    @JsonProperty("filter_mode")
    private FilterMode filterMode;

    @JsonProperty("override_stack_size")
    private int overrideStackSize;

    @JsonProperty("drop_position")
    private Position dropPosition;

    @JsonProperty("pickup_position")
    private Position pickupPosition;

    @JsonProperty("request_filters")
    private List<LogisticFilter> requestFilters;

    @JsonProperty("request_from_buffers")
    private boolean requestFromBuffers;

    private SpeakerParameter parameters;

    @JsonProperty("alert_parameters")
    private SpeakerAlertParameter alertParameters;

    @JsonProperty("auto_launch")
    private boolean autoLaunch;

    private int variation;

    private Color color;

    private String station;

    @JsonProperty("manual_trains_limit")
    private int manualTrainsLimit;

    @JsonProperty("switch_state")
    private boolean switchState;

    private JsonNode tags;

    // Undocumented, for power production

    @JsonProperty("buffer_size")
    private long bufferSize;
    @JsonProperty("power_production")
    private long powerProduction;
    @JsonProperty("power_usage")
    private long powerUsage;

    public enum FilterMode {
        @JsonProperty("whitelist") WHITELIST,
        @JsonProperty("blacklist") BLACKLIST
    }

    public enum Priority {
        @JsonProperty("left") LEFT,
        @JsonProperty("right") RIGHT
    }

    public enum Type {
        @JsonProperty("input") INPUT,
        @JsonProperty("output") OUTPUT
    }
}
