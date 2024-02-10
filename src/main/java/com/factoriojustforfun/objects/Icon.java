package com.factoriojustforfun.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Icon {
    private SignalID signal;
    private int index;
}
