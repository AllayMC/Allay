package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.particle.CustomParticle;
import org.joml.Vector3d;

/**
 * @author daoge_cmd
 */
public class ParticleCommand extends Command {
    public ParticleCommand() {
        super("particle", TrKeys.MC_COMMANDS_PARTICLE_DESCRIPTION, Permissions.COMMAND_PARTICLE);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .str("effect")
                .pos("position")
                .intNum("count", 1).optional()
                .exec(context -> {
                    String effect = context.getResult(0);
                    Vector3d position = context.getResult(1);
                    int count = context.getResult(2);

                    if (count < 1) {
                        context.addError("%" + TrKeys.MC_COMMANDS_GENERIC_NUM_TOOSMALL, String.valueOf(count), "1");
                        return context.fail();
                    }

                    var dimension = context.getSender().getCommandExecuteLocation().dimension();
                    var particle = new CustomParticle(effect);

                    for (int i = 0; i < count; i++) {
                        dimension.addParticle(position, particle);
                    }

                    context.addOutput("%" + TrKeys.MC_COMMANDS_PARTICLE_SUCCESS, effect, String.valueOf(count));
                    return context.success();
                });
    }
}
