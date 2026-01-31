package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.joml.Vector3d;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class TeleportCommand extends Command {
    public TeleportCommand() {
        super("teleport", TrKeys.MC_COMMANDS_TP_DESCRIPTION, Permissions.COMMAND_TELEPORT);
        aliases.add("tp");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .pos("pos")
                .doubleNum("pitch").optional()
                .doubleNum("yaw").optional()
                .exec((context, sender) -> {
                    Vector3d pos = context.getResult(0);
                    double pitch = context.getResult(1);
                    double yaw = context.getResult(2);
                    var loc = new Location3d(pos.x, pos.y, pos.z, pitch, yaw, context.getSender().getCommandExecuteLocation().dimension());

                    sender.teleport(loc);
                    context.addOutput(TrKeys.MC_COMMANDS_TP_SUCCESS_COORDINATES, sender.getDisplayName(), pos.x, pos.y, pos.z);

                    return context.success();
                }, SenderType.ENTITY)
                .root()
                .target("destination")
                .exec((context, sender) -> {
                    List<Entity> destination = context.getResult(0);
                    if (destination.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    if (destination.size() > 1) {
                        context.addTooManyTargetsError();
                        return context.fail();
                    }

                    var destEntity = destination.getFirst();

                    sender.teleport(destEntity.getLocation());
                    context.addOutput(TrKeys.MC_COMMANDS_TP_SUCCESS, sender.getDisplayName(), destEntity.getDisplayName());

                    return context.success();
                }, SenderType.ENTITY)
                .root()
                .target("victims")
                .pos("pos")
                .doubleNum("pitch").optional()
                .doubleNum("yaw").optional()
                .exec(context -> {
                    List<Entity> victims = context.getResult(0);
                    Vector3d pos = context.getResult(1);
                    double pitch = context.getResult(2);
                    double yaw = context.getResult(3);
                    var loc = new Location3d(pos.x, pos.y, pos.z, pitch, yaw, context.getSender().getCommandExecuteLocation().dimension());
                    for (Entity victim : victims) {
                        victim.teleport(loc);
                        context.addOutput(TrKeys.MC_COMMANDS_TP_SUCCESS_COORDINATES, victim.getDisplayName(), pos.x, pos.y, pos.z);
                    }

                    return context.success();
                })
                .up(3)
                .target("destination")
                .exec(context -> {
                    List<Entity> victims = context.getResult(0);
                    List<Entity> destination = context.getResult(1);
                    if (destination.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    if (destination.size() > 1) {
                        context.addTooManyTargetsError();
                        return context.fail();
                    }

                    var destEntity = destination.getFirst();
                    for (Entity victim : victims) {
                        victim.teleport(destEntity.getLocation());
                        context.addOutput(TrKeys.MC_COMMANDS_TP_SUCCESS, victim.getDisplayName(), destEntity.getDisplayName());
                    }

                    return context.success();
                });
    }
}
