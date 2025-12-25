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
                    context.getSender().sendMessage("Hello World!");
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
                        context.getSender().sendMessage("Hello World!");
                    } else {
                        context.getSender().sendMessage(message);
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
Here is a full list of all built-in parameter types:

| Type                               | Description                                           | Result Type                |
|------------------------------------|-------------------------------------------------------|----------------------------|
| `str(name)`                        | Single word string                                    | `String`                   |
| `msg(name)`                        | Message (can contain spaces, consumes remaining args) | `String`                   |
| `intNum(name)`                     | Integer number                                        | `Integer`                  |
| `floatNum(name)`                   | Float number                                          | `Float`                    |
| `doubleNum(name)`                  | Double number                                         | `Double`                   |
| `longNum(name)`                    | Long number                                           | `Long`                     |
| `shortNum(name)`                   | Short number                                          | `Short`                    |
| `bool(name)`                       | Boolean (true/false)                                  | `Boolean`                  |
| `enums(name, values...)`           | String enumeration                                    | `String`                   |
| `enumsIgnoreCase(name, values...)` | String enumeration (case-insensitive)                 | `String`                   |
| `enumClass(name, EnumClass.class)` | Enum class (auto-mapped)                              | `EnumClass`                |
| `target(name)`                     | Entity selector (@a, @e, @p, etc.)                    | `List<Entity>`             |
| `playerTarget(name)`               | Player selector                                       | `List<Entity>`             |
| `wildcardTarget(name)`             | Wildcard target selector                              | `String`                   |
| `pos(name)`                        | Position (x y z, supports ~)                          | `Vector3d`                 |
| `gameMode(name)`                   | Game mode                                             | `GameMode`                 |
| `difficulty(name)`                 | Difficulty                                            | `Difficulty`               |
| `effect(name)`                     | Effect type                                           | `EffectType`               |
| `enchantment(name)`                | Enchantment type                                      | `EnchantmentType`          |
| `itemType(name)`                   | Item type                                             | `ItemType<?>`              |
| `blockType(name)`                  | Block type                                            | `BlockType<?>`             |
| `blockPropertyValues(name)`        | Block property values                                 | `List<BlockPropertyValue>` |
| `entityType(name)`                 | Entity type                                           | `EntityType<?>`            |
| `remain(name)`                     | Remaining arguments as list                           | `List<String>`             |
| `key(name)`                        | Literal keyword (for subcommands)                     | `String`                   |
| `cmd(name)`                        | Command string                                        | `String`                   |

## Add command aliases

You can add aliases to your command so players can use shorter or alternative names:

```java linenums="1" hl_lines="5"
public class HelloCommand extends Command {
    public HelloCommand() {
        super("hello", "Greets the command sender.", "myplugin.command.hello");
        // Add aliases so players can also use /hi or /greet
        aliases.addAll(List.of("hi", "greet"));
    }
    // ...
}
```

Now players can use `/hello`, `/hi`, or `/greet` to execute your command.

## Create subcommands

Many commands have subcommands (like `/time set` or `/time add`). You can create subcommands using
the `key()` method and navigate the command tree with `up()` and `root()`:

```java linenums="1"
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;

public class MyCommand extends Command {
    public MyCommand() {
        super("mycommand", "A command with subcommands.", "myplugin.command.mycommand");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("help") // /mycommand help
                .exec(context -> {
                    context.getSender().sendMessage("Available commands: help, reload, set");
                    return context.success();
                })
                .root() /*(1)!*/
                .key("reload") // /mycommand reload
                .exec(context -> {
                    context.getSender().sendMessage("Reloading configuration...");
                    return context.success();
                })
                .root()
                .key("set") // /mycommand set <key> <value>
                .str("key")
                .str("value")
                .exec(context -> {
                    String key = context.getResult(1);
                    String value = context.getResult(2);
                    context.getSender().sendMessage("Set " + key + " to " + value);
                    return context.success();
                });
    }
}
```

1. :star: `root()` returns to the root node to define a new branch. You can also use `up()` to go up one level, or `up(n)` to go up n levels.

### Tree navigation methods

| Method     | Description                 |
|------------|-----------------------------|
| `root()`   | Return to the root node     |
| `up()`     | Go up one level in the tree |
| `up(n)`    | Go up n levels in the tree  |
| `parent()` | Get the parent node         |

## Restrict command to specific senders

Sometimes you want a command to only be executable by players, or only from the server console.
Use `SenderType` to restrict who can execute a command:

```java linenums="1" hl_lines="13"
import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;

public class HealCommand extends Command {
    public HealCommand() {
        super("heal", "Heal yourself.", "myplugin.command.heal");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .exec((context, player) -> { /*(1)!*/
                    player.setHealth(player.getMaxHealth());
                    context.addOutput("You have been healed!");
                    return context.success();
                }, SenderType.PLAYER); /*(2)!*/
    }
}
```

1. :star: When using `SenderType`, the executor receives a typed sender as the second parameter.
2. :star: This command can only be executed by players. Console will receive an error message.

### Available sender types

| SenderType                 | Description                          |
|----------------------------|--------------------------------------|
| `SenderType.ANY`           | Any sender (default)                 |
| `SenderType.PLAYER`        | Only players can execute             |
| `SenderType.ACTUAL_PLAYER` | Only real players (not fake players) |
| `SenderType.ENTITY`        | Only entities can execute            |
| `SenderType.SERVER`        | Only server console can execute      |

## Add permissions to command nodes

Besides the main command permission, you can add permissions to specific command nodes.
This is useful for hiding subcommands from players who don't have access:

```java linenums="1" hl_lines="12"
@Override
public void prepareCommandTree(CommandTree tree) {
    tree.getRoot()
            .key("info")
            .exec(context -> {
                context.addOutput("Server info...");
                return context.success();
            })
            .root()
            .key("admin")
            .permission("myplugin.command.admin") /*(1)!*/
            .key("reload")
            .exec(context -> {
                context.addOutput("Reloading...");
                return context.success();
            });
}
```

1. :star: Players without `myplugin.command.admin` permission won't see or be able to use the `/mycommand admin` subcommand.

## Handle command errors

When a command fails, you should return `context.fail()` and optionally add an error message:

```java linenums="1"
.exec(context -> {
    List<EntityPlayer> targets = context.getResult(0);

    // Check if any targets were found
    if (targets.isEmpty()) {
        context.addNoTargetMatchError(); /*(1)!*/
        return context.fail();
    }

    // Check for too many targets
    if (targets.size() > 1) {
        context.addTooManyTargetsError();
        return context.fail();
    }

    // Custom error message
    if (!someCondition) {
        context.addError("Something went wrong!"); /*(2)!*/
        return context.fail();
    }

    return context.success();
})
```

1. :star: Built-in error helper for "no targets matched" error.
2. :star: Custom error message (will be displayed in red).

### Built-in error helpers

| Method                                | Description                      |
|---------------------------------------|----------------------------------|
| `addNoTargetMatchError()`             | No entities matched the selector |
| `addTooManyTargetsError()`            | Too many entities matched        |
| `addPlayerNotFoundError()`            | Player not found                 |
| `addSyntaxError()`                    | Syntax error at current argument |
| `addInvalidExecutorError(SenderType)` | Wrong sender type                |
| `addError(message, args...)`          | Custom error message (red)       |
| `addOutput(message, args...)`         | Normal output message            |

## Using enum classes

For cleaner code, you can use Java enums directly with `enumClass()`:

```java linenums="1"
public class ColorCommand extends Command {
    public ColorCommand() {
        super("color", "Set your color.", "myplugin.command.color");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .enumClass("color", Color.class) /*(1)!*/
                .exec(context -> {
                    Color color = context.getResult(0); /*(2)!*/
                    context.addOutput("Selected color: " + color.name());
                    return context.success();
                });
    }

    private enum Color {
        RED, GREEN, BLUE, YELLOW
    }
}
```

1. :star: The enum values will be automatically converted to command options.
2. :star: The result is already typed as the enum class, no manual parsing needed.

## Advanced: Server-side only commands

Some commands should not be sent to the client (e.g., they conflict with client-side commands).
Override `isServerSideOnly()` to hide the command from clients:

```java linenums="1" hl_lines="9-12"
public class MyServerCommand extends Command {
    public MyServerCommand() {
        super("myserver", "Server-side only command.", "myplugin.command.myserver");
    }

    // Command tree setup...

    @Override
    public boolean isServerSideOnly() {
        return true;
    }
}
```

## Advanced: Debug commands

You can mark a command as a debug command, which makes its name appear blue in the client:

```java linenums="1" hl_lines="9-12"
public class DebugCommand extends Command {
    public DebugCommand() {
        super("mydebug", "A debug command.", "myplugin.command.debug");
    }

    // Command tree setup...

    @Override
    public boolean isDebugCommand() {
        return true;
    }
}
```

## Conclusion

You've learned how to:

- Create and register commands
- Add parameters with various types
- Create subcommands with tree navigation
- Restrict commands to specific sender types
- Handle errors properly
- Use enum classes for cleaner code

For more examples, check the [built-in commands](https://github.com/AllayMC/Allay/tree/master/server/src/main/java/org/allaymc/server/command/defaults) in the Allay source code.