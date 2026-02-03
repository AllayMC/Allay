---
comments: true
---

# Use scoreboards

Scoreboards are often used to display dynamically changing information to players, and allay has a build-in scoreboard
system. In this section, you will learn how to use scoreboards in your plugin.

## Create a Scoreboard

Let's say we want to show the information of the server to the player. Before we send scoreboard to the player, we need
to create a scoreboard first:

```java linenums="1" hl_lines="4"
import org.allaymc.api.scoreboard.Scoreboard;

public class MyScoreboard {
    protected static final Scoreboard info = new Scoreboard("INFO");
}
```

Now we create a scoreboard named `INFO`. Let's add some contents to the scoreboard and send it to the players when they
join:

```java linenums="1" hl_lines="13-15 16-23 25-28 30-33"
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.server.PlayerJoinEvent;
import org.allaymc.api.eventbus.event.server.PlayerQuitEvent;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.server.Server;

import java.util.List;

public class MyScoreboard {
    protected static final Scoreboard info = new Scoreboard("INFO");

    static {
        update();
    }

    protected static void update() {
        var networkSettings = Server.SETTINGS.networkSettings();
        info.setLines(List.of(
                "Welcome to the server!",
                networkSettings.ip() + ":" + networkSettings.port()
        ));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        info.addViewer(event.getPlayer(), DisplaySlot.SIDEBAR); /*(1)!*/
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        info.removeViewer(event.getPlayer(), DisplaySlot.SIDEBAR); /*(2)!*/
    }
}
```

1. :star: We choose to display the information in the sidebar, so that the player can see the information on the right
   side of the screen

2. :star: Don't forget to remove the player from the scoreboard when they quit the server because the player will not be
   removed automatically. DisplaySlot should be same as the one you added (`DisplaySlot.SIDEBAR` in this case).

**:partying_face: And that's it!** Now the player will see the server information when they join the server. Here we
listened to `PlayerJoinEvent` and `PlayerQuitEvent`. For how to use event handler, please refer
to [here](register-event-listeners).

## Update the Scoreboard

You may want to update the scoreboard when some information changes. For example, when the player count changes, and
that's pretty easy:

```java linenums="1" hl_lines="17 25 31"
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.server.PlayerJoinEvent;
import org.allaymc.api.eventbus.event.server.PlayerQuitEvent;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.server.Server;

import java.util.List;

public class MyScoreboard {
    protected static final Scoreboard info = new Scoreboard("INFO");

    protected static void update() {
        var networkSettings = Server.SETTINGS.networkSettings();
        info.setLines(List.of(
                "Welcome to the server!",
                "Online: " + Server.getInstance().getPlayerManager().getPlayerCount(),
                networkSettings.ip() + ":" + networkSettings.port()
        ));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        info.addViewer(event.getPlayer(), DisplaySlot.SIDEBAR);
        update();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        info.removeViewer(event.getPlayer(), DisplaySlot.SIDEBAR);
        update();
    }
}
```

We just update the scoreboard when player joins or quits the server by using `Scoreboard#setLines()` method. And the
scoreboard will be resent to the existing viewers automatically.

## Available Display Slots

In the above examples, we used `DisplaySlot.SIDEBAR` to display the scoreboard in the sidebar. Allay provides three
types of display slots:

| Type       | Description                                                                                                                                                                                 |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| SIDEBAR    | Located on the right side of the player's screen, this is the most commonly used slot type.                                                                                                 |
| LIST       | Located on the online player list, if you want the player screen to stay clean, you can use this slot type.                                                                                 |
| BELOW_NAME | A special slot type which will be shown below player's name tag. Notice that this slot type is very different from the other two types, in most cases you don't need to use this slot type. |
