package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    @JsonInclude
    private double x;

    @JsonInclude
    private double y;
}
