package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InfinitySettings {
    @JsonProperty("remove_unfiltered_items")
    private boolean removeUnfilteredItems;

    private List<InfinityFilter> filters;

    private String name;
    private int percentage;
    private String temperature;
    private InfinityFilter.Mode mode;
}
