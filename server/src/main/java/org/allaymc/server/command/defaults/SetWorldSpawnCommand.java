package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.data.DimensionInfo;
import org.joml.Vector3d;
import org.joml.Vector3i;

/**
 * @author daoge_cmd
 */
public class SetWorldSpawnCommand extends VanillaCommand {
    public SetWorldSpawnCommand() {
        super("setworldspawn", TrKeys.MC_COMMANDS_SETWORLDSPAWN_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().pos("spawnPoint").exec((context, entity) -> {
            if (entity.getDimension().getDimensionInfo() != DimensionInfo.OVERWORLD) {
                context.addError("%" + TrKeys.MC_COMMANDS_SETWORLDSPAWN_WRONGDIMENSION);
                return context.fail();
            }

            Vector3d pos = context.getResult(0);
            var newSpawnPoint = new Vector3i((int) pos.x(), (int) pos.y(), (int) pos.z());
            entity.getWorld().getWorldData().setSpawnPoint(newSpawnPoint);
            context.addOutput(TrKeys.MC_COMMANDS_SETWORLDSPAWN_SUCCESS, newSpawnPoint.x(), newSpawnPoint.y(), newSpawnPoint.z());
            return context.success();
        }, SenderType.ENTITY);
    }
}
