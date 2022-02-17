package net.ripencc.compo;

import net.ripencc.compo.dto.Board;
import net.ripencc.compo.dto.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.ripencc.compo.dto.Move.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

class ValidMovesTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = Board.builder().width(3).height(3).build();
    }

    Set<Move.Direction> valid_moves(Point position) {
        var result = new HashSet<Move.Direction>();
        if (position.x > 0) {
            result.add(left);
        }
        if (position.y > 0) {
            result.add(down);
        }
        if (position.x < board.getWidth() - 1) {
            result.add(right);
        }
        if (position.y < board.getHeight() - 1) {
            result.add(up);
        }
        return result;
    }

    @Test
    void should_avoid_bottom_left() {
        var valid = Set.of(up, right);
        assertEquals(valid, valid_moves(new Point()));
    }

    @Test
    void should_avoid_top_right() {
        var valid = Set.of(down, left);
        assertEquals(valid, valid_moves(new Point(2, 2)));
    }
}
