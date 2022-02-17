package net.ripencc.compo.dto;

import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.ripencc.compo.dto.Move.Direction.*;

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

    public Set<Move.Direction> valid_moves(Point position) {
        var result = new HashSet<Move.Direction>();
        if (position.x > 0) {
            result.add(left);
        }
        if (position.y > 0) {
            result.add(down);
        }
        if (position.x < width - 1) {
            result.add(right);
        }
        if (position.y < height - 1) {
            result.add(up);
        }
        return result;
    }

}
