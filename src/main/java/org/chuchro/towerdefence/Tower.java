package org.chuchro.towerdefence;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javafx.scene.media.AudioClip;
import org.chuchro.towerdefence.utils.Constants;

public class Tower {
    double x, y;
    double range = 100;
    double damage = 10;
    long lastShotTime = 0;
    boolean haveTarget = false;
    long shootingCooldown = 500_000_000;
    List<Projectile> projectiles = new ArrayList<>();
    double size = 30;
    private static AudioClip shootSound;

    public Tower(double x, double y) {
        this.x = x;
        this.y = y;
        try {
            shootSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/sounds/shot.mp3")).toExternalForm());
            shootSound.setVolume(0.5);
        } catch (Exception e) {
            System.err.println("Error loading sound file: " + e.getMessage());
        }
    }

    public static void setGlobalVolume(double volume) {
        shootSound.setVolume(volume);
    }

    public boolean contains(double mouseX, double mouseY) {
        return mouseX >= (x - size / 2) && mouseX <= (x + size / 2)
                && mouseY >= (y - size / 2) && mouseY <= (y + size / 2);
    }

    public void upgrade() {
        this.range += 20;
        this.damage += 5;
        this.shootingCooldown -= 100_000_000;
    }

    public void shoot(List<Enemy> enemies) {
        long currentTime = System.nanoTime();
        if (currentTime - lastShotTime < shootingCooldown) {
            return;
        }

        Optional<Enemy> foundEnemy = findEnemy(enemies);
        if (foundEnemy.isEmpty()) return;

        if (!haveTarget) {
            if (shootSound != null && Constants.SOUND_ENABLED) {
                shootSound.play();
            }
            foundEnemy.get().health-=15;
            lastShotTime= currentTime;
        }
    }
    public Optional<Enemy> findEnemy(List<Enemy> enemies) {
        Enemy foundEnemy = null;

        for (Enemy enemy : enemies) {
            double distance = Math.sqrt(Math.pow((enemy.x - this.x), 2) + Math.pow((enemy.y - this.y), 2));
            if (distance <= range) {
                haveTarget = false;
                foundEnemy = enemy;
                break;
            }
        }

        return Optional.ofNullable(foundEnemy);
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x - size / 2, y - size / 2, size, size);  // Draw tower as a square
        gc.setStroke(Color.LIGHTBLUE);
        gc.strokeOval(x - range, y - range, range * 2, range * 2);

        projectiles.forEach(p -> p.render(gc));
    }
}
