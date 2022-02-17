package net.ripencc.compo.dto;

import net.ripencc.compo.TestHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    void snakePointsShouldNotIncludeTails() {
        Board subject = Board.builder()
                .snakes(List.of(TestHelper.otherSnake(), TestHelper.tallSnake()))
                .build();

        assertEquals(3, subject.getSnakePoints().size());
    }
}