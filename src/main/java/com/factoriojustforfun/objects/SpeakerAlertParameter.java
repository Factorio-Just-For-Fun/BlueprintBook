package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpeakerAlertParameter {
    @JsonProperty("show_alert")
    private boolean showAlert;

    @JsonProperty("show_on_map")
    private boolean showOnMap;

    @JsonProperty("icon_signal_id")
    private SignalID iconSignalId;

    @JsonProperty("alert_message")
    private String alertMessage;
}
