package org.chuchro.towerdefence.ui.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.chuchro.towerdefence.Game;
import org.chuchro.towerdefence.ui.component.MenuButton;
import org.chuchro.towerdefence.utils.Constants;

public class Pause {
    private Scene scene;
    private final Game game;

    public Pause(Game game) {
        this.game =game;

    }

    public void showPauseMenu() {
        game.isGameRunning =false;
        Button resumeButton = new MenuButton("Resume");
        Button mainMenuButton = new MenuButton("Main Menu");

        VBox pauseMenu = new VBox(20);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.8);" +
                        "-fx-padding: 20px;"
        );

        resumeButton.setOnAction(e -> {
            game.getGameRoot().getChildren().remove(pauseMenu);
            game.isGameRunning = true;
        });

        mainMenuButton.setOnAction(e -> {
            game.getGameRoot().getChildren().remove(pauseMenu);
            game.returnToMainMenu();
            game.resetGame();
        });

        pauseMenu.getChildren().addAll(resumeButton, mainMenuButton);
        game.getGameRoot().getChildren().add(pauseMenu);

        pauseMenu.setLayoutX((double) (Constants.WINDOW_WIDTH - 200) / 2);
        pauseMenu.setLayoutY((double) (Constants.WINDOW_WIDTH - 100) / 2);
    }
}
