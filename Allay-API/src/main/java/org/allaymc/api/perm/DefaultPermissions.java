package org.allaymc.api.perm;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.perm.tree.PermTree;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public interface DefaultPermissions {
    PermTree VISITOR =
            PermTree.create()
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "help")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "tell")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "me");

    PermTree MEMBER =
            PermTree.create()
                    .extendFrom(VISITOR)
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "help")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "tell")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "me");

    PermTree OPERATOR =
            PermTree.create()
                    .addPerm("*");
}
