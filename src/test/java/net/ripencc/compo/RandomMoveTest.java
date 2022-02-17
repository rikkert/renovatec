package net.ripencc.compo;

import net.ripencc.compo.algo.RandomMove;
import net.ripencc.compo.algo.Utils;
import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

import static net.ripencc.compo.dto.Move.Direction.up;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomMoveTest {

    private RandomMove subject;

    @BeforeEach
    void setup() {
        subject = new RandomMove(new Utils());
    }

    @Test
    void can_only_go_up() {
        var other = Snake.builder()
            .head(new Point(1, 1))
            .body(Arrays.asList(new Point(1, 0)))
            .build();
        var board = Board.builder()
            .width(3).height(3)
            .snakes(Arrays.asList(other))
            .build();
        var me = Snake.builder().head(new Point(0, 0)).build();

        var battle = Battle.builder()
            .board(board)
            .you(me)
            .build();
        var result = subject.getNextDirection(battle);
        assertEquals(up, result);
    }

}
