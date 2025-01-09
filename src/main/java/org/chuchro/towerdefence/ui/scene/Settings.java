package org.chuchro.towerdefence.ui.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.chuchro.towerdefence.ui.component.MenuButton;

public class Settings {
    Scene scene;

    Settings() {
    }

    public void showSettings() {
        VBox settingsLayout = new VBox(20);
        settingsLayout.setAlignment(Pos.CENTER);
        settingsLayout.setStyle("-fx-background-color: #2C3E50;");

        Text settingsTitle = new Text("Settings");
        settingsTitle.setFont(new Font("Arial Bold", 30));
        settingsTitle.setFill(Color.WHITE);

        Button difficultyButton = new MenuButton("Difficulty: Normal");
        Button soundButton = new MenuButton("Sound: On");
        Button backButton = new MenuButton("Back to Main Menu");

        settingsLayout.getChildren().addAll(settingsTitle, difficultyButton, soundButton, backButton);
        this.scene = new Scene(settingsLayout, 800, 600);
    }

    public Scene getScene() {
        return this.scene;
    }
}
