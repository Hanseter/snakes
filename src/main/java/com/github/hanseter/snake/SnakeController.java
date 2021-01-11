package com.github.hanseter.snake;

import javafx.geometry.Point2D;

import java.util.List;

public interface SnakeController {

    void onViewChanged(Point2D headPos, double width, double height, List<List<Point2D>> visibleLines);

    Direction getDirection();

    void setDirection(Direction direction);
}
