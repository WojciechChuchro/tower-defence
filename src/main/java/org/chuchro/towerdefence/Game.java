package org.chuchro.towerdefence;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Game extends Application {
    private final List<Tower> towers = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<Point2D> path = new ArrayList<>();
    private float money = 100;
    private long lastEnemySpawnTime = 0;
    private final long enemySpawnCooldown = 500_000_000;
    private GraphicsContext gc;
    private boolean isMenuVisible = false;
    private VBox menu;
    private Pane gameRoot;
    private Text moneyText;
    private Stage primaryStage;
    private Scene gameScene;
    private Scene mainMenuScene;
    private boolean isGameRunning = false;
    private AnimationTimer gameLoop;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        initializeMainMenu();
        initializeGame();

        primaryStage.setTitle("Tower Defense Game");
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    private void initializeMainMenu() {
        // Create main menu buttons
        Button playButton = createMenuButton("Play");
        Button settingsButton = createMenuButton("Settings");
        Button exitButton = createMenuButton("Exit");

        // Create title
        Text titleText = new Text("Tower Defense");
        titleText.setFont(new Font("Arial Bold", 40));
        titleText.setFill(Color.WHITE);

        // Setup button actions
        playButton.setOnAction(e -> startGame());
        settingsButton.setOnAction(e -> showSettings());
        exitButton.setOnAction(e -> primaryStage.close());

        // Create main menu layout
        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(titleText, playButton, settingsButton, exitButton);
        menuLayout.setStyle("-fx-background-color: #2C3E50;");

        mainMenuScene = new Scene(menuLayout, 800, 600);
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(40);
        button.setStyle(
                "-fx-background-color: #3498db;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-cursor: hand;"
        );

        // Add hover effect
        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: #2980b9;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 16px;" +
                                "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: #3498db;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 16px;" +
                                "-fx-cursor: hand;"
                )
        );

        return button;
    }

    private void showSettings() {
        // Create settings menu
        VBox settingsLayout = new VBox(20);
        settingsLayout.setAlignment(Pos.CENTER);
        settingsLayout.setStyle("-fx-background-color: #2C3E50;");

        Text settingsTitle = new Text("Settings");
        settingsTitle.setFont(new Font("Arial Bold", 30));
        settingsTitle.setFill(Color.WHITE);

        // Add some example settings
        Button difficultyButton = createMenuButton("Difficulty: Normal");
        Button soundButton = createMenuButton("Sound: On");
        Button backButton = createMenuButton("Back to Main Menu");

        backButton.setOnAction(e -> primaryStage.setScene(mainMenuScene));

        settingsLayout.getChildren().addAll(settingsTitle, difficultyButton, soundButton, backButton);
        Scene settingsScene = new Scene(settingsLayout, 800, 600);
        primaryStage.setScene(settingsScene);
    }

    private void initializeGame() {
        moneyText = new Text("Your money: " + money);
        moneyText.setX(700);
        moneyText.setY(20);

        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        gameRoot = new Pane(canvas);
        gameRoot.getChildren().add(moneyText);

        // Create pause button
        Button pauseButton = new Button("Menu");
        pauseButton.setLayoutX(10);
        pauseButton.setLayoutY(10);
        pauseButton.setOnAction(e -> showPauseMenu());
        gameRoot.getChildren().add(pauseButton);

        gameScene = new Scene(gameRoot, 800, 600);

        // Initialize path
        path.add(new Point2D(0, 300));
        path.add(new Point2D(200, 300));
        path.add(new Point2D(200, 100));
        path.add(new Point2D(600, 100));
        path.add(new Point2D(600, 500));
        path.add(new Point2D(800, 500));

        setupGameControls();
        initializeGameLoop();
    }

    private void setupGameControls() {
        gameScene.setOnMouseClicked(e -> {
            if (!isGameRunning || isMenuVisible) {
                return;
            }
            if (isTowerClicked(e.getX(), e.getY())) {
                handleTowerClick(e.getX(), e.getY());
            } else {
                towers.add(new Tower(e.getX(), e.getY()));
            }
        });
    }

    private void initializeGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isGameRunning) {
                    update(now);
                    render();
                }
            }
        };
    }

    private void startGame() {
        isGameRunning = true;
        primaryStage.setScene(gameScene);
        gameLoop.start();
    }

    private void showPauseMenu() {
        isGameRunning = false;

        Button resumeButton = createMenuButton("Resume");
        Button mainMenuButton = createMenuButton("Main Menu");

        VBox pauseMenu = new VBox(20);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.8);" +
                        "-fx-padding: 20px;"
        );

        resumeButton.setOnAction(e -> {
            gameRoot.getChildren().remove(pauseMenu);
            isGameRunning = true;
        });

        mainMenuButton.setOnAction(e -> {
            gameRoot.getChildren().remove(pauseMenu);
            primaryStage.setScene(mainMenuScene);
            resetGame();
        });

        pauseMenu.getChildren().addAll(resumeButton, mainMenuButton);
        gameRoot.getChildren().add(pauseMenu);

        // Center the pause menu
        pauseMenu.setLayoutX((800 - 200) / 2);  // 800 is scene width, 200 is button width
        pauseMenu.setLayoutY((600 - 100) / 2);  // 600 is scene height, 100 is approximate menu height
    }

    private void resetGame() {
        // Reset game state
        towers.clear();
        enemies.clear();
        money = 100;
        updateMoneyDisplay();
        isGameRunning = false;
    }
    private void updateMoneyDisplay() {
        moneyText.setText("Your money: " + money);  // Update the text content
    }
    // Method to check if the click is on any tower
    private boolean isTowerClicked(double mouseX, double mouseY) {
        for (Tower tower : towers) {
            if (tower.contains(mouseX, mouseY)) {
                return true;
            }
        }
        return false;
    }

    // Method to handle click event on a tower
    private void handleTowerClick(double mouseX, double mouseY) {
        for (Tower tower : towers) {
            if (tower.contains(mouseX, mouseY)) {
                // Here you can implement any behavior you want when a tower is clicked
                System.out.println("Tower clicked at: (" + mouseX + ", " + mouseY + ")");
                showTowerMenu(tower);
                // For example, show tower details, upgrade tower, etc.
                break; // Once we find the clicked tower, we can break out of the loop
            }
        }
    }
    private void showTowerMenu(Tower tower) {
        isMenuVisible = true;
        // Create menu buttons
        Button sellButton = new Button("Sell (+30)");
        Button upgradeButton = new Button("Upgrade (-60)");

        // Set button actions
        sellButton.setOnAction(event -> sellTower(tower));
        upgradeButton.setOnAction(event -> upgradeTower(tower));

        // Create menu container
        menu = new VBox(10, sellButton, upgradeButton);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        gameRoot.getChildren().add(menu); // Add menu to the root container
    }
    private void sellTower(Tower tower) {
        money += 30; // Add 30 money for selling
        towers.remove(tower); // Remove the tower from the game
        gameRoot.getChildren().remove(menu); // Remove the menu from the screen
        System.out.println("Tower sold! Current money: " + money);
        isMenuVisible = false;
        updateMoneyDisplay();
    }

    // Handle upgrading the tower
    private void upgradeTower(Tower tower) {
        if (money >= 60) {

            money -= 60; // Subtract 60 for upgrading
            tower.upgrade(); // Upgrade the tower (we'll add upgrade logic in Tower class)
            gameRoot.getChildren().remove(menu); // Remove the menu from the screen

            updateMoneyDisplay();
            System.out.println("Tower upgraded! Current money: " + money);
        } else {
            showInsufficientFundsAlert(); // Show an alert if the player doesn't have enough money
        }

        isMenuVisible = false;
    }

    // Show alert for insufficient funds
    private void showInsufficientFundsAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Insufficient Funds");
        alert.setHeaderText(null);
        alert.setContentText("You do not have enough money to upgrade this tower!");
        alert.showAndWait();
    }
    public void update(long now) {
        // Spawn enemies
        if (now - lastEnemySpawnTime >= enemySpawnCooldown) {
            enemies.add(new Enemy(path));
            lastEnemySpawnTime = now;
        }

        // Update enemies
        enemies.removeIf(enemy -> enemy.health <= 0);
        enemies.forEach(Enemy::update);

        // Update towers
        towers.forEach(tower -> tower.shoot(enemies));
    }

    public void render() {
        gc.clearRect(0, 0, 800, 600);

        // Draw path
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(20);
        gc.beginPath();
        gc.moveTo(path.getFirst().x, path.getFirst().y);
        for (int i = 1; i < path.size(); i++) {
            gc.lineTo(path.get(i).x, path.get(i).y);
        }
        gc.stroke();

        // Draw game objects
        enemies.forEach(enemy -> enemy.render(gc));
        towers.forEach(tower -> tower.render(gc));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
