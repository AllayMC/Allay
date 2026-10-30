package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * {@code RootNode} is the structural entry point for a command tree rather than
 * a consumable parameter. Its matcher always accepts without changing context
 * state, while parent navigation, default-value access, cost queries, and
 * parameter serialization raise {@code UnsupportedOperationException}. Empty
 * trees can execute an executor attached directly to this root.
 *
 * @author daoge_cmd
 */
public class RootNode extends BaseNode {

    /**
     * Creates a structural root with no parent, default, or attached children.
     */
    public RootNode() {
        super("ROOT", null, null);
    }

    /**
     * Returns {@code true} without consuming arguments or appending any parsed
     * result.
     */
    @Override
    public boolean match(CommandContext context) {
        return true;
    }

    /**
     * Rejects omission-default lookup because the root is structural rather
     * than an argument parameter. This operation always raises
     * {@code UnsupportedOperationException}; callers perform it only on
     * non-root parameter nodes.
     */
    @Override
    public Object getDefaultValue() {
        throw new UnsupportedOperationException("Cannot call getDefaultValue() on root node");
    }

    /**
     * Rejects parent lookup because the root is structural rather than an
     * argument parameter. This operation always raises
     * {@code UnsupportedOperationException}; callers perform it only on
     * non-root parameter nodes.
     */
    @Override
    public CommandNode parent() {
        throw new UnsupportedOperationException("Cannot call parent() on root node");
    }

    /**
     * Rejects upward navigation regardless of the supplied count because the
     * root is structural rather than an argument parameter. This operation
     * always raises {@code UnsupportedOperationException}; callers perform it
     * only on non-root parameter nodes.
     */
    @Override
    public CommandNode up(int count) {
        throw new UnsupportedOperationException("Cannot call up() on root node");
    }

    /**
     * Rejects parameter serialization because the root is structural rather
     * than an argument parameter. This operation always raises
     * {@code UnsupportedOperationException}; callers perform it only on
     * non-root parameter nodes.
     */
    @Override
    public CommandParamData toNetworkData() {
        throw new UnsupportedOperationException();
    }

    /**
     * Rejects per-node maximum-cost lookup because the root is structural
     * rather than an argument parameter. This operation always raises
     * {@code UnsupportedOperationException}; callers perform it only on
     * non-root parameter nodes.
     */
    @Override
    public int getMaxArgCost() {
        throw new UnsupportedOperationException("Cannot call getMaxArgCost() on root node");
    }

    /**
     * Rejects branch maximum-cost lookup because the root is structural rather
     * than an argument parameter. This operation always raises
     * {@code UnsupportedOperationException}; callers perform it only on
     * non-root parameter nodes.
     */
    @Override
    public int getMaxArgCostBranch() {
        throw new UnsupportedOperationException("Cannot call getMaxArgCostBranch() on root node");
    }

    /**
     * Rejects branch minimum-cost lookup because the root is structural rather
     * than an argument parameter. This operation always raises
     * {@code UnsupportedOperationException}; callers perform it only on
     * non-root parameter nodes.
     */
    @Override
    public int getMinArgCostBranch() {
        throw new UnsupportedOperationException("Cannot call getMinArgCostBranch() on root node");
    }
}
