package com.github.hanseter.snake;

import javafx.geometry.Point2D;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBot implements SnakeController {
    private Direction direction;

    @Override
    public void onViewChanged(Point2D headPos, double width, double height, List<List<Point2D>> visibleLines) {
    }

    @Override
    public Direction getDirection() {
        if (ThreadLocalRandom.current().nextInt(10) > 8) {
            System.out.println("Choosing new direction");
            int randomDirectionIndex = ThreadLocalRandom.current().nextInt(Direction.values().length);
            direction = Direction.values()[randomDirectionIndex];
        }
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
