package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author xingchentye
 */
public class XpCommand extends VanillaCommand {

    public XpCommand() {
        super("xp", TrKeys.MC_COMMANDS_XP_DESCRIPTION);
    }

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