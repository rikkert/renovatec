package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Decision;
import net.ripencc.compo.dto.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static net.ripencc.compo.dto.Move.Direction.left;

@Component
public class RandomMove {

    private LegalMove legalMove;

    @Autowired
    public RandomMove(LegalMove legalMove) {
        this.legalMove = legalMove;
    }

    public Move.Direction getNextDirection(Battle battle) {
        List<Decision> legalMoves = legalMove.getNextDirections(battle);
        Collections.shuffle(legalMoves);

        if (legalMoves.isEmpty()) {
            // nowhere to go
            return left;
        } else {
            return legalMoves.get(0).getDirection();
        }
    }
}
