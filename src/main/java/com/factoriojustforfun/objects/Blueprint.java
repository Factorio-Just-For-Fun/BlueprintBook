package com.factoriojustforfun.objects;

import com.factoriojustforfun.utils.JsonUtils;
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
public class Blueprint {
    private final String item = "blueprint";

    private String label;

    @JsonProperty("label_color")
    private Color labelColor;

    private List<Entity> entities;

    private List<Icon> icons;

    private String description;

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
