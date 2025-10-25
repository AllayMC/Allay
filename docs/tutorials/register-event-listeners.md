---
comments: true
---

# Register Event Listeners

Allay provides a powerful event system that allows plugins to listen to various events that happen in the
server. Let's walk you through registering a simple event listener.

## Add Event Handlers

Let's say your plugin want to listen to when a player joins the server and send a message to inform everyone in the server.
You need to put the event listener in a class and annotate the method with `@EventHandler`:

```java linenums="1"
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;

public class MyEventListener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) { /*(1)!*/
        Server.getInstance().getMessageChannel().broadcastText(TextFormat.YELLOW + "Welcome " + event.getPlayer().getDisplayName() + " to the server!");
    }
}
```

1. :star: See tips

!!! tip

    Method annotated with `@EventHandler` must have one and only one parameter and return `void`.
    The type of the parameter is the event that you want to listen to.

## Register Listeners

Now, we need to register our event handlers to the Allay event system.

```java linenums="1" hl_lines="7"
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Server.getInstance().getEventBus().registerListener(new MyEventListener());
    }
}
```

**:partying_face: And that's it!** Your plugin should now listen to and handle events when player joins.
Remember, you can add as many methods as you want to listen to any event.

## Explore Available Events

Allay provides a wide range of events that you can listen to. To see a list of all available events,
visit the [Event List](/advanced/event-list) page.
