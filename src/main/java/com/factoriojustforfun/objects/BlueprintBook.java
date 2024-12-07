package com.factoriojustforfun.objects;

import com.factoriojustforfun.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BlueprintBook {
    private final String item = "blueprint-book";
    private String label;

    @JsonProperty("label_color")
    private Color labelColor;

    private List<Icon> icons;
    private String description;

    private List<BlueprintBookEntry> blueprints = new ArrayList<>();

    @JsonProperty("active_index")
    private int activeIndex;

    private final long version = JsonUtils.VERSION;
}
