package org.allaymc.server.command.tree.node;

import com.google.common.collect.Lists;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.Collections;
import java.util.List;

/**
 * {@code TargetNode} resolves one selector token or online player name into a
 * list of live entities. Selector-shaped input uses {@code EntitySelectorAPI};
 * other input is looked up as a player name. A missing name is a successful
 * empty selection so executors can decide whether no targets is acceptable for
 * their command.
 *
 * @author daoge_cmd
 */
public class TargetNode extends BaseNode {

    /**
     * Creates an unattached target node retaining its parent and default entity
     * list. Optional omission inserts that shared list directly; ordinary
     * matching resolves candidates from the current server state and can supply
     * a shared empty list.
     */
    public TargetNode(String name, CommandNode parent, List<? extends Entity> defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Resolves the current token as a structurally recognized selector or a
     * player name, then filters and stores its entities. Selector syntax
     * failures return {@code false} without consuming input; unknown player
     * names succeed with a shared unmodifiable empty list.
     *
     * <p>
     * The {@code filterResult} hook runs before storage and consumption.
     * Resolved entity references remain live server objects; a subclass can
     * modify a mutable selection but must also tolerate the unmodifiable empty
     * selection. Optional defaults bypass matching and filtering entirely.
     */
    @Override
    public boolean match(CommandContext context) {
        List<Entity> entities;
        var token = context.queryArg();
        if (EntitySelectorAPI.getAPI().checkValid(token)) {
            try {
                entities = EntitySelectorAPI.getAPI().matchEntities(context.getSender(), token);
            } catch (SelectorSyntaxException exception) {
                // Structurally recognized selectors with invalid argument values are
                // mismatches, leaving their token available for the tree's syntax failure
                // instead of treating them as unresolved player names.

                return false;
            }
        } else {
            var player = Server.getInstance().getPlayerManager().getPlayerByName(token);
            if (player != null) {
                entities = Lists.newArrayList(player.getControlledEntity());
            } else {
                // A missing player name is still a parsed target expression. Executors
                // such as testfor inspect an empty selection and choose their own
                // no-target feedback instead of receiving a syntax mismatch here.
                entities = Collections.emptyList();
            }
        }
        filterResult(entities);
        context.putResult(entities);
        context.popArg();
        return true;
    }

    /**
     * Leaves the resolved entity list unchanged in this base implementation. A
     * selection can be mutable, but an unknown player name supplies a shared
     * unmodifiable empty list, so overrides must tolerate that case rather than
     * assuming mutable ownership. Optional default insertion bypasses this
     * hook.
     */
    protected void filterResult(List<Entity> entities) {
    }

    /**
     * Creates client metadata advertising {@code CommandParam.TARGET} for
     * entity selection input.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.TARGET);
        return data;
    }
}
