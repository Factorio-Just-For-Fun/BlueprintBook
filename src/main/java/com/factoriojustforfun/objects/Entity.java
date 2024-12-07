package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Entity {
    // General Fields
    @JsonProperty("entity_number")
    private int entityNumber;
    protected String name;
    private Position position;
    private Quality quality;

    // Used in
    // Used for logistic generator
    @JsonProperty("control_behavior")
    private JsonNode controlBehavior;

    // Used for train name fixer
    private Color color;
    private String station;

    // Unknown Fields
    @JsonIgnore
    private Map<String, JsonNode> unknownFields = new HashMap<>();

    @JsonAnyGetter
    public Map<String, JsonNode> otherFields() {
        return unknownFields;
    }

    @JsonAnySetter
    public void setOtherField(String name, JsonNode value) {
        unknownFields.put(name, value);
    }
}
