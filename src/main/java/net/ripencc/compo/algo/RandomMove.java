package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.ripencc.compo.dto.Move.Direction.up;

@Component
public class RandomMove {

    private static final List<Move.Direction> MOVES = Stream.of(
            up, Move.Direction.down, Move.Direction.left, Move.Direction.right)
        .collect(Collectors.toList());

    private final Utils utils;

    @Autowired
    public RandomMove(Utils utils) {
        this.utils = utils;
    }

    public Move.Direction getNextDirection(Battle battle) {
        Collections.shuffle(MOVES);
        Set<Point> legalPositions = getLegalPositions(battle);
        Point head = battle.getYou().getHead();

        for (Move.Direction move : MOVES) {
            if (legalPositions.contains(moveHead(head, move))) {
                return move;
            }
        }

        // dead end
        return up;
    }

    private Set<Point> getLegalPositions(Battle battle) {
        Board board = battle.getBoard();
        Point head = battle.getYou().getHead();
        Set<Point> positions = utils.getMoves(head);
        Set<Point> occupied = utils.getSnakes(board);
        occupied.addAll(utils.getWall(board.getWidth(), board.getHeight()));

        positions.removeAll(occupied);
        return positions;
    }

    private Point moveHead(Point head, Move.Direction direction) {
        switch (direction) {
            case up:
                return new Point(head.x, head.y + 1);
            case down:
                return new Point(head.x, head.y - 1);
            case left:
                return new Point(head.x - 1, head.y);
            case right:
                return new Point(head.x + 1, head.y);
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }
}
