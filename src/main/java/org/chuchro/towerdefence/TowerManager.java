package org.chuchro.towerdefence;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    public final List<Tower> towers = new ArrayList<>();
    Game game;

    public TowerManager(Game game) {
        this.game = game;

    }
    public boolean isTowerClicked(double mouseX, double mouseY) {
        for (Tower tower : towers) {
            if (tower.contains(mouseX, mouseY)) {
                return true;
            }
        }
        return false;
    }

    public void handleTowerClick(double mouseX, double mouseY) {
        for (Tower tower : towers) {
            if (tower.contains(mouseX, mouseY)) {
                System.out.println("Tower clicked at: (" + mouseX + ", " + mouseY + ")");
                showTowerMenu(tower);
                break;
            }
        }
    }

    private void showInsufficientFundsAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Insufficient Funds");
        alert.setHeaderText(null);
        alert.setContentText("You do not have enough money to upgrade this tower!");
        alert.showAndWait();
    }
    private void showTowerMenu(Tower tower) {
        game.isMenuVisible = true;
        Button sellButton = new Button("Sell (+30)");
        Button upgradeButton = new Button("Upgrade (-60)");

        sellButton.setOnAction(event -> sellTower(tower));
        upgradeButton.setOnAction(event -> upgradeTower(tower));

        menu = new VBox(10, sellButton, upgradeButton);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        gameRoot.getChildren().add(menu);
    }

    private void sellTower(Tower tower) {
        money += 30;
        towers.remove(tower);
        gameRoot.getChildren().remove(menu);
        System.out.println("Tower sold! Current money: " + money);
        isMenuVisible = false;
        updateMoneyDisplay();
    }

    private void upgradeTower(Tower tower) {
        if (money >= 60) {

            money -= 60;
            tower.upgrade();
            gameRoot.getChildren().remove(menu);

            updateMoneyDisplay();
            System.out.println("Tower upgraded! Current money: " + money);
        } else {
            showInsufficientFundsAlert();
        }

        isMenuVisible = false;
    }
}
