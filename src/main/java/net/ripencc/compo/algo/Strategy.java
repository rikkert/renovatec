package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Decision;
import net.ripencc.compo.dto.Move;
import net.ripencc.compo.dto.Snake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static net.ripencc.compo.dto.Move.Direction.left;

@Service
public class Strategy {

    private final Logger logger = LoggerFactory.getLogger(Strategy.class);
    private final Decision defaultDecision = Decision.builder().direction(left).build();
    private enum State {
        ANGRY,
        HUNGRY,
        FULL,
        NORMAl
    }

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
        Move.MoveBuilder result = Move.builder();
        State state = getCurrentState(battle);

        switch (state) {
            case NORMAl:
                result.direction(randomAlgo.getNextDirection(battle));
                break;
            case HUNGRY:
                result.direction(findFood(battle).getDirection());
                break;
        }

        return result.build();
    }

    private State getCurrentState(Battle battle) {
        Snake you = battle.getYou();
        if (you.getHealth() < 25) {
            return State.HUNGRY;
        }

        return State.NORMAl;
    }

    private Decision findFood(Battle battle) {
        Decision.DecisionBuilder noFood = Decision.builder();
        return findFood.findClosest(battle)
                .flatMap(p -> legalMove.getDirectionsTowardsPoint(battle, p))
                .filter(Decision::isLegal)
                .findFirst()
                .orElse(noFood.direction(randomAlgo.getNextDirection(battle)).build());
    }
}
