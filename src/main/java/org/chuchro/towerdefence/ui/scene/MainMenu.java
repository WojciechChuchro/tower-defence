package org.chuchro.towerdefence.ui.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.chuchro.towerdefence.Game;
import org.chuchro.towerdefence.ui.component.MenuButton;

public class MainMenu {
    private Scene scene;
    private final Settings settings;
    private final Game game;

    public MainMenu(Game game) {
        this.game = game;
        this.settings = new Settings(game, this);
        initializeMainMenu();
    }

    private void initializeMainMenu() {
        Button playButton = new MenuButton("Play");
        Button settingsButton = new MenuButton("Settings");
        Button exitButton = new MenuButton("Exit");

        Text titleText = new Text("Tower Defense");
        titleText.setFont(new Font("Arial Bold", 40));
        titleText.setFill(Color.WHITE);

        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(titleText, playButton, settingsButton, exitButton);
        menuLayout.setStyle("-fx-background-color: #2C3E50;");

        this.scene = new Scene(menuLayout, 800, 600);

        playButton.setOnAction(e -> game.startGame());
        settingsButton.setOnAction(e -> game.setPrimaryStage(settings.getScene()));
        exitButton.setOnAction(e -> game.closeStage());
    }
    public Scene getScene() {
        return this.scene;
    }
}
