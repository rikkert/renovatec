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
        NORMAL
    }

    private LegalMove legalMove;
    private RandomMove randomAlgo;
    private FindFood findFood;
    private FindTail findTail;
    private Attack attack;

    @Autowired
    public Strategy(LegalMove legalMove, RandomMove randomAlgo, FindFood findFood, FindTail findTail, Attack attack) {
        this.legalMove = legalMove;
        this.randomAlgo = randomAlgo;
        this.findFood = findFood;
        this.findTail = findTail;
        this.attack = attack;
    }

    public Move determineNextMove(Battle battle) {
        Move.MoveBuilder result = Move.builder();
        State state = getCurrentState(battle);

        switch (state) {
            case ANGRY -> result.direction(
                    attack.findClosest(battle).findFirst()
                            .map(Decision::getDirection)
                            .orElse(randomAlgo.getNextDirection(battle)));
            case NORMAL -> result.direction(
                    findTail.getNextDirections(battle)
                            .map(Decision::getDirection)
                            .findFirst()
                            .orElse(randomAlgo.getNextDirection(battle)));
            case HUNGRY -> result.direction(findFood(battle).getDirection());
        }

        return result.build();
    }

    private State getCurrentState(Battle battle) {
        Snake you = battle.getYou();

        if (battle.getBoard().getSnakes().parallelStream()
                .anyMatch(s -> s.getLength() < you.getLength())) {
            return State.ANGRY;
        }

        if (battle.getBoard().getLongestSnakeLength(you) >= you.getLength()
            || you.getHealth() < 25)
            return State.HUNGRY;

        return State.NORMAL;
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
