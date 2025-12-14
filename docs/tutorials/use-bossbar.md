---
comments: true
---

# Use Boss Bars

Boss bars are visual elements displayed at the top of the player's screen, typically used to show
boss health, progress indicators, or status information. Allay provides a flexible API for creating
and managing boss bars.

## Key Concepts

### BossBar

`BossBar` is the main interface for creating and managing boss bars. Each boss bar has the following
properties:

| Property      | Type            | Description                                   | Default  |
|---------------|-----------------|-----------------------------------------------|----------|
| `title`       | `String`        | The text displayed on the boss bar            | `""`     |
| `progress`    | `float` (0-1)   | The fill percentage of the bar                | `1.0`    |
| `color`       | `BossBarColor`  | The color of the boss bar                     | `PINK`   |
| `style`       | `BossBarStyle`  | The segmentation style of the bar             | `SOLID`  |
| `darkenSky`   | `boolean`       | Whether the sky should darken when displayed  | `false`  |

### BossBarColor

Available colors for boss bars:

| Color            | Description          |
|------------------|----------------------|
| `PINK`           | Pink color (default) |
| `BLUE`           | Blue color           |
| `RED`            | Red color            |
| `GREEN`          | Green color          |
| `YELLOW`         | Yellow color         |
| `PURPLE`         | Purple color         |
| `REBECCA_PURPLE` | Rebecca purple color |
| `WHITE`          | White color          |

### BossBarStyle

Styles determine how the progress bar is segmented:

| Style          | Description                            |
|----------------|----------------------------------------|
| `SOLID`        | No segments, continuous bar (default)  |
| `SEGMENTED_6`  | Bar split into 6 segments              |
| `SEGMENTED_10` | Bar split into 10 segments             |
| `SEGMENTED_12` | Bar split into 12 segments             |
| `SEGMENTED_20` | Bar split into 20 segments             |

### BossBarViewer

`BossBarViewer` is an interface implemented by entities that can view boss bars. The `Player`
interface extends `BossBarViewer`, so players can view boss bars directly.

## Creating a Boss Bar

To create a new boss bar, use the static `BossBar.create()` method:

```java linenums="1"
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.bossbar.BossBarColor;
import org.allaymc.api.bossbar.BossBarStyle;

// Create a new boss bar
BossBar bossBar = BossBar.create();

// Configure the boss bar
bossBar.setTitle("Boss Fight!");
bossBar.setProgress(1.0f); // Full bar
bossBar.setColor(BossBarColor.RED);
bossBar.setStyle(BossBarStyle.SEGMENTED_10);
```

## Showing Boss Bars to Players

To display a boss bar to a player, use the `addViewer()` method:

```java linenums="1"
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.entity.interfaces.EntityPlayer;

public void showBossBarToPlayer(EntityPlayer player) {
    BossBar bossBar = BossBar.create();
    bossBar.setTitle("Dragon Health");
    bossBar.setColor(BossBarColor.PURPLE);
    bossBar.setProgress(0.75f); // 75% full

    // Show the boss bar to the player
    bossBar.addViewer(player);
}
```

To remove the boss bar from a player:

```java linenums="1"
// Remove a specific player
bossBar.removeViewer(player);

// Remove all viewers
bossBar.removeAllViewers();
```

## Updating Boss Bars

When you modify a boss bar's properties, all current viewers will automatically see the changes:

```java linenums="1"
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.bossbar.BossBarColor;

public class BossHealthBar {
    private final BossBar bossBar;
    private float maxHealth;
    private float currentHealth;

    public BossHealthBar(String bossName, float maxHealth) {
        this.bossBar = BossBar.create();
        this.bossBar.setTitle(bossName);
        this.bossBar.setColor(BossBarColor.RED);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void damage(float amount) {
        currentHealth = Math.max(0, currentHealth - amount);
        // Update progress - all viewers will see the change
        bossBar.setProgress(currentHealth / maxHealth);

        // Change color based on health
        if (currentHealth / maxHealth < 0.25f) {
            bossBar.setColor(BossBarColor.YELLOW);
        }
    }

    public void addViewer(EntityPlayer player) {
        bossBar.addViewer(player);
    }

    public void removeViewer(EntityPlayer player) {
        bossBar.removeViewer(player);
    }
}
```

## Practical Examples

### Progress Indicator

Use a boss bar to show loading or task progress:

```java linenums="1"
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.bossbar.BossBarColor;
import org.allaymc.api.bossbar.BossBarStyle;
import org.allaymc.api.entity.interfaces.EntityPlayer;

public class LoadingBar {
    private final BossBar bossBar;

    public LoadingBar() {
        this.bossBar = BossBar.create();
        this.bossBar.setTitle("Loading...");
        this.bossBar.setProgress(0f);
        this.bossBar.setColor(BossBarColor.GREEN);
        this.bossBar.setStyle(BossBarStyle.SEGMENTED_10);
    }

    public void show(EntityPlayer player) {
        bossBar.addViewer(player);
    }

    public void updateProgress(float progress, String message) {
        bossBar.setProgress(Math.min(1f, Math.max(0f, progress)));
        bossBar.setTitle(message);
    }

    public void complete(EntityPlayer player) {
        bossBar.setTitle("Complete!");
        bossBar.setProgress(1f);
        // Remove after completion
        bossBar.removeViewer(player);
    }
}
```

### Event Timer

Display a countdown timer for events:

```java linenums="1"
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.bossbar.BossBarColor;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;

import java.util.Set;

public class EventTimer {
    private final BossBar bossBar;
    private final Set<EntityPlayer> participants;
    private final int totalSeconds;
    private int remainingSeconds;

    public EventTimer(String eventName, int seconds, Set<EntityPlayer> participants) {
        this.bossBar = BossBar.create();
        this.bossBar.setTitle(eventName + " - " + formatTime(seconds));
        this.bossBar.setProgress(1f);
        this.bossBar.setColor(BossBarColor.BLUE);
        this.participants = participants;
        this.totalSeconds = seconds;
        this.remainingSeconds = seconds;

        // Add all participants as viewers
        participants.forEach(bossBar::addViewer);
    }

    public void tick() {
        remainingSeconds--;
        float progress = (float) remainingSeconds / totalSeconds;
        bossBar.setProgress(progress);
        bossBar.setTitle("Event ends in " + formatTime(remainingSeconds));

        // Change color as time runs out
        if (progress < 0.25f) {
            bossBar.setColor(BossBarColor.RED);
        } else if (progress < 0.5f) {
            bossBar.setColor(BossBarColor.YELLOW);
        }

        if (remainingSeconds <= 0) {
            end();
        }
    }

    public void end() {
        bossBar.removeAllViewers();
    }

    private String formatTime(int seconds) {
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }
}
```

### Multi-Player Boss Fight

Show the same boss bar to multiple players in a boss fight:

```java linenums="1"
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.bossbar.BossBarColor;
import org.allaymc.api.bossbar.BossBarStyle;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.Collection;

public class BossFight {
    private final BossBar healthBar;
    private float bossHealth = 100f;
    private final float maxHealth = 100f;

    public BossFight(String bossName) {
        this.healthBar = BossBar.create();
        this.healthBar.setTitle(bossName);
        this.healthBar.setProgress(1f);
        this.healthBar.setColor(BossBarColor.PURPLE);
        this.healthBar.setStyle(BossBarStyle.SEGMENTED_20);
        this.healthBar.setDarkenSky(true); // Darken sky for dramatic effect
    }

    public void playerEnterArena(EntityPlayer player) {
        healthBar.addViewer(player);
    }

    public void playerLeaveArena(EntityPlayer player) {
        healthBar.removeViewer(player);
    }

    public void damageBoss(float damage) {
        bossHealth = Math.max(0, bossHealth - damage);
        healthBar.setProgress(bossHealth / maxHealth);

        // Visual feedback based on health
        if (bossHealth / maxHealth < 0.2f) {
            healthBar.setColor(BossBarColor.RED);
            healthBar.setTitle("Enraged Boss!");
        } else if (bossHealth / maxHealth < 0.5f) {
            healthBar.setColor(BossBarColor.YELLOW);
        }
    }

    public boolean isBossDefeated() {
        return bossHealth <= 0;
    }

    public void endFight() {
        healthBar.removeAllViewers();
    }

    public Collection<?> getViewers() {
        return healthBar.getViewers();
    }
}
```

## Tips

!!! tip "Boss Bar Lifetime"
    Boss bars are not automatically cleaned up. Make sure to call `removeViewer()` or
    `removeAllViewers()` when you no longer need to display the boss bar to avoid memory leaks.

!!! tip "Progress Range"
    The progress value must be between 0.0 and 1.0 (inclusive). Values outside this range will
    throw an exception.

!!! tip "Darken Sky Effect"
    The `setDarkenSky(true)` option creates a dramatic visual effect similar to the Wither or
    Ender Dragon boss fights. Use it sparingly for special events.

!!! tip "Automatic Updates"
    When you modify any property of a boss bar (title, progress, color, style, darkenSky),
    all current viewers will automatically see the changes. You don't need to manually refresh
    the display.
