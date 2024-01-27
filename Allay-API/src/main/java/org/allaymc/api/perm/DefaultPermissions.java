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
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "help");

    PermTree MEMBER =
            PermTree.create()
                    .extendFrom(VISITOR)
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "me");

    PermTree OPERATOR =
            PermTree.create()
                    .extendFrom(MEMBER)
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gamemode")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gm")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gamerule")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gr")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gametest")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "gt")
                    .addPerm(SimpleCommand.COMMAND_PERM_PREFIX + "stop");
}
