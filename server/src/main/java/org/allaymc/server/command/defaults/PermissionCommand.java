package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.permission.Permissions;

/**
 * {@code PermissionCommand} exposes a permission-check branch for the active
 * command sender. The branch is intended to print the tristate name, but its
 * handler currently reads result index {@code 2} although its two parsed nodes
 * populate only indexes {@code 0} and {@code 1}.
 *
 * @author daoge_cmd
 */
public class PermissionCommand extends Command {

    /**
     * Creates the {@code /permission} command with its built-in authorization permission.
     */
    public PermissionCommand() {
        super("permission", "Check a certain permission on the current command sender", Permissions.COMMAND_PERMISSION);
    }

    /**
     * Installs the {@code check} keyword and required permission-name argument.
     * The handler currently requests result index {@code 2}, which is absent
     * from this branch's parsed results. The context supplies {@code null}, so
     * the calculator checks that value instead of the supplied permission name.
     */
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
