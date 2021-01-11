package com.github.hanseter.snake;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Board {
    private final int width;
    private final int height;
    private final List<Snake> snakes;
    private final List<SnakeUI> uis;
    private final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor(e -> {
        Thread t = new Thread(e);
        t.setDaemon(true);
        return t;
    });

    public Board(int width, int height, List<Snake> snakes, List<SnakeUI> uis) {
        this.width = width;
        this.height = height;
        this.snakes = snakes;
        this.uis = uis;
    }


    public void start() {
        for (Snake snake : snakes) {
            snake.onGameStarts(width, height);
        }
        var lines = calculateLines(snakes);
        for (SnakeUI uis : uis) {
            uis.onGameStarts(width, height, lines);
        }
        timer.schedule(this::update, 500, TimeUnit.MILLISECONDS);
    }

    private void update() {
        for (Snake snake : snakes) {
            snake.move();
        }
        //check for collision, game end, etc.
        var lines = calculateLines(snakes);
        for (SnakeUI uis : uis) {
            uis.update(lines);
        }
        for (Snake snake: snakes) {
            //snake.updateVisibleArea()
        }
        timer.schedule(this::update, 50, TimeUnit.MILLISECONDS);
    }

    private List<List<Point2D>> calculateLines(List<Snake> snakes) {
        List<List<Point2D>> lines = new ArrayList<>(snakes.size());
        for (Snake snake : snakes) {
            lines.add(snake.getPoints());
        }
        return lines;
    }

}
