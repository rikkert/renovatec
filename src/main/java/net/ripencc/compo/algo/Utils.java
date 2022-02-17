package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Move;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static net.ripencc.compo.dto.Move.Direction.*;

@Component
public class Utils {

    public static final Set<Move.Direction> MOVES = Set.of(up, down, left, right);

    @Cacheable("walls")
    public Set<Point> getWall(int width, int height) {
        var topAndBottom = IntStream.range(0, width)
                .mapToObj(x -> new Point(x, -1))
                .flatMap(p -> Stream.of(p, new Point(p.x, height)));
        var leftAndRight = IntStream.range(0, height)
                .mapToObj(y -> new Point(-1, y))
                .flatMap(p -> Stream.of(p, new Point(width, p.y)));

        return Stream.concat(leftAndRight, topAndBottom).collect(Collectors.toSet());
    }

    public Set<Point> getMoves(Point head) {
        Set<Point> moves = new HashSet<>(4);
        moves.add(new Point(head.x + 1, head.y));
        moves.add(new Point(head.x - 1, head.y));
        moves.add(new Point(head.x, head.y + 1));
        moves.add(new Point(head.x, head.y - 1));

        return moves;
    }


}
