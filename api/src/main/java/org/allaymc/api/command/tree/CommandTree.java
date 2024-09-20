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

    /**
     * Parses the command tree with the given sender and arguments.
     *
     * @param sender The command sender.
     * @param args   The command arguments.
     *
     * @return The result of the command parsing.
     */
    CommandResult parse(CommandSender sender, String[] args);

    /**
     * Gets the root node of the command tree.
     *
     * @return The root node of the command tree.
     */
    CommandNode getRoot();

    /**
     * Gets all leaf nodes of the command tree.
     *
     * @return A list of all leaf nodes of the command tree.
     */
    List<CommandNode> getLeaves();

    interface CommandTreeFactory {
        /**
         * Creates a new instance of {@link CommandTree} using the provided {@link Command}.
         *
         * @param command The command to create the tree for.
         *
         * @return A new instance of {@link CommandTree}.
         */
        CommandTree create(Command command);
    }
}
