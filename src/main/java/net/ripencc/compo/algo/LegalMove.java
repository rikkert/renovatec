package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Decision;
import net.ripencc.compo.dto.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LegalMove {

    private final Utils utils;

    @Autowired
    public LegalMove(Utils utils) {
        this.utils = utils;
    }

    public List<Decision> getNextDirections(Battle battle) {
        return getDirectionsTowardsPoint(battle, battle.getYou().getHead())
                .collect(Collectors.toList());
    }

    public Stream<Decision> getDirectionsTowardsPoint(Battle battle, Point point) {
        Set<Point> legalPositions = getLegalPositions(battle);
        return Utils.MOVES.parallelStream().map(move -> {
                    Point nextPoint = moveHead(battle.getYou().getHead(), move);
                    return Decision.builder()
                            .direction(move)
                            .point(nextPoint)
                            .legal(legalPositions.contains(nextPoint))
                            .build();
                })
                .sorted(Comparator.comparingInt(m -> (int) m.getPoint().distance(point)))
                .filter(Decision::isLegal);
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
        return switch (direction) {
            case up -> new Point(head.x, head.y + 1);
            case down -> new Point(head.x, head.y - 1);
            case left -> new Point(head.x - 1, head.y);
            case right -> new Point(head.x + 1, head.y);
        };
    }
}
