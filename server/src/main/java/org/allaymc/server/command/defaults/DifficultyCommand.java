package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.data.Difficulty;

/**
 * {@code DifficultyCommand} requests a difficulty change in the executing
 * entity's world. Its command tree requires an entity sender and a parsed
 * difficulty value; feedback reports the request even when the world's
 * difficulty-change event is cancelled or changes the requested value.
 *
 * @author IWareQ
 */
public class DifficultyCommand extends Command {
    /**
     * Creates the {@code /difficulty} command with its built-in authorization permission.
     */
    public DifficultyCommand() {
        super("difficulty", TrKeys.MC_COMMANDS_DIFFICULTY_DESCRIPTION, Permissions.COMMAND_DIFFICULTY);
    }

    /**
     * Installs an entity-only handler for the required difficulty argument.
     * The handler requests the change through world data and reports success
     * without inspecting whether a listener cancelled or modified that request.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().difficulty("difficulty").exec((context, entity) -> {
            Difficulty difficulty = context.getResult(0);
            entity.getWorld().getWorldData().setDifficulty(difficulty);
            context.addOutput(TrKeys.MC_COMMANDS_DIFFICULTY_SUCCESS, difficulty);
            return context.success();
        }, SenderType.ENTITY);
    }
}
