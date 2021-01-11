package com.github.hanseter.snake.display;

import com.github.hanseter.snake.SnakeUI;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class BoardView implements SnakeUI {
    private static final List<Color> colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA);
    private final Canvas canvas = new Canvas();

    public BoardView() {
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void onGameStarts(int width, int height, List<List<Point2D>> snakes) {
        canvas.setWidth(width);
        canvas.setHeight(height);
        update(snakes);
    }

    @Override
    public void update(List<List<Point2D>> snakes) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int i = 0;
        for (List<Point2D> snake : snakes) {
            gc.setStroke(colors.get(i++));
            gc.setLineWidth(5);
            gc.beginPath();
            gc.moveTo(snake.get(0).getX(), snake.get(0).getY());
            for (int j = 1; j < snake.size(); j++) {
                gc.lineTo(snake.get(j).getX(), snake.get(j).getY());
            }
            gc.stroke();
        }
    }

    @Override
    public void onGameEnds() {

    }
}
