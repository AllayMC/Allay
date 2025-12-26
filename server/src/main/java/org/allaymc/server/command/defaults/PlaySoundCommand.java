package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.sound.CustomSound;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class PlaySoundCommand extends Command {
    public PlaySoundCommand() {
        super("playsound", TrKeys.MC_COMMANDS_PLAYSOUND_DESCRIPTION, Permissions.COMMAND_PLAYSOUND);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .str("sound")
                .playerTarget("player").optional()
                .pos("position").optional()
                .floatNum("volume", 1.0f).optional()
                .floatNum("pitch", 1.0f).optional()
                .floatNum("minimumVolume", 0.0f).optional()
                .exec((context, sender) -> {
                    String sound = context.getResult(0);
                    Collection<EntityPlayer> players = context.getResult(1);
                    Vector3d position = context.getResult(2);
                    float volume = context.getResult(3);
                    float pitch = context.getResult(4);
                    float minimumVolume = context.getResult(5);

                    if (minimumVolume < 0) {
                        context.addError("%" + TrKeys.MC_COMMANDS_GENERIC_NUM_TOOSMALL, String.valueOf(minimumVolume), "0");
                        return context.fail();
                    }

                    // If no players specified, use sender if sender is a player
                    if (players.isEmpty()) {
                        if (sender instanceof EntityPlayer player) {
                            players = List.of(player);
                        } else {
                            context.addNoTargetMatchError();
                            return context.fail();
                        }
                    }

                    // If no position specified, use the first target's position
                    if (position == null) {
                        var firstPlayer = players.iterator().next();
                        var loc = firstPlayer.getLocation();
                        position = new Vector3d(loc.x(), loc.y(), loc.z());
                    }

                    double maxDistance = volume > 1 ? volume * 16 : 16;

                    List<String> successes = new ArrayList<>();
                    for (EntityPlayer player : players) {
                        var controller = player.getController();
                        if (controller == null) continue;

                        String name = player.getDisplayName();
                        var playerLoc = player.getLocation();

                        if (position.distance(playerLoc.x(), playerLoc.y(), playerLoc.z()) > maxDistance) {
                            if (minimumVolume <= 0) {
                                context.addError("%" + TrKeys.MC_COMMANDS_PLAYSOUND_PLAYERTOOFAR, name);
                                continue;
                            }

                            // Play sound at player's position with minimum volume
                            controller.viewSound(new CustomSound(sound, minimumVolume, pitch), playerLoc, false);
                        } else {
                            // Play sound at specified position with specified volume
                            controller.viewSound(new CustomSound(sound, volume, pitch), position, false);
                        }

                        successes.add(name);
                    }

                    if (successes.isEmpty()) {
                        return context.fail();
                    }

                    context.addOutput("%" + TrKeys.MC_COMMANDS_PLAYSOUND_SUCCESS, sound, String.join(", ", successes));
                    return context.success();
                }, SenderType.ANY);
    }
}
