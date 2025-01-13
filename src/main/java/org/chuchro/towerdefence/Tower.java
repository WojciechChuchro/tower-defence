package org.chuchro.towerdefence;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    double x, y;
    double range = 100;
    double damage = 10;
    long lastShotTime = 0;
    boolean haveTarget = false;
    long shootingCooldown = 500_000_000;
    List<Projectile> projectiles = new ArrayList<>();
    double size = 30;
    public Tower(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public boolean contains(double mouseX, double mouseY) {
        // Tower is drawn as a square with top-left corner at (x - size/2, y - size/2)
        // and width and height both equal to 'size'.
        return mouseX >= (x - size / 2) && mouseX <= (x + size / 2)
                && mouseY >= (y - size / 2) && mouseY <= (y + size / 2);
    }

    public void upgrade() {
        this.range += 20; // Increase range by 20
        this.damage += 5; // Increase damage by 5
        this.shootingCooldown -= 100_000_000; // Decrease shooting cooldown (faster shooting)
    }
    public void shoot(List<Enemy> enemies) {
        long currentTime = System.nanoTime();
        if (currentTime - lastShotTime < shootingCooldown) {
            return;
        }

        for (Enemy enemy : enemies) {
            double distance = Math.sqrt(Math.pow(enemy.x - x, 2) + Math.pow(enemy.y - y, 2));
            if (distance <= range) {
                projectiles.add(new Projectile(x, y, enemy));
                lastShotTime = currentTime;
                break;
            }
        }

        // Update and remove inactive projectiles
        projectiles.removeIf(p -> !p.active);  // Removes all inactive projectiles
        projectiles.forEach(Projectile::update);  // Update all remaining projectiles
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x - size / 2, y - size / 2, size, size);  // Draw tower as a square
        gc.setStroke(Color.LIGHTBLUE);
        gc.strokeOval(x - range, y - range, range * 2, range * 2);

        // Render all projectiles
        projectiles.forEach(p -> p.render(gc));
    }
}
