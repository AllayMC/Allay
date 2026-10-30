package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.List;

/**
 * {@code PlayAnimationCommand} applies one animation request to every selected
 * entity. The handler packages the animation name, next state, stop expression,
 * and controller into a shared animation value, rejects an empty selection,
 * and reports completion after dispatching the requests.
 *
 * @author daoge_cmd
 */
public class PlayAnimationCommand extends Command {
    /**
     * Creates the {@code /playanimation} command with its built-in authorization permission.
     */
    public PlayAnimationCommand() {
        super("playanimation", TrKeys.MC_COMMANDS_PLAYANIMATION_DESCRIPTION, Permissions.COMMAND_PLAYANIMATION);
    }

    /**
     * Installs animation application with optional client animation parameters.
     * The next state defaults to {@code "default"}; stop expression and
     * controller default to empty strings. A nonempty target selection receives
     * the same constructed animation request.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .target("entity")
                .str("animation")
                .str("nextState", "default").optional()
                .str("stopExpression", "").optional()
                .str("controller", "").optional()
                .exec(context -> {
                    List<Entity> entities = context.getResult(0);
                    String animation = context.getResult(1);
                    String nextState = context.getResult(2);
                    String stopExpression = context.getResult(3);
                    String controller = context.getResult(4);

                    if (entities.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    var entityAnimation = new EntityAnimation(animation, nextState, controller, stopExpression);
                    for (Entity entity : entities) {
                        entity.applyAnimation(entityAnimation);
                    }

                    context.addOutput("%" + TrKeys.MC_COMMANDS_PLAYANIMATION_SUCCESS);
                    return context.success();
                });
    }
}
