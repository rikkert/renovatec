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
    public Set<Point> getWall(int width, int height) {
        var topAndBottom = IntStream.range(0, width)
                .mapToObj(x -> new Point(x, -1))
                .flatMap(p -> Stream.of(p, new Point(p.x, height)));
        var leftAndRight = IntStream.range(0, height)
                .mapToObj(y -> new Point(-1, y))
                .flatMap(p -> Stream.of(p, new Point(width, p.y)));

        return Stream.concat(leftAndRight, topAndBottom).collect(Collectors.toSet());
    }
}
