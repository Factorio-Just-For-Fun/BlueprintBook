package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleRecord {
    private String station;

    @JsonProperty("wait_conditions")
    private List<WaitCondition> waitConditions;
}
