package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignalID {
    private String name;
    private String backup;
    private Type type;
    private Quality quality;

    public enum Type {
        @JsonProperty("item")
        ITEM,

        @JsonProperty("fluid")
        FLUID,

        @JsonProperty("virtual")
        VIRTUAL,

        @JsonProperty("space-location")
        SPACE_LOCATION,

        @JsonProperty("recipe")
        RECIPE,

        @JsonProperty("quality")
        QUALITY
    }
}
