package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.world.LightningStrikeEvent;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * {@code SummonCommand} requests entities in the executing entity's dimension,
 * using its location when coordinates are omitted. The handler rejects player
 * entities and unloaded destination chunks. Lightning uses the dimension's
 * strike operation so command-caused lightning follows its event path.
 *
 * @author daoge_cmd
 */
public class SummonCommand extends Command {

    /**
     * Creates the {@code /summon} command with its built-in authorization permission.
     */
    public SummonCommand() {
        super("summon", TrKeys.MC_COMMANDS_SUMMON_DESCRIPTION, Permissions.COMMAND_SUMMON);
    }

    /**
     * Installs entity-only summoning with an optional position and count {@code 1}.
     * The destination chunk must already be loaded. Nonpositive counts perform
     * no spawning but still report success, and lightning requests use
     * {@link LightningStrikeEvent.Cause#COMMAND}.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .entityType("entityType")
                .pos("pos").optional()
                .intNum("count", 1).optional()
                .exec((context, sender) -> {
                    var dim = sender.getCommandExecuteLocation().dimension();
                    EntityType<?> entityType = context.getResult(0);
                    if (entityType == EntityTypes.PLAYER) {
                        context.addError("%" + TrKeys.MC_COMMANDS_SUMMON_FAILED);
                        return context.fail();
                    }

                    Vector3dc pos = context.getResult(1);
                    if (pos == null) {
                        pos = sender.getCommandExecuteLocation();
                    }

                    // Flooring preserves the containing chunk for negative fractional destination coordinates.
                    var floorPos = pos.floor(new Vector3d());
                    if (dim.getChunkManager().getChunkByDimensionPos((int) floorPos.x(), (int) floorPos.z()) == null) {
                        context.addError("%" + TrKeys.MC_COMMANDS_SUMMON_OUTOFWORLD);
                        return context.fail();
                    }

                    int count = context.getResult(2);
                    for (var i = 1; i <= count; i++) {
                        // Lightning uses the strike operation so listeners
                        // observe a command-caused strike instead of ordinary
                        // entity creation alone.
                        if (entityType == EntityTypes.LIGHTNING_BOLT) {
                            dim.strikeLightning(pos.x(), pos.y(), pos.z(), LightningStrikeEvent.Cause.COMMAND);
                            continue;
                        }

                        var entity = entityType.createEntity(
                                EntityInitInfo.builder()
                                        .dimension(dim)
                                        .pos(pos)
                                        .rot(sender.getLocation().yaw(), sender.getLocation().pitch())
                                        .build()
                        );
                        dim.getEntityManager().addEntity(entity);
                    }

                    context.addOutput(TrKeys.MC_COMMANDS_SUMMON_SUCCESS);
                    return context.success();
                }, SenderType.ENTITY);
    }
}
