package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class FogCommand extends Command {
    public FogCommand() {
        super("fog", TrKeys.MC_COMMANDS_FOG_DESCRIPTION, Permissions.COMMAND_FOG);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .key("push")
                .str("fogId")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    String fogId = context.getResult(2);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            player.getController().pushFog(fogId);
                        }
                    });
                    var playerNames = players.stream().map(EntityPlayer::getDisplayName).collect(Collectors.joining(", "));
                    context.addOutput(TrKeys.MC_COMMANDS_FOG_SUCCESS_PUSH, playerNames, fogId);
                    return context.success();
                })
                .up(2)
                .key("pop")
                .str("fogId")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    String fogId = context.getResult(2);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            if (!player.getController().popFog(fogId)) {
                                context.addOutput(TrKeys.MC_COMMANDS_FOG_INVALIDFOGID, fogId);
                            }
                        }
                    });
                    var playerNames = players.stream().map(EntityPlayer::getDisplayName).collect(Collectors.joining(", "));
                    context.addOutput(TrKeys.MC_COMMANDS_FOG_SUCCESS_POP, playerNames, fogId);
                    return context.success();
                })
                .up(2)
                .key("remove")
                .str("fogId")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    String fogId = context.getResult(2);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            if (player.getController().removeFog(fogId) == 0) {
                                context.addOutput(TrKeys.MC_COMMANDS_FOG_INVALIDFOGID, fogId);
                            }
                        }
                    });
                    var playerNames = players.stream().map(EntityPlayer::getDisplayName).collect(Collectors.joining(", "));
                    context.addOutput(TrKeys.MC_COMMANDS_FOG_SUCCESS_REMOVE, playerNames, fogId);
                    return context.success();
                });
    }
}
