package com.github.hanseter.snake;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Snake {

    private final List<Point2D> points = new ArrayList<>();
    private double velocity = 1;
    private Direction direction = Direction.values()[ThreadLocalRandom.current().nextInt(4)];
    private Direction nextDirection = direction;
    private int length = 100;

    private final SnakeController controller;

    public Snake(SnakeController controller) {
        this.controller = controller;
        controller.setDirection(direction);
    }

    public List<Point2D> getPoints() {
        return points;
    }

    private void setDirection(Direction newDirection) {
        if ((this.direction == Direction.UP && newDirection == Direction.DOWN)
                || (this.direction == Direction.DOWN && newDirection == Direction.UP)
                || (this.direction == Direction.LEFT && newDirection == Direction.RIGHT)
                || (this.direction == Direction.RIGHT && newDirection == Direction.LEFT)) {
            return;
        }
        System.out.println(newDirection);
        this.nextDirection = newDirection;
    }

    public void onGameStarts(int width, int height) {
        var startX = ThreadLocalRandom.current().nextInt(width - length * 2);
        var startY = ThreadLocalRandom.current().nextInt(height - length * 2);
        var startPoint = new Point2D(startX, startY);
        points.add(startPoint);
        points.add(startPoint.add(direction.getVector().multiply(length)));
    }

    public void move() {
        setDirection(controller.getDirection());
        moveHead();
        moveButt();
        direction = nextDirection;
        System.out.println(points.size());
    }

    private void moveButt() {
        var firstPoint = points.get(0);
        var secondPoint = points.get(1);
        var buttVector = secondPoint.subtract(firstPoint).normalize();
        var newButt = firstPoint.add(buttVector);
        if (newButt.equals(secondPoint)) {
            points.remove(0);
        } else {
            points.set(0, newButt);
        }
    }

    private void moveHead() {
        var nextPoint = points.get(points.size() - 1).add(nextDirection.getVector().multiply(velocity));
        if (direction == nextDirection) {
            points.set(points.size() - 1, nextPoint);
        } else {
            points.add(nextPoint);
        }
    }
}
