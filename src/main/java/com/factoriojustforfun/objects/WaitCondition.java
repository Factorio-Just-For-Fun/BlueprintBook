package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class WaitCondition {
    private Type type;

    @JsonProperty("compare_type")
    private CompareType compareType;

    private long ticks;

    private JsonNode condition;

    public enum Type {
        @JsonProperty("time") TIME,
        @JsonProperty("inactivity") INACTIVITY,
        @JsonProperty("full") FULL_CARGO,
        @JsonProperty("empty") EMPTY_CARGO,
        @JsonProperty("item_count") ITEM_COUNT,
        @JsonProperty("circuit") CIRCUIT,
        @JsonProperty("robots_inactive") ROBOTS_INACTIVE,
        @JsonProperty("fluid_count") FLUID_COUNT,
        @JsonProperty("passenger_present") PASSENGER_PRESENT,
        @JsonProperty("passenger_not_present") PASSENGER_NOT_PRESENT
    }

    private enum CompareType {
        @JsonProperty("and") AND,
        @JsonProperty("or") OR;
    }
}
