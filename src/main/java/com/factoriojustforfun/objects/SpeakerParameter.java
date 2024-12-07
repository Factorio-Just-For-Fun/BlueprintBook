package com.factoriojustforfun.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpeakerParameter {
    @JsonProperty("playback_volume")
    private double playbackVolume;

    @JsonProperty("playback_globally")
    private boolean playbackGlobally;

    @JsonProperty("allow_polyphony")
    private boolean allowPolyphony;
}
