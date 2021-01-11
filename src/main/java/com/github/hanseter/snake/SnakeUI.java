package com.github.hanseter.snake;

import javafx.geometry.Point2D;

import java.util.List;

public interface SnakeUI {
    void onGameStarts(int width, int height, List<List<Point2D>> snakes);

    void update(List<List<Point2D>> snakes);

    void onGameEnds();
}
