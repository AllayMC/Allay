package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xingchentye
 */
public class XpCommand extends SimpleCommand {

    public XpCommand() {
        super("xp", TrKeys.M_COMMANDS_XP_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .str("amount")
                .playerTarget("player")
                .exec(context -> {
                    String amountStr = context.getResult(0);
                    Collection<EntityPlayer> players = context.getResult(1);
                    List<String> playerNameList = players.stream().map(EntityPlayer::getOriginName).collect(Collectors.toList());

                    if (players.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    boolean isLevel = amountStr.endsWith("L");
                    int amount = Integer.parseInt(isLevel ? amountStr.substring(0, amountStr.length() - 1) : amountStr);

                    for (EntityPlayer player : players) {
                        if (isLevel) {
                            for (int i = 1; i <= amount; i++) {
                                player.addExperience(player.getRequireExperienceForCurrentLevel());
                            }
                            continue;
                        }
                        player.addExperience(amount);
                    }

                    if (isLevel) {
                        context.addOutput(TrKeys.M_COMMANDS_XP_SUCCESS_LEVELS, amount, String.join(", ", playerNameList));
                    } else {
                        context.addOutput(TrKeys.M_COMMANDS_XP_SUCCESS, amount, String.join(", ", playerNameList));
                    }

                    return context.success();
                });
    }
}