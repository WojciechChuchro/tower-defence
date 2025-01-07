package org.chuchro.towerdefence;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Projectile {
    double startX, startY;
    double x, y;
    Enemy target;
    double speed = 60;  // Speed of the projectile
    boolean active = true;

    public Projectile(double startX, double startY, Enemy target) {
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.target = target;
    }

    public void update() {
        // Calculate the direction vector from the projectile to the target
        double dx = target.x - x;
        double dy = target.y - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // If the projectile is close enough to the target (within a small threshold)
        if (distance < 2) { // Threshold distance to consider it as "reached"
            target.health -= 10; // Apply damage to the target
            active = false; // Deactivate the projectile after hit
            return;
        }

        // Normalize the direction vector (ensure consistent speed and direction)
        double directionX = dx / distance; // Get the normalized direction (unit vector)
        double directionY = dy / distance;

        // Move the projectile smoothly by a fixed amount based on speed
        x += directionX * speed;
        y += directionY * speed;
    }

    public void render(GraphicsContext gc) {
        // Calculate the angle of the projectile relative to the starting position and target
        double dx = target.x - x;
        double dy = target.y - y;
        double angle = Math.atan2(dy, dx);

        // Draw the bullet as a small circle or oval (adjust the size as needed)
        gc.setFill(Color.ORANGE);

        // Calculate the bullet's x and y position to center it on its current coordinates
        double bulletRadius = 5;  // Bullet size (adjust as needed)
        gc.save(); // Save the current state of the canvas

        // Translate to the position of the projectile and rotate the canvas to the angle
        gc.translate(x, y);
        gc.rotate(Math.toDegrees(angle)); // Rotate to face the direction of movement

        // Draw the bullet shape (a small circle)
        gc.fillOval(-bulletRadius / 2, -bulletRadius / 2, bulletRadius, bulletRadius);

        // Restore the canvas state to stop affecting other drawing operations
        gc.restore();
    }
}
