package org.chuchro.towerdefence.ui.component;

import javafx.scene.control.Button;

public class MenuButton extends Button {
    private static final String BASE_STYLE =
            "-fx-background-color: #3498db;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 16px;" +
                    "-fx-cursor: hand;";

    private static final String HOVER_STYLE =
            "-fx-background-color: #2980b9;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 16px;" +
                    "-fx-cursor: hand;";

    public MenuButton(String text) {
        super(text);
        setPrefWidth(200);
        setPrefHeight(40);
        setStyle(BASE_STYLE);
        setupHoverEffects();
    }

    private void setupHoverEffects() {
        setOnMouseEntered(e -> setStyle(HOVER_STYLE));
        setOnMouseExited(e -> setStyle(BASE_STYLE));
    }
}
