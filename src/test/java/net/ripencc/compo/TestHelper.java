package net.ripencc.compo;

import net.ripencc.compo.dto.Snake;

import java.awt.*;
import java.util.List;

public class TestHelper {
    public static Snake otherSnake() {
        return Snake.builder()
            .head(new Point(1, 1))
            .body(List.of(new Point(1, 1), new Point(1, 0)))
            .length(2)
            .build();
    }

    public static Snake tallSnake() {
        return Snake.builder()
            .head(new Point(2, 2))
            .body(List.of(new Point(2, 2), new Point(2, 1), new Point(2, 0)))
            .length(3)
            .build();
    }

    public static Snake strongSnake() {
        return Snake.builder()
                .head(new Point(4, 4))
                .body(List.of(
                        new Point(4, 4),
                        new Point(4, 3),
                        new Point(4, 2),
                        new Point(4, 1),
                        new Point(4, 0)))
                .length(5)
                .build();
    }
}
