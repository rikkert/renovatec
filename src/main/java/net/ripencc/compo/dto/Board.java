package net.ripencc.compo.dto;

import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static net.ripencc.compo.dto.Move.Direction.*;

@Builder
@Data
public class Board {
    /**
     * {
     * "height": 11,
     * "width": 11,
     * "food": [
     * {"x": 5, "y": 5},
     * {"x": 9, "y": 0},
     * {"x": 2, "y": 6}
     * ],
     * "hazards": [
     * {"x": 0, "y": 0},
     * {"x": 0, "y": 1},
     * {"x": 0, "y": 2}
     * ],
     * "snakes": [
     * {"id": "snake-one", ... },
     * {"id": "snake-two", ... },
     * {"id": "snake-three", ... }
     * ]
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


    public Integer getLongestSnakeLength() {
        return snakes.parallelStream().map(snake -> snake.getBody().size()).reduce(0, Math::max);
    }

    public Integer getLongestSnakeLength(Snake you) {
        return snakes.parallelStream()
            .filter(snake1 -> !snake1.getHead().equals(you.getHead()))
            .map(snake -> snake.getBody().size()).reduce(0, Math::max);
    }

    public Set<Point> getSnakePoints() {
        return snakes.stream()
            .flatMap(snake -> snake.getBody().stream().limit(snake.getLength() - 1))
            .collect(Collectors.toSet());
    }

    public Point getCenter() {
        return new Point(width / 2, height / 2);
    }

    public Set<Point> getOpponentsHeads(Snake you) {
        return snakes.stream()
            .filter(snake -> !snake.getId().equals(you.getId()))
            .filter(snake -> you.getLength() < snake.getLength())
            .map(Snake::getHead)
            .collect(Collectors.toSet());
    }
}
