package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Decision;
import net.ripencc.compo.dto.Snake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.stream.Stream;

@Component
public class FindTail {

    private LegalMove legalMove;

    @Autowired
    public FindTail(LegalMove legalMove) {
        this.legalMove = legalMove;
    }

    public Stream<Decision> getNextDirections(Battle battle) {
        Snake snake = battle.getYou();
        Point tail = snake.getBody().get(snake.getLength() - 1);

        return legalMove.getDirectionsTowardsPoint(battle, tail);
    }
}
