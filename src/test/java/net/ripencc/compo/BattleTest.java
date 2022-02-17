package net.ripencc.compo;

import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BattleTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = Board.builder().width(3).height(3).build();
    }

    @Test
    void should_render_empty_battle() {
        var board = Board.builder()
            .width(3).height(3)
            .snakes(List.of())
            .build();

        var battle = Battle.builder()
            .board(board)
            .build();

        assertEquals("""
            ···
            ···
            ···
            """, battle.toString());
    }

    @Test
    void should_render_snakes() {
        var other = TestHelper.otherSnake();
        var tall = TestHelper.tallSnake();

        var board = Board.builder()
            .width(3).height(3)
            .snakes(List.of(other))
            .build();

        var battle = Battle.builder()
            .board(board)
            .build();

        assertEquals("""
            ···
            ·S·
            ·S·
            """, battle.toString());
    }

    @Test
    void should_find_tallest_snake() {
        var other = TestHelper.otherSnake();
        var tall = TestHelper.tallSnake();

        var board = Board.builder()
            .width(3).height(3)
            .snakes(List.of(other, tall))
            .build();

        var battle = Battle.builder()
            .board(board)
            .build();
        var longest = battle.getBoard().getLongestSnakeLength();

        assertEquals(3, longest);
    }


}
