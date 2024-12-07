package com.factoriojustforfun.objects;

import lombok.Data;

import java.util.List;

@Data
public class Schedule {
    private List<ScheduleRecord> schedule;
    private List<Integer> locomotives;
}
