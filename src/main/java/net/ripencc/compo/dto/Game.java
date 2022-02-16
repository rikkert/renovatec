package net.ripencc.compo.dto;

import lombok.Data;

@Data
public class Game {
    /**
     * {
     *   "id": "totally-unique-game-id",
     *   "ruleset": {
     *       "name": "standard",
     *       "version": "v1.2.3"
     *     },
     *   "timeout": 500,
     *   "source": "league"
     * }
     */
    private String id;
    private Integer timeout;
    private String source;
}
