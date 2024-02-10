package com.factoriojustforfun.objects;

import com.factoriojustforfun.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Blueprint {
    private final String item = "blueprint";

    private String label;

    @JsonProperty("label_color")
    private Color labelColor;

    private List<Entity> entities;

    private List<Tile> tiles;

    private List<Icon> icons;

    private List<Schedule> schedules;

    private String description;

    @JsonProperty("snap-to-grid")
    private Position snapToGrid;

    @JsonProperty("absolute-snapping")
    private boolean absoluteGrid;

    @JsonProperty("position-relative-to-grid")
    private Position positionRelativeToGrid;

    private final long version = JsonUtils.VERSION;
}
