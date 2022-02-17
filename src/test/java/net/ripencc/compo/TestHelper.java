package net.ripencc.compo;

import net.ripencc.compo.dto.Snake;

import java.awt.*;
import java.util.List;

public class TestHelper {
    public static Snake otherSnake() {
        return Snake.builder()
            .head(new Point(1, 1))
            .body(List.of(new Point(1, 0), new Point(1, 1)))
            .build();
    }

}
