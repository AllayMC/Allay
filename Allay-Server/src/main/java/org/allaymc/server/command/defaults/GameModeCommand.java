package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.data.CommonEnum;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * Allay Project 2024/1/6
 *
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
                .enums("gamemode", CommonEnum.GAMEMODE_ENUM)
                .target("player")
                .optional()
                .exec(context -> {
                    var gametype = CommonEnum.getGameTypeFromString(context.getFirstResult());
                    if (gametype == null) {
                        context.addSyntaxError(1);
                        return context.failed();
                    }
                    List<EntityPlayer> players = new ArrayList<>();
                    if (context.getArgCount() == 1) {
                        // 未填写"target"参数
                        if (context.getSender() instanceof EntityPlayer player) {
                            players.add(player);
                        } else {
                            context.addInvalidExecutorError(SenderType.PLAYER);
                            return context.failed();
                        }
                    } else {
                        // 有填写"target"参数，检查是否匹配到目标
                        players.addAll(context.getSecondResult());
                        if (players.isEmpty()) {
                            context.addNoTargetMatchError();
                            return context.failed();
                        }
                    }
                    for (var player : players) {
                        var gametypeName = I18n.get().tr(player.getLangCode(), CommonEnum.getGameTypeTrKey(gametype));
                        player.setGameType(gametype);
                        if (player == context.getSender()) {
                            context.addOutput(TrKeys.M_COMMANDS_GAMEMODE_SUCCESS_SELF, gametypeName);
                        } else {
                            context.addOutput(TrKeys.M_COMMANDS_GAMEMODE_SUCCESS_OTHER, gametypeName);
                            context.sendWhisperTo(player, TrKeys.M_GAMEMODE_CHANGED, gametypeName);
                        }
                    }
                    return context.success();
                });
    }
}