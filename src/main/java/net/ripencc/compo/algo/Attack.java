package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Decision;
import net.ripencc.compo.dto.Snake;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class Attack {
    LegalMove legalMove;

    public Attack(LegalMove legalMove) {
        this.legalMove = legalMove;
    }

    public Stream<Decision> findClosest(Battle battle) {
        Snake you = battle.getYou();
        Optional<Point> point = battle.getBoard().getSnakes().parallelStream()
                .filter(snake -> snake.getLength() < you.getLength())
                .map(Snake::getHead)
                .min(Comparator.comparingInt(p -> (int) p.distance(you.getHead())));

        if (point.isPresent()) {
            return legalMove.getDirectionsTowardsPoint(battle, point.get());
        } else return Stream.empty();
    }
}
