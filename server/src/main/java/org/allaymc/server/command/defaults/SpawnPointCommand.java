package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.world.data.DimensionInfo;
import org.joml.Vector3d;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class SpawnPointCommand extends VanillaCommand {

    public SpawnPointCommand() {
        super("spawnpoint", TrKeys.MC_COMMANDS_SPAWNPOINT_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .pos("spawnPos")
                .exec((context, sender) -> {
                    var senderLoc = sender.getLocation();
                    if (senderLoc.dimension().getDimensionInfo() != DimensionInfo.OVERWORLD) {
                        context.addError("%" + TrKeys.MC_COMMANDS_SPAWNPOINT_WRONGDIMENSION);
                        return context.fail();
                    }

                    List<EntityPlayer> players = context.getResult(0);
                    Vector3d pos = context.getResult(1);

                    for (EntityPlayer player : players) {
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
