package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.LangCode;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.GameMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class GameModeCommand extends Command {
    public GameModeCommand() {
        super("gamemode", TrKeys.MC_COMMANDS_GAMEMODE_DESCRIPTION, Permissions.COMMAND_FILL);
        aliases.add("gm");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .gameMode()
                .playerTarget("players").optional()
                .exec(context -> {
                    GameMode gameMode = context.getResult(0);
                    List<EntityPlayer> players = new ArrayList<>();
                    if (context.getArgCount() == 1) {
                        // target arg is missing
                        if (context.getSender() instanceof EntityPlayer player) {
                            players.add(player);
                        } else {
                            context.addInvalidExecutorError(SenderType.PLAYER);
                            return context.fail();
                        }
                    } else {
                        // target arg is filled, check if the target is matched
                        players.addAll(context.getResult(1));
                        if (players.isEmpty()) {
                            context.addNoTargetMatchError();
                            return context.fail();
                        }
                    }

                    var gameModeName = I18n.get().tr(gameMode.getTranslationKey(), LangCode.en_US);
                    for (var player : players) {
                        player.setGameMode(gameMode);

                        if (player.equals(context.getSender())) {
                            context.addOutput(TrKeys.MC_COMMANDS_GAMEMODE_SUCCESS_SELF, gameModeName);
                        } else {
                            context.addOutput(TrKeys.MC_COMMANDS_GAMEMODE_SUCCESS_OTHER, gameModeName, player.getDisplayName());
                            context.sendWhisperTo(player, TrKeys.MC_GAMEMODE_CHANGED, gameModeName);
                        }
                    }

                    return context.success();
                });
    }
}
