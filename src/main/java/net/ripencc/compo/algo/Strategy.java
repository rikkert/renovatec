package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Random;
import java.util.Set;

import static net.ripencc.compo.dto.Move.Direction.up;

@Component
public class Strategy {

    private Utils utils;
    private RandomMove randomAlgo;

    @Autowired
    public Strategy(Utils utils, RandomMove randomAlgo) {
        this.utils = utils;
        this.randomAlgo = randomAlgo;
    }

    public Move determineNextMove(Battle battle) {
        Board board = battle.getBoard();

        return Move.builder()
                .direction(randomAlgo.getNextDirection(battle))
                .build();
    }
}
