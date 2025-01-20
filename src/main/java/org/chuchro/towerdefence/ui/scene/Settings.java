package org.chuchro.towerdefence.ui.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.chuchro.towerdefence.Game;
import org.chuchro.towerdefence.Tower;
import org.chuchro.towerdefence.ui.component.MenuButton;
import org.chuchro.towerdefence.utils.Constants;
import org.chuchro.towerdefence.utils.Difficulty;

public class Settings {
    private Scene scene;
    private MainMenu mainMenu;
    private final Game game;
    private StackPane root;
    private VBox settingsLayout;
    private boolean soundEnabled = true;
    private Button difficultyButton;

    Settings(Game game, MainMenu mainMenu) {
        this.game = game;
        this.mainMenu = mainMenu;
        showSettings();
    }

    public void showDifficultyMenu() {
        VBox difficultyMenu = new VBox(10);
        difficultyMenu.setAlignment(Pos.CENTER);
        difficultyMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        difficultyMenu.setMaxWidth(300);
        difficultyMenu.setMaxHeight(400);

        Text title = new Text("Select Difficulty");
        title.setFont(new Font("Arial Bold", 24));
        title.setFill(Color.WHITE);

        Button easyButton = new MenuButton("Easy");
        Button normalButton = new MenuButton("Normal");
        Button hardButton = new MenuButton("Hard");
        Button backButton = new MenuButton("Back");

        easyButton.setPrefWidth(200);
        normalButton.setPrefWidth(200);
        hardButton.setPrefWidth(200);
        backButton.setPrefWidth(200);

        easyButton.setOnAction(e -> {
            Constants.difficulty = Difficulty.EASY;
            updateDifficultyButton();
            root.getChildren().remove(difficultyMenu);
        });

        normalButton.setOnAction(e -> {
            Constants.difficulty = Difficulty.NORMAL;
            updateDifficultyButton();
            root.getChildren().remove(difficultyMenu);
        });

        hardButton.setOnAction(e -> {
            Constants.difficulty = Difficulty.HARD;
            updateDifficultyButton();
            root.getChildren().remove(difficultyMenu);
        });

        backButton.setOnAction(e -> root.getChildren().remove(difficultyMenu));

        difficultyMenu.getChildren().addAll(title, easyButton, normalButton, hardButton, backButton);

        root.getChildren().add(difficultyMenu);
    }

    private void updateDifficultyButton() {
        difficultyButton.setText("Difficulty: " + Constants.difficulty);
    }

    public void showSettings() {
        root = new StackPane();
        root.setStyle("-fx-background-color: #2C3E50;");

        settingsLayout = new VBox(20);
        settingsLayout.setAlignment(Pos.CENTER);

        Text settingsTitle = new Text("Settings");
        settingsTitle.setFont(new Font("Arial Bold", 30));
        settingsTitle.setFill(Color.WHITE);

        difficultyButton = new MenuButton("Difficulty: " + Constants.difficulty);
        Button backButton = new MenuButton("Back to Main Menu");

        difficultyButton.setOnAction(e -> showDifficultyMenu());
        Button soundButton = new MenuButton("Sound: " + (Constants.SOUND_ENABLED ? "On" : "Off"));
        soundButton.setOnAction(e -> {
            Constants.SOUND_ENABLED = !Constants.SOUND_ENABLED;
            soundEnabled = Constants.SOUND_ENABLED;
            soundButton.setText("Sound: " + (Constants.SOUND_ENABLED ? "On" : "Off"));
        });

        Button volumeButton = new MenuButton("Volume Settings");
        volumeButton.setOnAction(e -> showVolumeMenu());

        backButton.setOnAction(e -> game.setPrimaryStage(mainMenu.getScene()));

        settingsLayout.getChildren().addAll(settingsTitle, difficultyButton, soundButton,volumeButton,  backButton);

        root.getChildren().add(settingsLayout);

        this.scene = new Scene(root, 800, 600);
    }
    private void showVolumeMenu() {
        VBox volumeMenu = new VBox(10);
        volumeMenu.setAlignment(Pos.CENTER);
        volumeMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        volumeMenu.setMaxWidth(300);
        volumeMenu.setMaxHeight(400);

        Text title = new Text("Volume Control");
        title.setFont(new Font("Arial Bold", 24));
        title.setFill(Color.WHITE);

        Slider volumeSlider = new Slider(0, 100, 50);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Tower.setGlobalVolume(newValue.doubleValue() / 100.0);
        });
        Button volumeButton = new MenuButton("Volume Settings");
        volumeButton.setOnAction(e -> showVolumeMenu());


        Button backButton = new MenuButton("Back");
        backButton.setPrefWidth(200);
        backButton.setOnAction(e -> root.getChildren().remove(volumeMenu));

        volumeMenu.getChildren().addAll(title, volumeSlider, backButton);
        root.getChildren().add(volumeMenu);
    }
    public Scene getScene() {
        return this.scene;
    }
}
