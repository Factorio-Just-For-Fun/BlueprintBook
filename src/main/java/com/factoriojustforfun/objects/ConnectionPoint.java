package com.factoriojustforfun.objects;

import lombok.Data;

import java.util.List;

@Data
public class ConnectionPoint {
    private List<ConnectionData> red;
    private List<ConnectionData> green;
}
