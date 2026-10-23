package org.allaymc.api.command.tree;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;

import java.util.List;

/**
 * {@code CommandTree} associates a command with its root argument node and
 * exposes parsing and terminal-path discovery. Each parse receives arguments
 * without the command name and produces an execution result. Node construction
 * and executor registration configure the tree before callers use it for
 * execution.
 *
 * @author daoge_cmd
 */
public interface CommandTree {

    /**
     * {@code FACTORY} holds the installed tree factory. Server initialization
     * must provide it before command constructors call {@code create} to obtain
     * their parsing tree.
     */
    AllayAPI.APIInstanceHolder<Factory> FACTORY = AllayAPI.APIInstanceHolder.create();

    /**
     * Creates a parsing tree associated with the supplied command through the
     * installed factory.
     */
    static CommandTree create(Command command) {
        return FACTORY.get().create(command);
    }

    /**
     * Parses the slash-free argument tokens and invokes the selected terminal
     * executor or redirect path. Parsing uses a fresh invocation context; a
     * failed path returns its accumulated feedback, while executor failures may
     * propagate to the registry.
     */
    CommandResult parse(CommandSender sender, String[] args);

    /**
     * Returns the structural root from which command argument parsing begins.
     */
    CommandNode getRoot();

    /**
     * Returns terminal nodes in traversal order, including the root when the
     * root itself is terminal. The server creates a new list for each call
     * while retaining references to existing nodes.
     */
    List<CommandNode> getLeaves();

    /**
     * {@code Factory} creates command-associated trees for the installed
     * command parsing implementation.
     */
    interface Factory {
        /**
         * Creates a new parsing tree retaining the command supplied by its
         * caller.
         */
        CommandTree create(Command command);
    }
}
