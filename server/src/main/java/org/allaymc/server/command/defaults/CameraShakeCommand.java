package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.CameraShakeType;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class CameraShakeCommand extends Command {
    public CameraShakeCommand() {
        super("camerashake", TrKeys.MC_COMMANDS_SCREENSHAKE_DESCRIPTION, Permissions.COMMAND_CAMERASHAKE);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("add")
                .playerTarget("player")
                .floatNum("intensity")
                .floatNum("seconds")
                .enumsIgnoreCase("shakeType", "rotational", "CameraShakeType", new String[]{"positional", "rotational"})
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(1);
                    float intensity = context.getResult(2);
                    float seconds = context.getResult(3);
                    String shakeTypeStr = context.getResult(4);
                    CameraShakeType shakeType = shakeTypeStr.equalsIgnoreCase("positional")
                            ? CameraShakeType.POSITIONAL
                            : CameraShakeType.ROTATIONAL;
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            player.getController().shakeCamera(shakeType, intensity, seconds);
                        }
                    });
                    var playerNames = players.stream().map(EntityPlayer::getDisplayName).collect(Collectors.joining(", "));
                    context.addOutput(TrKeys.MC_COMMANDS_SCREENSHAKE_SUCCESS, playerNames);
                    return context.success();
                })
                .root()
                .key("stop")
                .playerTarget("player")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(1);
                    players.forEach(player -> {
                        if (player.isActualPlayer()) {
                            player.getController().stopCameraShake();
                        }
                    });
                    var playerNames = players.stream().map(EntityPlayer::getDisplayName).collect(Collectors.joining(", "));
                    context.addOutput(TrKeys.MC_COMMANDS_SCREENSHAKE_SUCCESSSTOP, playerNames);
                    return context.success();
                });
    }
}
