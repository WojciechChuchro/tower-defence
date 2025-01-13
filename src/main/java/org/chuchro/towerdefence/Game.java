package org.chuchro.towerdefence;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.chuchro.towerdefence.ui.scene.MainMenu;
import org.chuchro.towerdefence.ui.scene.Pause;
import org.chuchro.towerdefence.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application {
    TowerManager towerManager;
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<Point2D> path = new ArrayList<>();
    public FloatProperty money = new SimpleFloatProperty(Constants.INITIAL_MONEY);
    private long lastEnemySpawnTime = 0;
    private GraphicsContext gc;
    public boolean isMenuVisible = false;
    private Pane gameRoot;
    private Text moneyText;
    private Stage primaryStage;
    private Scene gameScene;
    private Pause pause;
    public boolean isGameRunning = false;
    private AnimationTimer gameLoop;
    MainMenu mainMenu;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.towerManager = new TowerManager(this);
        this.mainMenu= new MainMenu(this);
        this.pause = new Pause(this);
        initializeGame();

        primaryStage.setTitle("Tower Defense Game");
        primaryStage.setScene(mainMenu.getScene());
        primaryStage.show();
    }
    public void setPrimaryStage(Scene scene) {
        primaryStage.setScene(scene);
    }

    public Pane getGameRoot() {
        return this.gameRoot;
    }
    public void returnToMainMenu() {
        primaryStage.setScene(mainMenu.getScene());
    }

    private void initializeGame() {
        moneyText = new Text();
        moneyText.setX(700);
        moneyText.setY(20);
        moneyText.textProperty().bind(money.asString("Your money: %.2f"));

        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        gameRoot = new Pane(canvas);
        gameRoot.getChildren().add(moneyText);

        Button pauseButton = new Button("Menu");
        pauseButton.setLayoutX(10);
        pauseButton.setLayoutY(10);
        pauseButton.setOnAction(e -> pause.showPauseMenu());
        gameRoot.getChildren().add(pauseButton);

        gameScene = new Scene(gameRoot, 800, 600);

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
            if (towerManager.isTowerClicked(e.getX(), e.getY())) {
                towerManager.handleTowerClick(e.getX(), e.getY());
            } else {
                towerManager.towers.add(new Tower(e.getX(), e.getY()));
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

    public void startGame() {
        isGameRunning = true;
        primaryStage.setScene(gameScene);
        gameLoop.start();
    }

    public void resetGame() {
        towerManager.towers.clear();
        enemies.clear();
        money.set(100);
        updateMoneyDisplay();
        isGameRunning = false;
    }

    private void updateMoneyDisplay() {
        moneyText.setText("Your money: " + money);  // Update the text content
    }
    public void closeStage() {
        this.primaryStage.close();
    }

    public void update(long now) {
        if (now - lastEnemySpawnTime >= Constants.ENEMY_SPAWN_COOLDOWN) {
            enemies.add(new Enemy(path));
            lastEnemySpawnTime = now;
        }

        enemies.removeIf(enemy -> enemy.health <= 0);
        enemies.forEach(Enemy::update);

        towerManager.towers.forEach(tower -> tower.shoot(enemies));
    }

    public void render() {
        gc.clearRect(0, 0, 800, 600);

        gc.setStroke(Color.GRAY);
        gc.setLineWidth(20);
        gc.beginPath();
        gc.moveTo(path.getFirst().x, path.getFirst().y);
        for (int i = 1; i < path.size(); i++) {
            gc.lineTo(path.get(i).x, path.get(i).y);
        }
        gc.stroke();

        enemies.forEach(enemy -> enemy.render(gc));
        towerManager.towers.forEach(tower -> tower.render(gc));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
