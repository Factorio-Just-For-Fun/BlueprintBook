package com.factoriojustforfun.objects;

import com.factoriojustforfun.objects.bookentries.BlueprintBookItem;
import com.factoriojustforfun.objects.bookentries.BlueprintItem;
import com.factoriojustforfun.objects.bookentries.DeconstructionPlannerItem;
import com.factoriojustforfun.objects.bookentries.UpgradePlannerItem;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(BlueprintItem.class),
        @JsonSubTypes.Type(BlueprintBookItem.class),
        @JsonSubTypes.Type(DeconstructionPlannerItem.class),
        @JsonSubTypes.Type(UpgradePlannerItem.class)
})
public abstract class BlueprintBookEntry {
    private int index;
}
