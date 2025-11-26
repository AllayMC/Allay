package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.permission.Permissions;

/**
 * @author daoge_cmd
 */
public class PermissionCommand extends Command {

    public PermissionCommand() {
        super("permission", "Check a certain permission on the current command sender", Permissions.COMMAND_PERMISSION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("check")
                .str("permission")
                .exec(context -> {
                    context.addOutput(context.getSender().hasPermission(context.getResult(2)).name());
                    return context.success();
                });
    }
}
