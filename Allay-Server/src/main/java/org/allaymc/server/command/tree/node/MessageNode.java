package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * Allay Project 2024/1/5
 *
 * @author daoge_cmd
 */
public class MessageNode extends BaseNode {
    public MessageNode(String name, CommandNode parent, String defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    public boolean match(CommandContext context) {
        var builder = new StringBuilder();
        var index = context.getCurrentArgIndex();
        while (context.haveUnhandledArg()) {
            builder.append(context.popArg()).append(" ");
        }
        context.putResult(index, builder.toString().trim());
        return true;
    }

    @Override
    public int getMaxArgCost() {
        return Short.MAX_VALUE;
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.MESSAGE);
        return data;
    }

    @Override
    public CommandNode addLeaf(CommandNode leaf) {
        throw new UnsupportedOperationException("MessageNode can't add leaf");
    }
}
