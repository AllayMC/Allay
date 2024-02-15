package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3f;
import org.joml.Vector3f;

import java.util.List;

/**
 * Allay Project 2024/2/15
 *
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
                .target("targets")
                .pos("pos")
                .exec(context -> {
                    List<Entity> targets = context.getFirstResult();
                    Vector3f pos = context.getSecondResult();
                    var loc = new Location3f(pos.x, pos.y, pos.z, context.getSender().getCmdExecuteLocation().dimension());
                    for (Entity target : targets) {
                        target.teleport(loc);
                        // TODO: Entity name
                        context.addOutput(TrKeys.M_COMMANDS_TP_SUCCESS_COORDINATES, target.getEntityType().getIdentifier(), pos.x, pos.y, pos.z);
                    }
                    return context.success();
                });
    }
}
