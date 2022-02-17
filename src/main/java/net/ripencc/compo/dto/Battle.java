package net.ripencc.compo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Battle {
    private Game game;
    private int turn;
    private Board board;
    private Snake you;
}
