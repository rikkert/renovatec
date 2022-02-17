package net.ripencc.compo;

import net.ripencc.compo.dto.Snake;

import java.awt.*;
import java.util.List;

public class TestHelper {
    public static Snake otherSnake() {
        return Snake.builder()
            .head(new Point(1, 1))
            .body(List.of(new Point(1, 1), new Point(1, 0)))
            .build();
    }

    public static Snake tallSnake() {
        return Snake.builder()
            .head(new Point(2, 2))
            .body(List.of(new Point(2, 2), new Point(2, 1), new Point(2, 0)))
            .build();
    }

}
