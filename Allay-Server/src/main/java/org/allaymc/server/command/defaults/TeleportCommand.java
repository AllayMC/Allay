package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;

/**
 * Allay Project 2024/2/15
 *
 * @author daoge_cmd
 */
public class TeleportCommand extends SimpleCommand {
    public TeleportCommand() {
        super("teleport", TrKeys.M_COMMANDS_TP_DESCRIPTION);
        aliases.add("tp");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        // TODO
    }
}
