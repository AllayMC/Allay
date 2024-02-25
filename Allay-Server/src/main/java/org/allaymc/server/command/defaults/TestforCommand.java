package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.i18n.TrKeys;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Allay Project 2024/2/25
 *
 * @author daoge_cmd
 */
public class TestforCommand extends SimpleCommand {

    public TestforCommand() {
        super("testfor", TrKeys.M_COMMANDS_TESTFOR_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .target("victim")
                .exec(context -> {
                    List<Entity> entities = context.getFirstResult();
                    if (entities.isEmpty()) {
                        context.addError(TrKeys.M_COMMANDS_GENERIC_NOTARGETMATCH);
                        return context.failed();
                    }
                    context.addOutput(TrKeys.M_COMMANDS_TESTFOR_SUCCESS, entities.stream().map(entity -> {
                        var name = entity.getDisplayName();
                        if (name.isBlank()) name = entity.getEntityType().getIdentifier().toString();
                        return name;
                    }).collect(Collectors.joining(", ")));
                    return context.success(entities.size());
                });
    }
}
