package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConnectionData {
    @JsonProperty("entity_id")
    private int entityId;

    @JsonProperty("circuit_id")
    private int circuitId;
}
