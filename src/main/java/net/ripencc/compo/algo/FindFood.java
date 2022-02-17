package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Comparator;
import java.util.stream.Stream;

@Component
public class FindFood {

    public Stream<Point> findClosest(Battle battle) {
        Board board = battle.getBoard();
        Point head = battle.getYou().getHead();

        return board.getFood()
                .parallelStream()
                .sorted(Comparator.comparingInt(p -> (int) p.distance(head)));
    }
}
