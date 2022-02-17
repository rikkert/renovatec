package net.ripencc.compo.dto;

import lombok.Builder;
import lombok.Value;

import java.awt.*;

@Builder
@Value
public class Decision {
    private Point point;
    private Move.Direction direction;
    private boolean legal;
}
