---
comments: true
---

# Schedule tasks

Allay provides a task scheduling system that allows plugins to schedule tasks for future execution, possibly at
regular intervals. In this tutorial, we'll guide you on scheduling a straightforward task.

Here, we want an on-screen popup displaying "Hi!" to appear for every online player at a 1-second interval:

```java linenums="1"
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Server.getInstance().getScheduler().scheduleRepeating(this, () -> {
            Server.getInstance().getOnlinePlayers().values().forEach(player -> player.sendPopup("Hi!"));
            return true; /*(1)!*/
        }, 20);
    }
}
```

1. :star: The task will continue to run at the specified interval until the task returns `false`.

**:partying_face: And that's it!** The server will now send a "Hi" message to all players online
at an interval of 20 ticks or approximately every second.