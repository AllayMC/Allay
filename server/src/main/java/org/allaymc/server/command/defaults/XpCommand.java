package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * {@code XpCommand} adds experience points or repeated level-sized increments
 * to selected players. An uppercase {@code L} suffix selects level mode, which
 * adds the current level's required experience on each iteration; nonpositive
 * level amounts perform no iterations but still report success.
 *
 * @author xingchentye
 */
public class XpCommand extends Command {

    /**
     * Creates the {@code /xp} command with its built-in authorization permission.
     */
    public XpCommand() {
        super("xp", TrKeys.MC_COMMANDS_XP_DESCRIPTION, Permissions.COMMAND_XP);
    }

    /**
     * Installs required amount text and player targets for experience changes.
     * Only uppercase {@code L} selects level mode. Empty selections fail, and
     * malformed or overflowing numeric text propagates {@link NumberFormatException}
     * before any target receives experience.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().str("amount").playerTarget("player").exec(context -> {
            String amountStr = context.getResult(0);
            Collection<EntityPlayer> players = context.getResult(1);

            if (players.isEmpty()) {
                context.addNoTargetMatchError();
                return context.fail();
            }

            boolean isLevel = amountStr.endsWith("L");
            int amount = Integer.parseInt(isLevel ? amountStr.substring(0, amountStr.length() - 1) : amountStr);

            for (EntityPlayer player : players) {
                if (isLevel) {
                    for (int i = 1; i <= amount; i++) {
                        player.addExperience(player.getRequiredExperienceForCurrentLevel());
                    }
                } else {
                    player.addExperience(amount);
                }
            }

            String playersName = players.stream()
                    .map(EntityPlayer::getDisplayName)
                    .collect(Collectors.joining(", "));
            context.addOutput(
                    isLevel ? TrKeys.MC_COMMANDS_XP_SUCCESS_LEVELS : TrKeys.MC_COMMANDS_XP_SUCCESS,
                    amount, playersName
            );

            return context.success();
        });
    }
}
