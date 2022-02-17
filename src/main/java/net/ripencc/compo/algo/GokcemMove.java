package net.ripencc.compo.algo;

import net.ripencc.compo.Gokcem;
import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Decision;
import net.ripencc.compo.dto.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static net.ripencc.compo.dto.Move.Direction.left;

@Component
public class GokcemMove {

    private Gokcem gokcem;

    @Autowired
    public GokcemMove(Gokcem gokcem) {
        this.gokcem = gokcem;
    }

    public Move.Direction getNextDirection(Battle battle) {
       var x =  gokcem.move(battle);

        return x.getDirection();
    }
}
