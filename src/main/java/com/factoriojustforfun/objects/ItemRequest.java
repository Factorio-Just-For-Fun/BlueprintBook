package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ItemRequest {
    private ItemRequestId id;
    private ItemRequestItems items;

    @Data
    private static class ItemRequestId {
        private String name;
    }

    @Data
    public static class ItemRequestItems {
        @JsonProperty("in_inventory")
        private List<ItemRequestItem> inInventory;
    }

    @Data
    public static class ItemRequestItem {
        private int inventory;
        private int stack;
    }
}
