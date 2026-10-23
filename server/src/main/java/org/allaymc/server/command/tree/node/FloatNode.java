package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * {@code FloatNode} parses one command argument as a single-precision
 * floating-point value and appends the boxed result. Parsing failure leaves
 * argument and result cursors unchanged so sibling nodes can still inspect the
 * token. Java's parser also accepts its supported nonfinite values; the node
 * adds no finite-value restriction. Client metadata uses
 * {@code CommandParam.FLOAT} syntax for this parameter.
 *
 * @author daoge_cmd
 */
public class FloatNode extends BaseNode {
    /**
     * Creates an unattached numeric node with the supplied name, parent, and
     * {@code float} omission default. The default is stored for later optional
     * selection; callers must attach the node and enable optionality
     * separately.
     */
    public FloatNode(String name, CommandNode parent, float defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Parses the current token as a {@code float} and appends a {@code Float}
     * result before consuming that token. A {@code NumberFormatException}
     * returns {@code false} without modifying either cursor or adding
     * numeric-error feedback.
     */
    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        var number = 0f;
        try {
            number = Float.parseFloat(arg);
        } catch (NumberFormatException e) {
            // A numeric mismatch must leave this token available to later sibling
            // matchers, so parsing finishes before any context result or cursor changes.
            return false;
        }
        context.putResult(number);
        context.popArg();
        return true;
    }

    /**
     * Creates client parameter metadata using {@code CommandParam.FLOAT} syntax
     * with this node's name and optionality.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.FLOAT);
        return data;
    }
}
