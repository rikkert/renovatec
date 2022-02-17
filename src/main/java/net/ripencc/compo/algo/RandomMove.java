package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Decision;
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

    public Decision getNextDirection(Battle battle) {
        List<Decision> legalMoves = legalMove.getNextDirections(battle);
        Collections.shuffle(legalMoves);

        if (legalMoves.isEmpty()) {
            // nowhere to go
            return Decision.builder()
                    .direction(left)
                    .legal(true)
                    .point(battle.getYou().moveHead(left))
                    .build();
        } else {
            return legalMoves.get(0);
        }
    }
}
