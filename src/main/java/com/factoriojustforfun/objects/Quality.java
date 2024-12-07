package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Quality {
    @JsonProperty("normal") NORMAL,
    @JsonProperty("uncommon") UNCOMMON,
    @JsonProperty("rare") RARE,
    @JsonProperty("epic") EPIC,
    @JsonProperty("legendary") LEGENDARY
}
