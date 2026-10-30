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
 * {@code PlaySoundCommand} sends a custom sound to selected player controllers.
 * The first target supplies an omitted position, and an empty selection uses
 * the sender when it is an {@link EntityPlayer} instance. Distant recipients
 * either receive the minimum-volume fallback at their own positions or produce
 * individual distance errors.
 *
 * <p>
 * Volume above {@code 1} extends the base sixteen-block hearing range by the
 * same multiplier. For finite {@code minimumVolume}, a target beyond that range
 * remains eligible only when the minimum is positive, and the sound moves to
 * that target's position. Targets without controllers receive no request, and
 * distance errors do not prevent later recipients from being considered. The
 * command succeeds when at least one controller receives a request, even if
 * other selected recipients were skipped or too far away.
 *
 * @author daoge_cmd
 */
public class PlaySoundCommand extends Command {
    /**
     * Creates the {@code /playsound} command with its built-in authorization permission.
     */
    public PlaySoundCommand() {
        super("playsound", TrKeys.MC_COMMANDS_PLAYSOUND_DESCRIPTION, Permissions.COMMAND_PLAYSOUND);
    }

    /**
     * Installs sound delivery with volume and pitch {@code 1} and minimum volume
     * {@code 0}. Negative minimum volume fails before delivery. Targets without
     * controllers are skipped, distance failures do not stop later targets,
     * and at least one dispatched sound is required for overall success.
     *
     * <p>
     * Omitted coordinates use the first selected target's location, including
     * when that target has no controller. The handler then evaluates distance
     * separately for each recipient. A positive finite minimum permits a
     * distant target to receive the relocated fallback; nonpositive finite
     * values add an error instead. {@code Float.NaN} passes both minimum-volume
     * rejection checks and reaches the fallback unchanged, because this handler
     * has no finite-value check.
     */
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

                    // Player senders provide a recipient when selection is
                    // empty; other senders have no controller to receive
                    // the fallback.
                    if (players.isEmpty()) {
                        if (sender instanceof EntityPlayer player) {
                            players = List.of(player);
                        } else {
                            context.addNoTargetMatchError();
                            return context.fail();
                        }
                    }

                    if (position == null) {
                        var firstPlayer = players.iterator().next();
                        var loc = firstPlayer.getLocation();
                        position = new Vector3d(loc.x(), loc.y(), loc.z());
                    }

                    // Volume above one extends the base sixteen-block hearing range.
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

                            // Relocate the sound so distant recipients that
                            // pass the minimum-volume check receive a request.
                            // NaN also passes that check and is forwarded unchanged.
                            controller.viewSound(new CustomSound(sound, minimumVolume, pitch), playerLoc, false);
                        } else {
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
