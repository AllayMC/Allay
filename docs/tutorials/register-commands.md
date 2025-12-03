---
comments: true
---

# Register Commands

In this section, we'll walk you through creating a simple command. By the end of this section, you'll have
a basic understanding of how to define and register commands with allay.

## Create a Command

Let's start with a simple command `/hello` that greets the command sender.

```java linenums="1"
import org.allaymc.api.command.Command;
import org.allaymc.api.server.Server;

public class HelloCommand extends Command {
    public HelloCommand() {
        super("hello", "Greets the command sender.", "myplugin.command.hello");
    }
}
```

**And it's just as simple as that!** You've just created a new command with description.
Then you need to register the command into the server:

```java linenums="1" hl_lines="7"
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.registry.Registries;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Registries.COMMANDS.register(new HelloCommand());
    }
}
```

## Add permissions

By default, only server operators can use newly created commands. Since we are creating a hello world
command here, we might want to change its permission for everyone to use.

Let's make the following changes to our code:

```java linenums="1" hl_lines="9"
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.permission.OpPermissionCalculator;
import org.allaymc.api.server.Server;

public class HelloCommand extends Command {
    public HelloCommand() {
        super("hello", "Greets the command sender.", "myplugin.command.hello");
        OpPermissionCalculator.NON_OP_PERMISSIONS.addAll(this.permissions); /*(1)!*/
    }
}
```

1. :star: See tips

!!! tip

    The `getPermissions()` method returns a list of permissions that the command requires, 
    and `OpPermissionCalculator` is the default permission calculator for new players.

    3rd party permission plugin (e.g. LuckPerms) may replace it (`OpPermissionCalculator`) with
    their own permission calculator, and in that case this line of code will be meaningless.

## Handle the commands

Now, the next step is to handle the commands we just created. Let's say we want to send a "Hello, World!" greeting
message to whoever execute this command. It's just a few more lines away from that.

```java linenums="1" hl_lines="10-17"
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.server.Server;

public class HelloCommand extends Command {
    public HelloCommand() {
        super("hello", "Greets the command sender.", "myplugin.command.hello");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .exec(context -> {
                    context.getSender().sendText("Hello World!");
                    return context.success();
                });
    }
}
```

Now, [install](create-your-first-plugin#build-the-plugin) your plugin and restart your server. Join the game and type `/help hello`. You should see the usage
information, which indicates that our command has been successfully added to the game!

Now, type `/hello`, and you should receive the message "Hello World!".

## Add parameters to commands

Now, let's say we want to send a custom message to greet the sender instead of "Hello World!".
We can add a parameter to the command that accepts a message.

Let's change our code to add an **optional** parameter `message` with `msg` type:

```java linenums="1" hl_lines="13-14 16-21"
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.server.Server;

public class HelloCommand extends Command {
    public HelloCommand() {
        super("hello", "Greets the command sender.", "myplugin.command.hello");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .msg("message")
                .optional()
                .exec(context -> {
                    String message = context.getResult(0);
                    if (message.isBlank()) { /*(1)!*/
                        context.getSender().sendText("Hello World!");
                    } else {
                        context.getSender().sendText(message);
                    }
                    return context.success();
                });
    }
}
```

1.  :white_check_mark: Here, we checked if the optional parameter is provided.

**:partying_face:  And it's done!** Now when the users use `/hello This is my message!`, "This is my message!" will
be shown to them instead of "Hello World!".

!!! tip

    To make the parameter mandatory, remove the `.optional()` method call.

### Built-in parameter types

In the section above, we added a parameter with type `msg` which is a **built-in type** supported by Allay.
You can check the full list of all built-in types currently supported [here](https://github.com/AllayMC/Allay/blob/master/api/src/main/java/org/allaymc/api/command/tree/CommandNodeFactory.java).