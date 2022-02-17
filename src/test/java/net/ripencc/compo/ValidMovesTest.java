package net.ripencc.compo;

import net.ripencc.compo.dto.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Set;

import static net.ripencc.compo.dto.Move.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidMovesTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = Board.builder().width(3).height(3).build();
    }

    @Test
    void should_avoid_bottom_left() {
        var valid = Set.of(up, right);
        assertEquals(valid, board.valid_moves(new Point()));
    }

    @Test
    void should_avoid_top_right() {
        var valid = Set.of(down, left);
        assertEquals(valid, board.valid_moves(new Point(2, 2)));
    }
}
