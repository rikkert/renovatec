package net.ripencc.compo.algo;

import net.ripencc.compo.Controller;
import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Decision;
import net.ripencc.compo.dto.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static net.ripencc.compo.dto.Move.Direction.left;

@Service
public class Strategy {

    private final Logger logger = LoggerFactory.getLogger(Strategy.class);
    private final Decision defaultDecision = Decision.builder().direction(left).build();

    private LegalMove legalMove;
    private RandomMove randomAlgo;
    private FindFood findFood;

    @Autowired
    public Strategy(LegalMove legalMove, RandomMove randomAlgo, FindFood findFood) {
        this.legalMove = legalMove;
        this.randomAlgo = randomAlgo;
        this.findFood = findFood;
    }

    public Move determineNextMove(Battle battle) {
        Decision decision = findFood.findClosest(battle)
                .flatMap(p -> legalMove.getDirectionsTowardsPoint(battle, p))
                .filter(Decision::isLegal)
                .findFirst()
                .orElse(defaultDecision);

        return Move.builder()
                .direction(decision.getDirection())
                .build();
    }
}
