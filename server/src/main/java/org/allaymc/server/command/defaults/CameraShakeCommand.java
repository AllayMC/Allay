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
 * {@code CameraShakeCommand} sends camera-shake additions or stops to selected
 * players with client controllers. Its output names every selected player,
 * including simulated players skipped by the handler, and the command reports
 * success after dispatching the applicable controller calls.
 *
 * @author daoge_cmd
 */
public class CameraShakeCommand extends Command {
    /**
     * Creates the {@code /camerashake} command with its built-in authorization permission.
     */
    public CameraShakeCommand() {
        super("camerashake", TrKeys.MC_COMMANDS_SCREENSHAKE_DESCRIPTION, Permissions.COMMAND_CAMERASHAKE);
    }

    /**
     * Installs camera-shake addition and stop branches for player targets.
     * Addition takes intensity, duration in seconds, and a shake type; both
     * branches skip targets without actual player controllers when sending
     * requests, while retaining those targets in feedback.
     */
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
