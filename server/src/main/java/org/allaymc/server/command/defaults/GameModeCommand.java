package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.data.CommonEnums;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.cloudburstmc.protocol.bedrock.data.GameType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class GameModeCommand extends SimpleCommand {
    public GameModeCommand() {
        super("gamemode", TrKeys.M_COMMANDS_GAMEMODE_DESCRIPTION);
        aliases.add("gm");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .gameMode()
                .playerTarget("players")
                .optional()
                .exec(context -> {
                    GameType gameType = context.getResult(0);
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

                    for (var player : players) {
                        // Let client translate the game mode name as nesting translate key is not supported currently
                        var gameTypeName = "%" + I18n.get().toClientFriendlyStyle(CommonEnums.getGameTypeTrKey(gameType));
                        player.setGameType(gameType);

                        if (player == context.getSender()) {
                            context.addOutput(TrKeys.M_COMMANDS_GAMEMODE_SUCCESS_SELF, gameTypeName);
                        } else {
                            context.addOutput(TrKeys.M_COMMANDS_GAMEMODE_SUCCESS_OTHER, gameTypeName, player.getDisplayName());
                            context.sendWhisperTo(player, TrKeys.M_GAMEMODE_CHANGED, gameTypeName);
                        }
                    }

                    return context.success();
                });
    }
}
