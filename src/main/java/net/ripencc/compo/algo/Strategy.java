package net.ripencc.compo.algo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static net.ripencc.compo.dto.Move.Direction.up;

@Component
public class Strategy {

    private Utils utils;
    private RandomMove randomAlgo;
    private FindFood findFood;

    @Autowired
    public Strategy(Utils utils, RandomMove randomAlgo, FindFood findFood) {
        this.utils = utils;
        this.randomAlgo = randomAlgo;
        this.findFood = findFood;
    }

    public Move determineNextMove(Battle battle) {
        Board board = battle.getBoard();

        List<Point> foodDirection = findFood.findClosest(battle);

        return Move.builder()
                .direction(randomAlgo.getNextDirection(battle))
                .build();
    }
}
