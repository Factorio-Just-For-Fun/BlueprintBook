package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InfinityFilter extends LogisticFilter {
    private Mode mode;

    public enum Mode {
        @JsonProperty("at-least") AT_LEAST,
        @JsonProperty("at-most") AT_MOST,
        @JsonProperty("exactly") EXACTLY
    }
}
