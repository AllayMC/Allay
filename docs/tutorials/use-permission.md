---
comments: true
---

# Use Permission

Allay provides a simple permission api that you can do permission checks easily. It also allows
you to write and use your own permission plugin. In this tutorial, we'll guide you on doing
permission checks and write/use custom permission plugin.

## Permission

Before we start, we need to know what permission is. On your server, there will be certain features,
commands, and functionality that exist. Some of these features will be included with the server, and
others are added with plugins. Most of these actions have a permission associated with them, so you
can control which users have access to each feature or command.

A permission is just a string (a sequence of letters/digits) and is separated into parts using periods.
For example, `allay.command.say` is the permission for Allay's `/say` command.

The string that represents a certain permission is also sometimes called a "permission node" or just
"node" for short. Permission nodes can have three values: `true`, `false`, and `undefined`.

- `true` means that the player has that permission and the condition(s) assigned to it will be given to the player.
- `false` means that the player does not have that permission and the condition(s) assigned to it will be denied to the player.
- `undefined` means that the permission is not explicitly set. This usually means that it defaults to being the same as `false`.
  Very rarely, plugins can make undefined permissions default to `true`, in which case you need to set them explicitly to `false`.

## Do Permission Check

It is effortless to do permission checks on a `Permissible` object:

```java linenums="1"
import org.allaymc.api.permission.Permissible;
import org.allaymc.api.permission.Tristate;

public class Example {
    public void example(Permissible permissible) { /*(1)!*/
        // Check if this permissible object has the permission "allay.command.say"
        Tristate tristate = permissible.hasPermission("allay.command.say");
        // Convert the Tristate to a boolean, where Tristate.TRUE will be true
        // and Tristate.FALSE, Tristate.UNDEFINED will be false
        boolean bool = tristate.asBoolean();
    }
}
```

1. :star: See tips

!!! tip

    `Permissible` is an interface that contains all necessary permission related methods. Objects like `Entity`,
    `Server`, and `CommandSender` implement this interface so you can also do permission checks on them.

## Permission Calculator

Every permissible object has a permission calculator associated with it. Permission calculator is used to calculate
the permission value when checking permissions on the permissible object:

```java linenums="1"
--8<-- "api/src/main/java/org/allaymc/api/permission/PermissionCalculator.java"
```

You are allowed to set a custom permission calculator for a permissible object. In the following example, we'll
create a permission calculator that always returns `Tristate.TRUE` for any permission (yeah that's hazardous xd)
and replace player's default permission calculator when they join the server:

```java linenums="1"
import org.allaymc.api.eventbus.event.server.PlayerSpawnEvent;
import org.allaymc.api.permission.PermissionCalculator;
import org.allaymc.api.permission.Tristate;

public class Example {
    @EventHandler
    public void onPlayerSpawn(PlayerSpawnEvent event) {
        event.getPlayer().getControlledEntity().setPermissionCalculator(new MyPermissionCalculator());
    }
    
    public static class MyPermissionCalculator implements PermissionCalculator {
        @Override
        public Tristate calculatePermission(String permission) {
            // Always return Tristate.TRUE for any permission!
            return Tristate.TRUE;
        }
    }
}
```

Above is a simple example of a permission calculator. You can do much more complicated things with it to implement
your own permission plugin.

## The Default Permission Calculator

By default, Allay uses a permission calculator for player where op players have all permissions and non-op players
have a limited set of permissions. Here is the source code of the `OpPermissionCalculator`:

```java linenums="1"
--8<-- "api/src/main/java/org/allaymc/api/permission/OpPermissionCalculator.java"
```

You are allowed to edit the `NON_OP_PERMISSIONS` set to change the default permission set for non-op players, and
that's actually what we did in [register-commands#add-permissions](register-commands#add-permissions)

## Use LuckPerms

You may be tired of the vanilla op/non-op permission system and want to use an advanced permission plugin to better
control your server. Allay officially supports [LuckPerms](https://luckperms.net/), which is the most popular permission
plugin for Minecraft servers. You can find the source code and download the pre-built LuckPerms jar for the Allay
platform [here](https://github.com/AllayMC/LuckPerms).

When LuckPerms is installed, it will replace the default permission calculator for players when they join the server.
LuckPerms does not care about player's op status, which invalidates the server's operator list. For more information
about how to use LuckPerms, please refer to their site.