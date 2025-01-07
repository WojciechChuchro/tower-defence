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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private final long enemySpawnCooldown = 500_000_000; // 2 seconds in nanoseconds
    private GraphicsContext gc;
    private boolean isMenuVisible = false;
    private VBox menu; // Menu to display options
    private Pane root; // The root pane for the scene
    private Text moneyText;

    @Override
    public void start(Stage stage) throws Exception {
        moneyText = new Text("Your money: " + money);
        moneyText.setX(700);
        moneyText.setY(20);
        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        this.root = new Pane(canvas);
        this.root.getChildren().add(moneyText);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Tower Defense Game");
        stage.show();

        // Initialize path
        path.add(new Point2D(0, 300));
        path.add(new Point2D(200, 300));
        path.add(new Point2D(200, 100));
        path.add(new Point2D(600, 100));
        path.add(new Point2D(600, 500));
        path.add(new Point2D(800, 500));

        scene.setOnMouseClicked(e -> {
            if(isMenuVisible) {
                return;
            }
            if (isTowerClicked(e.getX(), e.getY())) {
                handleTowerClick(e.getX(), e.getY());
            } else {
                towers.add(new Tower(e.getX(), e.getY()));
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
                render();
            }
        }.start();
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
        root.getChildren().add(menu); // Add menu to the root container
    }
    private void sellTower(Tower tower) {
        money += 30; // Add 30 money for selling
        towers.remove(tower); // Remove the tower from the game
        root.getChildren().remove(menu); // Remove the menu from the screen
        System.out.println("Tower sold! Current money: " + money);
        isMenuVisible = false;
        updateMoneyDisplay();
    }

    // Handle upgrading the tower
    private void upgradeTower(Tower tower) {
        if (money >= 60) {

            money -= 60; // Subtract 60 for upgrading
            tower.upgrade(); // Upgrade the tower (we'll add upgrade logic in Tower class)
            root.getChildren().remove(menu); // Remove the menu from the screen

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
        gc.moveTo(path.get(0).x, path.get(0).y);
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
