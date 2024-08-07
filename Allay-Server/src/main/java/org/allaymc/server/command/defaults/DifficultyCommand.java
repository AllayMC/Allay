package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.Difficulty;

/**
 * Allay Project 13/07/2024
 *
 * @author IWareQ
 */
public class DifficultyCommand extends SimpleCommand {
    public DifficultyCommand() {
        super("difficulty", TrKeys.M_COMMANDS_DIFFICULTY_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().difficultyNode("difficulty").exec((context, player) -> {
            Difficulty difficulty = context.getResult(0);
            player.getWorld().getWorldData().setDifficulty(difficulty);
            context.addOutput(TrKeys.M_COMMANDS_DIFFICULTY_SUCCESS, difficulty);
            return context.success();
        }, SenderType.PLAYER);
    }
}
