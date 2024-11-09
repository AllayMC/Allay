package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3f;
import org.joml.Vector3f;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class TeleportCommand extends SimpleCommand {
    public TeleportCommand() {
        super("teleport", TrKeys.M_COMMANDS_TP_DESCRIPTION);
        aliases.add("tp");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .pos("pos")
                .exec((context,sender) -> {
                    Vector3f pos = context.getResult(0);
                    var loc = new Location3f(pos.x, pos.y, pos.z, context.getSender().getCmdExecuteLocation().dimension());

                    sender.teleport(loc);
                    context.addOutput(TrKeys.M_COMMANDS_TP_SUCCESS_COORDINATES, sender.getDisplayName(), pos.x, pos.y, pos.z);

                    return context.success();
                },SenderType.ENTITY)
                .root()
                .target("destination")
                .exec((context,sender) -> {
                    List<Entity> destination = context.getResult(0);

                    if (destination.isEmpty()) {
                        context.addError("%" + TrKeys.M_COMMANDS_GENERIC_NOTARGETMATCH);
                        return context.fail();
                    }

                    if (destination.size() > 1) {
                        context.addError("%" + TrKeys.M_COMMANDS_GENERIC_TOOMANYTARGETS);
                        return context.fail();
                    }

                    var destEntity = destination.getFirst();

                    sender.teleport(destEntity.getLocation());
                    context.addOutput(TrKeys.M_COMMANDS_TP_SUCCESS, sender.getDisplayName(), destEntity.getDisplayName());

                    return context.success();
                },SenderType.ENTITY)
                .root()
                .target("victims")
                .pos("pos")
                .exec(context -> {
                    List<Entity> victims = context.getResult(0);
                    Vector3f pos = context.getResult(1);
                    var loc = new Location3f(pos.x, pos.y, pos.z, context.getSender().getCmdExecuteLocation().dimension());
                    for (Entity victim : victims) {
                        victim.teleport(loc);
                        context.addOutput(TrKeys.M_COMMANDS_TP_SUCCESS_COORDINATES, victim.getDisplayName(), pos.x, pos.y, pos.z);
                    }

                    return context.success();
                })
                .up()
                .target("destination")
                .exec(context -> {
                    List<Entity> victims = context.getResult(0);
                    List<Entity> destination = context.getResult(1);

                    if (destination.isEmpty()) {
                        context.addError("%" + TrKeys.M_COMMANDS_GENERIC_NOTARGETMATCH);
                        return context.fail();
                    }

                    if (destination.size() > 1) {
                        context.addError("%" + TrKeys.M_COMMANDS_GENERIC_TOOMANYTARGETS);
                        return context.fail();
                    }

                    var destEntity = destination.getFirst();
                    for (Entity victim : victims) {
                        victim.teleport(destEntity.getLocation());
                        context.addOutput(TrKeys.M_COMMANDS_TP_SUCCESS, victim.getDisplayName(), destEntity.getDisplayName());
                    }

                    return context.success();
                });
    }
}
