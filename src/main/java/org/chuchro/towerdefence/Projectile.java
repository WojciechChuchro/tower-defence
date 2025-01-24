package org.chuchro.towerdefence;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Projectile {
    double startX, startY;
    double x, y;
    Enemy target;
    double speed = 60;
    boolean active = true;

    public Projectile(double startX, double startY, Enemy target) {
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.target = target;
    }

    public void update() {
        double dx = target.x - x;
        double dy = target.y - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < 2) {
            target.health -= 10;
            active = false;
            return;
        }

        double directionX = dx / distance;
        double directionY = dy / distance;

        x += directionX * speed;
        y += directionY * speed;
    }

    public void render(GraphicsContext gc) {
        double dx = target.x - x;
        double dy = target.y - y;
        double angle = Math.atan2(dy, dx);

        gc.setFill(Color.ORANGE);

        double bulletRadius = 5;
        gc.save();

        gc.translate(x, y);
        gc.rotate(Math.toDegrees(angle));

        gc.fillOval(-bulletRadius / 2, -bulletRadius / 2, bulletRadius, bulletRadius);

        gc.restore();
    }
}
