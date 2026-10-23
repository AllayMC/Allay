package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code RemainArgNode} consumes all remaining tokens into a new string list
 * while preserving individual token boundaries. It advertises greedy message
 * syntax to clients but produces a list instead of joined text. Child
 * attachment is rejected because the matcher reserves all remaining input for
 * this terminal parameter.
 *
 * @author daoge_cmd
 */
public class RemainArgNode extends BaseNode {
    /**
     * Creates an unattached remaining-token node retaining the supplied parent
     * and omission list. Ordinary matching allocates a new result list, while
     * optional omission inserts the configured list directly without copying.
     */
    public RemainArgNode(String name, CommandNode parent, List<String> defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Consumes every remaining argument and appends a new list in input order.
     * Direct calls with exhausted input still succeed with an empty list;
     * optional tree selection instead inserts the configured default without
     * invoking this matcher.
     */
    @Override
    public boolean match(CommandContext context) {
        var args = new ArrayList<String>();
        while (context.haveUnhandledArg()) {
            args.add(context.popArg());
        }
        context.putResult(args);
        return true;
    }

    /**
     * Returns {@code Short.MAX_VALUE} as the finite branch bound for consuming
     * remaining tokens. The matcher itself continues until input ends, so this
     * bound constrains tree selection rather than its loop.
     */
    @Override
    public int getMaxArgCost() {
        // The greedy list is one result with many possible tokens, so branch
        // selection uses this broad finite bound rather than a one-token cost.
        return Short.MAX_VALUE;
    }

    /**
     * Creates client metadata advertising {@code CommandParam.MESSAGE_ROOT} for
     * greedy token consumption.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.MESSAGE_ROOT);
        return data;
    }

    /**
     * Rejects child attachment because this node consumes every remaining token
     * into its list result. The method always raises
     * {@code UnsupportedOperationException}; executors attach directly to this
     * terminal parameter.
     */
    @Override
    public CommandNode addLeaf(CommandNode leaf) {
        throw new UnsupportedOperationException("RemainArgNode can't add leaf");
    }
}
