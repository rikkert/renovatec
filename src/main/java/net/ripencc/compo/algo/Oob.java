package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static net.ripencc.compo.dto.Move.Direction.*;

// Simple out-of-bounds algorithm
@Service
public class Oob {

    private final Utils utils;

    @Autowired
    public Oob(Utils utils) {
        this.utils = utils;
    }

    public Move move(Battle battle) {
        var result = Move.builder();

        Board board = battle.getBoard();
        Point head = battle.getYou().getHead();
        Set<Point> occupied = battle.getBoard().getSnakes().stream()
                .flatMap(snake -> snake.getBody().stream())
                .collect(Collectors.toSet());
        occupied.addAll(utils.getWall(board.getWidth(), board.getHeight()));

        if (!occupied.contains(new Point(head.x, head.y +1)))
            result.direction(up);
        else if (!occupied.contains(new Point(head.x + 1, head.y)))
            result.direction(right);
        else if (!occupied.contains(new Point(head.x, head.y - 1)))
            result.direction(down);
        else if (!occupied.contains(new Point(head.x - 1, head.y)))
            result.direction(left);

        return result.build();
    }

}
