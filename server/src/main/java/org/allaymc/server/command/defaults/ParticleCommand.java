package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.particle.CustomParticle;
import org.joml.Vector3d;

/**
 * {@code ParticleCommand} emits repeated custom-particle requests at one
 * position in the sender's execution dimension. The handler requires a positive
 * count and passes the supplied effect identifier to the particle wrapper
 * without checking whether clients provide that effect.
 *
 * @author daoge_cmd
 */
public class ParticleCommand extends Command {
    /**
     * Creates the {@code /particle} command with its built-in authorization permission.
     */
    public ParticleCommand() {
        super("particle", TrKeys.MC_COMMANDS_PARTICLE_DESCRIPTION, Permissions.COMMAND_PARTICLE);
    }

    /**
     * Installs particle emission with a default count of {@code 1}.
     * Counts below {@code 1} fail before emission; accepted requests repeat the
     * same custom particle at the parsed position and then report command
     * success without client-side effect validation.
     */
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
