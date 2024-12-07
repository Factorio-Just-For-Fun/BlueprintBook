package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Color {
    @JsonProperty("r")
    private double red;

    @JsonProperty("g")
    private double green;

    @JsonProperty("b")
    private double blue;

    @JsonProperty("a")
    private double alpha;

    public Color(int red, int blue, int green, int alpha) {
        this(red / 255f, blue / 255f, green / 255f, alpha / 255f);
    }
}
