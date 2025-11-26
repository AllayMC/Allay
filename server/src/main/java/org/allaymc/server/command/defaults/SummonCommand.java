package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * @author daoge_cmd
 */
public class SummonCommand extends Command {

    public SummonCommand() {
        super("summon", TrKeys.MC_COMMANDS_SUMMON_DESCRIPTION, Permissions.COMMAND_SUMMON);
    }

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

                    var floorPos = pos.floor(new Vector3d());
                    if (dim.getChunkManager().getChunkByDimensionPos((int) floorPos.x(), (int) floorPos.z()) == null) {
                        context.addError("%" + TrKeys.MC_COMMANDS_SUMMON_OUTOFWORLD);
                        return context.fail();
                    }

                    int count = context.getResult(2);
                    for (var i = 1; i <= count; i++) {
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
