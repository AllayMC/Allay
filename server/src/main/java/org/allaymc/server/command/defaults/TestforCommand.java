package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code TestforCommand} reports entities matched by a target expression and
 * returns their count as command status. Its feedback prefers each entity's
 * display name and falls back to the entity-type identifier when that display
 * name is blank.
 *
 * @author daoge_cmd
 */
public class TestforCommand extends Command {

    /**
     * Creates the {@code /testfor} command with its built-in authorization permission.
     */
    public TestforCommand() {
        super("testfor", TrKeys.MC_COMMANDS_TESTFOR_DESCRIPTION, Permissions.COMMAND_TESTFOR);
    }

    /**
     * Installs target matching with a failed result for an empty selection.
     * Matched names are joined in selection order, blank display names use
     * type identifiers, and the successful status equals the matched entity
     * count.
     */
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
                // A blank display name would leave an unreadable entry in the
                // summary. Use the type identifier to identify that match.
                if (name.isBlank()) {
                    name = entity.getEntityType().getIdentifier().toString();
                }

                return name;
            }).collect(Collectors.joining(", ")));
            return context.success(entities.size());
        });
    }
}
