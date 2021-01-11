package com.github.hanseter.snake;

import javafx.geometry.Point2D;

import java.util.List;

public class HumanBot implements SnakeController{

    private Direction direction = null;

    @Override
    public void onViewChanged(Point2D headPos, double width, double height, List<List<Point2D>> visibleLines) {
        //human does not care
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
}
