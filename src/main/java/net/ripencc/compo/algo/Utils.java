package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Board;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class Utils {

    @Cacheable("walls")
    public Set<Point> getWall(Board board) {
        var topAndBottom = IntStream.range(0, board.getWidth())
                .mapToObj(x -> new Point(x, -1))
                .flatMap(p -> Stream.of(p, new Point(p.x, board.getHeight())));
        var leftAndRight = IntStream.range(0, board.getHeight())
                .mapToObj(y -> new Point(-1, y))
                .flatMap(p -> Stream.of(p, new Point(board.getWidth(), p.y)));

        return Stream.concat(leftAndRight, topAndBottom).collect(Collectors.toSet());
    }
}
