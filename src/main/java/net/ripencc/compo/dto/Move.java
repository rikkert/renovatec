package net.ripencc.compo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
@JsonSerialize()
public class Move {
    public enum Direction {
        up, down, left, right
    }

    @JsonProperty("move")
    private Direction direction;
    private String shout;
}
