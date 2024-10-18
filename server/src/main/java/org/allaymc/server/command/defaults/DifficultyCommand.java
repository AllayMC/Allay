package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.Difficulty;

/**
 * @author IWareQ
 */
public class DifficultyCommand extends SimpleCommand {
    public DifficultyCommand() {
        super("difficulty", TrKeys.M_COMMANDS_DIFFICULTY_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().difficulty("difficulty").exec((context, entity) -> {
            Difficulty difficulty = context.getResult(0);
            entity.getWorld().getWorldData().setDifficulty(difficulty);
            context.addOutput(TrKeys.M_COMMANDS_DIFFICULTY_SUCCESS, difficulty);
            return context.success();
        }, SenderType.ENTITY);
    }
}
