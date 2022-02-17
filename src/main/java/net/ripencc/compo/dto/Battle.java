package net.ripencc.compo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Builder
@Data
public class Battle {
    private Game game;
    private int turn;
    private Board board;
    private Snake you;

    @Override
    public String toString() {
        var grid = new int[board.getWidth()][board.getHeight()];
        board.getSnakePoints()
            .forEach(point -> grid[point.y][point.x] = 1);

        var lines = new ArrayList<String>();
        for (int y = board.getHeight() - 1; y >= 0; y--) {
            var line = new StringBuffer();
            for (int x = 0; x < board.getWidth(); x++) {
                var cell = grid[y][x];
                switch (cell) {
                    case 0:
                        line.append("·");
                        break;
                    case 1:
                        line.append("S");
                        break;
                }

            }
            lines.add(line.toString());
        }
        lines.add("");
        return String.join("\n", lines);
    }
}
