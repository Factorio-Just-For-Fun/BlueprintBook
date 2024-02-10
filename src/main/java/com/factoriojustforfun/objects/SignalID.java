package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class SignalID {
    @NonNull private String name;

    private String backup;

    @NonNull private Type type;

    public enum Type {
        @JsonProperty("item")
        ITEM,

        @JsonProperty("fluid")
        FLUID,

        @JsonProperty("virtual")
        VIRTUAL
    }
}
