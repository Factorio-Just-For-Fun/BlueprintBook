package com.factoriojustforfun.objects;

import lombok.Data;

import java.util.List;

@Data
public class Inventory {
    private int bar;
    private List<ItemFilter> filters;
}
