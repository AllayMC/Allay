package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.utils.MathUtils;
import org.allaymc.api.world.DimensionInfo;
import org.joml.Vector3f;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class SpawnPointCommand extends SimpleCommand {

    public SpawnPointCommand() {
        super("spawnpoint", TrKeys.M_COMMANDS_SPAWNPOINT_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .pos("spawnPos")
                .exec((context, sender) -> {
                    var senderLoc = sender.getLocation();
                    if (senderLoc.dimension().getDimensionInfo() != DimensionInfo.OVERWORLD) {
                        context.addError("%" + TrKeys.M_COMMANDS_SPAWNPOINT_WRONGDIMENSION);
                        return context.fail();
                    }

                    List<EntityPlayer> players = context.getResult(0);
                    Vector3f pos = context.getResult(1);

                    for (EntityPlayer player : players) {
                        player.setSpawnPoint(new Location3i(
                                (int) pos.x(), (int) pos.y(), (int) pos.z(),
                                senderLoc.pitch(), senderLoc.yaw(), senderLoc.headYaw(),
                                senderLoc.dimension())
                        );
                        context.addOutput(
                                TrKeys.M_COMMANDS_SPAWNPOINT_SUCCESS_SINGLE, player.getDisplayName(),
                                MathUtils.round(pos.x(), 2), MathUtils.round(pos.y(), 2), MathUtils.round(pos.z(), 2)
                        );
                    }

                    return context.success();
                }, SenderType.PLAYER);
    }
}
