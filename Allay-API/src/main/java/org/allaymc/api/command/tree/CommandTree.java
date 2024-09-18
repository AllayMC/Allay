package org.allaymc.api.command.tree;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;

import java.util.List;

/**
 * @author daoge_cmd
 */
public interface CommandTree {

    ApiInstanceHolder<CommandTreeFactory> FACTORY = ApiInstanceHolder.create();

    static CommandTree create(Command command) {
        return FACTORY.get().create(command);
    }

    CommandNode getRoot();

    List<CommandNode> getLeaves();

    CommandResult parse(CommandSender sender, String[] args);

    interface CommandTreeFactory {
        CommandTree create(Command command);
    }
}
