package org.chuchro.towerdefence;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

class Enemy {
    double x, y;
    double speed = 1;
    double health = 100;
    int currentPathIndex = 0;
    List<Point2D> path;

    public Enemy(List<Point2D> path) {
        this.path = path;
        if (!path.isEmpty()) {
            this.x = path.get(0).x;
            this.y = path.get(0).y;
        }
    }

    public void update() {
        if (currentPathIndex < path.size() - 1) {
            Point2D target = path.get(currentPathIndex + 1);
            double dx = target.x - x;
            double dy = target.y - y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < speed) {
                currentPathIndex++;
            } else {
                x += (dx / distance) * speed;
                y += (dy / distance) * speed;
            }
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x - 10, y - 10, 20, 20);

        // Health bar
        gc.setFill(Color.BLACK);
        gc.fillRect(x - 15, y - 20, 30, 5);
        gc.setFill(Color.GREEN);
        gc.fillRect(x - 15, y - 20, (health / 100) * 30, 5);
    }
}
