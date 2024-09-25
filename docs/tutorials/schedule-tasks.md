---
comments: true
---

# Schedule tasks

Allay provides a task scheduling system that allows plugins to schedule tasks for future execution, possibly at
regular intervals. In this tutorial, we'll guide you on scheduling a straightforward task.

## Register tasks

Here, we want an on-screen popup displaying "Hi!" to appear for every online player at a 1-second interval:

```java linenums="1" hl_lines="7-10"
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Server.getInstance().getScheduler().scheduleRepeating(this, () -> {
            Server.getInstance().getOnlinePlayers().values().forEach(player -> player.sendPopup("Hi!"));
            return true; /*(1)!*/
        }, 20/*(2)!*/);
    }
}
```

1. :star: The task will continue to run at the specified interval until the task returns `false`.

2. :star: The interval is specified in ticks. There are 20 ticks in a second, so 20 ticks is approximately 1 second.

**:partying_face: And that's it!** The server will now send a "Hi" message to all players online
at an interval of 20 ticks or approximately every second.

## Server Scheduler :vs: World Scheduler

Allay provides two types of scheduler: the server scheduler and the world scheduler. 
The server scheduler is running on the server game loop and is suitable for tasks that need to run globally,
and the world scheduler is running on the world game loop and is suitable for tasks that need to run per world.

Let's say you want to tell players the name of the block they are standing on, and because this is a world-specific task,
you'd better use the world scheduler for the best performance. Here's how you can do it:

```java linenums="1" hl_lines="7-12"
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Server.getInstance().getWorldPool().getWorlds().values().forEach(world -> {
            world.getScheduler().scheduleRepeating(this, () -> {
                world.getPlayers().forEach(player -> player.sendPopup(player.getBlockStateStandingOn().getBlockType().getIdentifier().toString()));
                return true;
            }, 20);
        });
    }
}
```
