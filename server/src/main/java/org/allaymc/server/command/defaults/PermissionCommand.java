package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.permission.Permission;
import org.allaymc.api.permission.PermissionGroup;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.TextFormat;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class PermissionCommand extends VanillaCommand {

    public PermissionCommand() {
        super("permission", "Manage player's permissions");
        aliases.add("perm");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("list")
                .exec(context -> {
                    context.getSender().sendMessage("List of registered permissions:");
                    Registries.PERMISSIONS
                            .getContent()
                            .values()
                            .stream()
                            .sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
                            .forEach(permission -> context.getSender().sendMessage(" - " + permission.getName() + ": " + TextFormat.GREEN + permission.getDescription()));

                    return context.success();
                })
                .root()
                .key("show")
                .playerTarget("target")
                .exec(context -> {
                    List<EntityPlayer> targets = context.getResult(1);
                    if (targets.size() > 1) {
                        context.addTooManyTargetsError();
                        return context.fail();
                    }

                    if (targets.isEmpty()) {
                        context.addPlayerNotFoundError();
                        return context.fail();
                    }

                    var target = targets.getFirst();
                    context.getSender().sendMessage("The player " + target.getDisplayName() + " has the following permissions:");
                    target.getPermissionGroup()
                            .getPermissions()
                            .stream()
                            .sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
                            .forEach(permission -> context.getSender().sendMessage(" - " + permission.getName() + ": " + TextFormat.GREEN + permission.getDescription()));
                    context.getSender().sendMessage("Parents: " + TextFormat.GREEN + target.getPermissionGroup().getParents().stream().map(PermissionGroup::getName).collect(Collectors.joining(", ")));

                    return context.success();
                })
                .root()
                .key("edit")
                .playerTarget("target")
                .str("permission")
                .bool("value")
                .exec(context -> {
                    List<EntityPlayer> targets = context.getResult(1);
                    if (targets.size() > 1) {
                        context.addTooManyTargetsError();
                        return context.fail();
                    }

                    if (targets.isEmpty()) {
                        context.addPlayerNotFoundError();
                        return context.fail();
                    }

                    var permission = Permission.get(context.getResult(2));
                    if (permission == null) {
                        context.getSender().sendMessage(TextFormat.RED + "Permission not found: " + context.getResult(2));
                        return context.fail();
                    }

                    boolean value = context.getResult(3);
                    var target = targets.getFirst();
                    target.setPermission(permission, value);
                    context.getSender().sendMessage("Set permission " + permission.getName() + " for player " + target.getDisplayName() + " to " + (value ? TextFormat.GREEN + "true" : TextFormat.RED + "false"));

                    return context.success();
                });
    }
}
