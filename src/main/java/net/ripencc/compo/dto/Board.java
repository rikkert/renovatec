package net.ripencc.compo.dto;

import lombok.Builder;
import lombok.Data;

import java.awt.Point;
import java.util.List;

@Builder
@Data
public class Board {
    /**
     * {
     *   "height": 11,
     *   "width": 11,
     *   "food": [
     *     {"x": 5, "y": 5},
     *     {"x": 9, "y": 0},
     *     {"x": 2, "y": 6}
     *   ],
     *   "hazards": [
     *     {"x": 0, "y": 0},
     *     {"x": 0, "y": 1},
     *     {"x": 0, "y": 2}
     *   ],
     *   "snakes": [
     *     {"id": "snake-one", ... },
     *     {"id": "snake-two", ... },
     *     {"id": "snake-three", ... }
     *   ]
     * }
     */

    private int height;
    private int width;
    private List<Point> food;
    private List<Point> hazards;
    private List<Snake> snakes;
}
