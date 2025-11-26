package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class TestforCommand extends Command {

    public TestforCommand() {
        super("testfor", TrKeys.MC_COMMANDS_TESTFOR_DESCRIPTION, Permissions.COMMAND_TESTFOR);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().target("victim").exec(context -> {
            List<Entity> entities = context.getResult(0);
            if (entities.isEmpty()) {
                context.addNoTargetMatchError();
                return context.fail();
            }

            context.addOutput(TrKeys.MC_COMMANDS_TESTFOR_SUCCESS, entities.stream().map(entity -> {
                var name = entity.getDisplayName();
                if (name.isBlank()) {
                    name = entity.getEntityType().getIdentifier().toString();
                }

                return name;
            }).collect(Collectors.joining(", ")));
            return context.success(entities.size());
        });
    }
}
