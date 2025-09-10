package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.data.Difficulty;

/**
 * @author IWareQ
 */
public class DifficultyCommand extends VanillaCommand {
    public DifficultyCommand() {
        super("difficulty", TrKeys.MC_COMMANDS_DIFFICULTY_DESCRIPTION);
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
