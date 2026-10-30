package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.joml.Vector3d;
import org.joml.Vector3i;

/**
 * {@code SetWorldSpawnCommand} changes the executing entity's world spawn from
 * the overworld. Its handler rejects other dimensions, converts supplied
 * coordinates to integers by truncation toward zero, and reports the resulting
 * block coordinates after updating the world's spawn point.
 *
 * @author daoge_cmd
 */
public class SetWorldSpawnCommand extends Command {
    /**
     * Creates the {@code /setworldspawn} command with its built-in authorization permission.
     */
    public SetWorldSpawnCommand() {
        super("setworldspawn", TrKeys.MC_COMMANDS_SETWORLDSPAWN_DESCRIPTION, Permissions.COMMAND_SETWORLDSPAWN);
    }

    /**
     * Installs an entity-only spawn update requiring an overworld executor.
     * The position argument is required, and integer casts truncate fractional
     * coordinates toward zero before the world data receives the new spawn
     * point.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().pos("spawnPoint").exec((context, entity) -> {
            if (entity.getDimension().getDimensionType() != DimensionTypes.OVERWORLD) {
                context.addError("%" + TrKeys.MC_COMMANDS_SETWORLDSPAWN_WRONGDIMENSION);
                return context.fail();
            }

            Vector3d pos = context.getResult(0);
            // Integer casts move negative fractional positions toward zero rather than their containing block.
            var newSpawnPoint = new Vector3i((int) pos.x(), (int) pos.y(), (int) pos.z());
            entity.getWorld().getWorldData().setSpawnPoint(newSpawnPoint);
            context.addOutput(TrKeys.MC_COMMANDS_SETWORLDSPAWN_SUCCESS, newSpawnPoint.x(), newSpawnPoint.y(), newSpawnPoint.z());
            return context.success();
        }, SenderType.ENTITY);
    }
}
