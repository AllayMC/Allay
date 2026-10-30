package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * {@code MessageNode} consumes all remaining argument tokens into one message
 * string for a terminal command path. Each token passes through
 * {@code processArg}, the processed values are joined with spaces, and outer
 * whitespace is trimmed. The node rejects attached children because greedy
 * consumption leaves no later parameter input.
 *
 * @author daoge_cmd
 */
public class MessageNode extends BaseNode {
    /**
     * Creates an unattached greedy message node retaining its parent and
     * omission default. The stored default is used only after optionality is
     * enabled; ordinary matching joins all remaining argument tokens.
     */
    public MessageNode(String name, CommandNode parent, String defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Consumes every remaining token, processes each through
     * {@code processArg}, and appends one trimmed message result. Direct calls
     * with no input still succeed with {@code ""}; tree selection normally uses
     * the optional default when input is exhausted.
     */
    @Override
    public boolean match(CommandContext context) {
        var builder = new StringBuilder();
        while (context.haveUnhandledArg()) {
            builder.append(processArg(context.popArg())).append(" ");
        }
        context.putResult(builder.toString().trim());
        return true;
    }

    /**
     * Returns one consumed token unchanged before the message join operation.
     */
    protected String processArg(String arg) {
        return arg;
    }

    /**
     * Returns {@code Short.MAX_VALUE} as the finite bound for greedy message
     * consumption. Branch-cost checks use this upper bound even though the
     * matcher itself loops until all remaining arguments are consumed.
     */
    @Override
    public int getMaxArgCost() {
        // This finite selection bound admits ordinary multiword messages without
        // deriving a token limit from the one-result shape of the greedy matcher.
        return Short.MAX_VALUE;
    }

    /**
     * Creates client metadata advertising {@code CommandParam.MESSAGE_ROOT} for
     * greedy message input.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.MESSAGE_ROOT);
        return data;
    }

    /**
     * Rejects child attachment because this matcher consumes all remaining
     * input for a terminal message. The method always raises
     * {@code UnsupportedOperationException}; callers attach an executor
     * directly to this node.
     */
    @Override
    public CommandNode addLeaf(CommandNode leaf) {
        throw new UnsupportedOperationException("MessageNode can't add leaf");
    }
}
