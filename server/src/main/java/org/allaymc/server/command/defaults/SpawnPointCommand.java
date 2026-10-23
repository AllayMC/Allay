package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.joml.Vector3d;

import java.util.List;

/**
 * {@code SpawnPointCommand} assigns personal spawn locations to selected players
 * from a player executor in the overworld. Each stored location uses the
 * executor's dimension and rotation with truncated target coordinates; feedback
 * instead displays the original coordinates rounded to two decimals.
 *
 * @author daoge_cmd
 */
public class SpawnPointCommand extends Command {

    /**
     * Creates the {@code /spawnpoint} command with its built-in authorization permission.
     */
    public SpawnPointCommand() {
        super("spawnpoint", TrKeys.MC_COMMANDS_SPAWNPOINT_DESCRIPTION, Permissions.COMMAND_SPAWNPOINT);
    }

    /**
     * Installs player-only spawn assignment with required targets and coordinates.
     * The executor must be in the overworld. Every selected player receives
     * the executor's dimension and rotation, while fractional coordinates are
     * truncated toward zero for storage.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .pos("spawnPos")
                .exec((context, sender) -> {
                    var senderLoc = sender.getLocation();
                    if (senderLoc.dimension().getDimensionType() != DimensionTypes.OVERWORLD) {
                        context.addError("%" + TrKeys.MC_COMMANDS_SPAWNPOINT_WRONGDIMENSION);
                        return context.fail();
                    }

                    List<EntityPlayer> players = context.getResult(0);
                    Vector3d pos = context.getResult(1);

                    for (EntityPlayer player : players) {
                        // Stored block coordinates truncate toward zero,
                        // while the following feedback preserves rounded
                        // fractional input for display.
                        player.setSpawnPoint(new Location3i(
                                (int) pos.x(), (int) pos.y(), (int) pos.z(),
                                senderLoc.pitch(), senderLoc.yaw(),
                                senderLoc.dimension())
                        );
                        context.addOutput(
                                TrKeys.MC_COMMANDS_SPAWNPOINT_SUCCESS_SINGLE, player.getDisplayName(),
                                MathUtils.round(pos.x(), 2), MathUtils.round(pos.y(), 2), MathUtils.round(pos.z(), 2)
                        );
                    }

                    return context.success();
                }, SenderType.PLAYER);
    }
}
