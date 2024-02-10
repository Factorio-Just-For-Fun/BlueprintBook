package com.factoriojustforfun.objects.bookentries;

import com.factoriojustforfun.objects.BlueprintBookEntry;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpgradePlannerItem extends BlueprintBookEntry {
    @JsonProperty("upgrade_planner")
    private JsonNode upgradePlanner;
}
