package org.allaymc.server.command.defaults;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;

/**
 * Allay Project 2024/2/13
 *
 * @author daoge_cmd
 */
public class VersionCommand extends SimpleCommand {

    public VersionCommand() {
        super("version", TrKeys.A_COMMAND_VERSION_DESCRIPTION);
        aliases.add("ver");
        aliases.add("about");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .exec(context -> {
                    context.addOutput(TrKeys.A_API_IMPLEMENTED, AllayAPI.getInstance().getCoreName(), AllayAPI.API_VERSION);
                    return context.success();
                });
    }
}
