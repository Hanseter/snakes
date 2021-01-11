package com.github.hanseter.snake.display;

import com.github.hanseter.snake.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class MainView extends Application {
    private final BoardView boardView = new BoardView();

    @Override
    public void start(Stage primaryStage) throws Exception {
        var pane = new AnchorPane(boardView.getCanvas());
        pane.setStyle("-fx-background-color: black");
        primaryStage.setScene(new Scene(pane, 600, 480));
        primaryStage.setTitle("SNAKES!");
        primaryStage.show();
        boardView.getCanvas().setFocusTraversable(true);
        var humanBot = new HumanBot();
        Snake snake = new Snake(humanBot);
        boardView.getCanvas().setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode().equals(KeyCode.W)) {
                humanBot.setDirection(Direction.UP);
            } else if (e.getCode().equals(KeyCode.A)) {
                humanBot.setDirection(Direction.LEFT);
            } else if (e.getCode().equals(KeyCode.S)) {
                humanBot.setDirection(Direction.DOWN);
            } else if (e.getCode().equals(KeyCode.D)) {
                humanBot.setDirection(Direction.RIGHT);
            }
        });
        new Board(600, 480, Arrays.asList(snake, new Snake(new RandomBot())), Arrays.asList(boardView)).start();
    }

}
