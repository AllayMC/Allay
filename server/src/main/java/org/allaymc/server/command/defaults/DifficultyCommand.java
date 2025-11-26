package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.data.Difficulty;

/**
 * @author IWareQ
 */
public class DifficultyCommand extends Command {
    public DifficultyCommand() {
        super("difficulty", TrKeys.MC_COMMANDS_DIFFICULTY_DESCRIPTION, Permissions.COMMAND_DIFFICULTY);
    }

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
