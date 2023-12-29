package org.allaymc.api.cmdv2.tree;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.cmdv2.CommandResult;
import org.allaymc.api.cmdv2.CommandSender;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandTree {

    ApiInstanceHolder<CommandTreeFactory> FACTORY = ApiInstanceHolder.of();

    static CommandTree create() {
        return FACTORY.get().create();
    }

    CommandNode getRoot();

    CommandResult parse(CommandSender sender, String[] args);

    interface CommandTreeFactory {
        CommandTree create();
    }
}
