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
public class StopSoundCommand extends Command {
    public StopSoundCommand() {
        super("stopsound", TrKeys.MC_COMMANDS_STOPSOUND_DESCRIPTION, Permissions.COMMAND_STOPSOUND);
    }

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
