package com.factoriojustforfun.objects.bookentries;

import com.factoriojustforfun.objects.Blueprint;
import com.factoriojustforfun.objects.BlueprintBookEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BlueprintItem extends BlueprintBookEntry {
    private Blueprint blueprint;
}