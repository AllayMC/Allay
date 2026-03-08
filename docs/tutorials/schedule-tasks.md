---
comments: true
---

# Schedule Tasks

Allay provides a task scheduling system that allows plugins to schedule tasks for future execution, possibly at
regular intervals. In this tutorial, we'll guide you on scheduling a straightforward task.

## Register Tasks

Here, we want an on-screen popup displaying "Hi!" to appear for every online player at a 1-second interval:

```java linenums="1" hl_lines="7-10"
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Server.getInstance().getScheduler().scheduleRepeating(this, () -> {
            Server.getInstance().getPlayerManager().getPlayers().values().forEach(player -> player.sendPopup("Hi!"));
            return true; /*(1)!*/
        }, 20/*(2)!*/);
    }
}
```

1. :star: The task will continue to run at the specified interval until the task returns `false`.

2. :star: The interval is specified in ticks. There are 20 ticks in a second, so 20 ticks is approximately 1 second.

**:partying_face: And that's it!** The server will now send a "Hi" message to all players online
at an interval of 20 ticks or approximately every second.

## Scheduler Types

Allay provides four types of scheduler: server, world, dimension, and entity. Pick the smallest scope that matches the data you touch.
Each scheduler runs on a specific thread:

- Server scheduler: runs on the server thread.
- World scheduler: runs on the world thread.
- Dimension scheduler: runs on the compute thread pool when `world-settings.tick-dimension-in-parallel` is set to `true`, otherwise runs on the world thread.
- Entity scheduler: runs on the thread of the entity's current dimension.

### Server Scheduler (global, non-world work)

Use the server scheduler for tasks that are global and do not mutate world, dimension, or entity state.

```java linenums="1" hl_lines="7-9"
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Server.getInstance().getScheduler().scheduleRepeating(this, this::cleanupExpiredSessions, 20 * 60);
    }

    private void cleanupExpiredSessions() {
        // Clean plugin-level caches or metrics.
    }
}
```

### World Scheduler (per-world state)

Use the world scheduler for tasks that operate on world-wide state (time, weather, world data).

```java linenums="1" hl_lines="8-10"
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.WorldData;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Server.getInstance().getWorldPool().getWorlds().values().forEach(world -> {
            world.getScheduler().scheduleRepeating(this, () -> world.getWorldData().setTimeOfDay(WorldData.TIME_NOON), 20 * 300);
        });
    }
}
```

### Dimension Scheduler (per-dimension blocks and entities)

Use the dimension scheduler for tasks that update blocks, particles, or entities inside one dimension.

```java linenums="1" hl_lines="9-13"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Server.getInstance().getWorldPool().getWorlds().values().forEach(world -> {
            world.getDimensions().values().forEach(dimension -> {
                dimension.getScheduler().scheduleRepeating(this, () -> {
                    dimension.setBlockState(0, 64, 0, BlockTypes.STONE.getDefaultState());
                    return true;
                }, 20);
            });
        });
    }
}
```

### Entity Scheduler (single-entity lifecycle)

Use the entity scheduler for tasks tied to a single entity so the task stops when the entity despawns.

```java linenums="1" hl_lines="6-9"
import org.allaymc.api.entity.interfaces.EntityPlayer;

public void onSomeEvent(EntityPlayer player) {
    player.getScheduler().scheduleRepeating(player, () -> {
        player.sendPopup("Charging...");
        return true;
    }, 20);
}
```
