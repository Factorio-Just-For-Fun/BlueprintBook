package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Connection {
    @JsonProperty("1")
    private ConnectionPoint one;

    @JsonProperty("2")
    private ConnectionPoint two;

    @JsonProperty("Cu0")
    private List<JsonNode> Cu0;

    @JsonProperty("Cu1")
    private List<JsonNode> Cu1;
}
