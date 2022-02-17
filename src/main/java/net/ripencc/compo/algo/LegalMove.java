package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Decision;
import net.ripencc.compo.dto.Snake;
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
        return getDirectionsTowardsPoint(battle, battle.getBoard().getCenter())
                .collect(Collectors.toList());
    }

    public Stream<Decision> getDirectionsTowardsPoint(Battle battle, Point point) {
        Snake you = battle.getYou();
        Set<Point> legalPositions = getLegalPositions(battle, you.getHead());

        return Utils.MOVES.parallelStream().map(move -> {
                    Point nextPoint = battle.getYou().moveHead(move);
                    return Decision.builder()
                            .direction(move)
                            .point(nextPoint)
                            .legal(legalPositions.contains(nextPoint))
                            .build();
                })
                .sorted(Comparator.comparingInt(m -> (int) m.getPoint().distance(point)))
                .filter(Decision::isLegal)
                // see if we can still move after this move at all
                .filter(d -> {
                    Set<Point> positions = getLegalPositions(battle, d.getPoint());
                    return !positions.isEmpty() ||
                            battle.getBoard().getOpponentsHeads(you).parallelStream()
                                    .noneMatch(positions::contains);
                });
    }

    private Set<Point> getLegalPositions(Battle battle, Point head) {
        Board board = battle.getBoard();
        Set<Point> positions = utils.getMoves(head);
        Set<Point> occupied = board.getSnakePoints();
        occupied.addAll(utils.getWall(board.getWidth(), board.getHeight()));

        positions.removeAll(occupied);
        return positions;
    }
}
