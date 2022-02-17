package net.ripencc.compo;

import net.ripencc.compo.dto.Move;

public class MovePoint {
    private Move.Direction direction;
    private double value;

    public MovePoint(Move.Direction direction, double value) {
        this.direction = direction;
        this.value = value;
    }

    public void setDirection(Move.Direction direction) {
        this.direction = direction;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Move.Direction getDirection() {
        return direction;
    }

    public double getValue() {
        return value;
    }

}
