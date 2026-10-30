package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * {@code StopSoundCommand} sends named-sound or all-sound stop requests to
 * selected player controllers. An omitted sound name selects all sounds.
 * Targets without controllers are skipped during dispatch but remain in the
 * completion message's list of selected player names.
 *
 * @author daoge_cmd
 */
public class StopSoundCommand extends Command {
    /**
     * Creates the {@code /stopsound} command with its built-in authorization permission.
     */
    public StopSoundCommand() {
        super("stopsound", TrKeys.MC_COMMANDS_STOPSOUND_DESCRIPTION, Permissions.COMMAND_STOPSOUND);
    }

    /**
     * Installs sound stopping with an optional name defaulting to an empty string.
     * Empty selections fail before dispatch; an empty name stops all sounds.
     * Nonempty selections succeed even when every selected player lacks a
     * controller and receives no stop request.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .str("sound", "").optional()
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    String sound = context.getResult(1);

                    if (players.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    boolean stopAll = sound.isEmpty();

                    for (EntityPlayer player : players) {
                        var controller = player.getController();
                        if (controller == null) continue;

                        if (stopAll) {
                            controller.stopAllSounds();
                        } else {
                            controller.stopSound(sound);
                        }
                    }

                    String playerNames = players.stream()
                            .map(EntityPlayer::getDisplayName)
                            .collect(Collectors.joining(", "));

                    if (stopAll) {
                        context.addOutput("%" + TrKeys.MC_COMMANDS_STOPSOUND_SUCCESS_ALL, playerNames);
                    } else {
                        context.addOutput("%" + TrKeys.MC_COMMANDS_STOPSOUND_SUCCESS, sound, playerNames);
                    }

                    return context.success();
                });
    }
}
