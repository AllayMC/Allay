---
comments: true
---

# Use Forms

Form is a special feature that exists in Minecraft: Bedrock Edition, which allows player to read and input data in a user-friendly way.
Allay provides a powerful form API that allows plugins to create and handle forms easily.

## Create and Send a Form

Let's say you want to show a simple form to the player when they join the server.
About how to register event listeners, please refer to [register an event listener](register-event-listeners.md).

```java linenums="1" hl_lines="8-11"
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
import org.allaymc.api.form.Forms;
import org.allaymc.api.utils.TextFormat;

public class MyEventListener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Forms.simple()
                .title(TextFormat.BLUE + "allay-chan")
                .content(TextFormat.GREEN + "Welcome to the server!")
                .sendTo(event.getPlayer());
    }
}
```

**:partying_face: And that's it!**. [Build and install your plugin](create-your-first-plugin.md#build-and-install-your-plugin),
and when players join the server, they will see a form with the content `Welcome to the server!`:

![use-forms-p1.png](use-forms-p1.png)

## Handle Form Response

Let's get deeper. You may want to ask a player if they like your server
and reply with different content based on the player's options. Here's how you can do it:

```java linenums="1" hl_lines="12-14"
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
import org.allaymc.api.form.Forms;
import org.allaymc.api.utils.TextFormat;

public class MyEventListener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        Forms.modal() /*(1)!*/
                .title(TextFormat.YELLOW + "Do You Like Allay?")
                .content(TextFormat.GREEN + "Please tell us if you like allay-chan!")
                .trueButton(TextFormat.GREEN + "Yes", () -> player.sendText("Thank you!"))
                .falseButton(TextFormat.RED + "No", () -> player.sendText("Sorry to hear that!"))
                .sendTo(player);
    }
}
```

1. :star: Modal form is a form type that only has two buttons.

And when players join the server, they will see a form with the title `Do You Like Allay?`
and two buttons `Yes` and `No`. When the player clicks on the `Yes` button, they will receive
a message `Thank you!`, and when they click on the `No` button, they will receive a message
`Sorry to hear that!`:

![use-forms-p2.png](use-forms-p2.png)

## Available Form Types

Here are all available form types that you can use in minecraft:

| Type   | Description                                                                                                                                                                                     |
|--------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Simple | Simple form can use buttons, and server will receive response when the player presses a button. It is very useful if you want to serve player a set of options and let him choose one.          |
| Modal  | Modal form is a small form that has two buttons. It is suitable for letting player to confirm something.                                                                                        |
| Custom | Custom form is a form that can use a lot of different elements such as dropdown, slider, toggle and has a submit button at the bottom. It can be used when you want player to submit some data. |