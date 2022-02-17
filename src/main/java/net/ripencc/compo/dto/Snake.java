package net.ripencc.compo.dto;

import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.util.List;

@Builder
@Data
public class Snake {
    /**
     * {
     *   "id": "totally-unique-snake-id",
     *   "name": "Sneky McSnek Face",
     *   "health": 54,
     *   "body": [
     *     {"x": 0, "y": 0},
     *     {"x": 1, "y": 0},
     *     {"x": 2, "y": 0}
     *   ],
     *   "latency": "123",
     *   "head": {"x": 0, "y": 0},
     *   "length": 3,
     *   "shout": "why are we shouting??",
     *   "squad": "1",
     *   "customizations":{
     *                "color":"#26CF04",
     *                "head":"smile",
     *                "tail":"bolt"
     *             }
     * }
     */
    private String id;
    private String name;
    private int health;
    private List<Point> body;
    private String latency;
    private Point head;
    private int length;
    private String shout;
    private String squad;
}
