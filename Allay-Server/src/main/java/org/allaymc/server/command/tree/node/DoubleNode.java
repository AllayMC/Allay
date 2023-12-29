package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class DoubleNode extends BaseNode {
    public DoubleNode(String name, CommandNode parent) {
        super(name, parent);
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        var number = 0d;
        try {
            number = Double.parseDouble(arg);
        } catch (NumberFormatException e) {
            return false;
        }
        context.putResult(number);
        context.popArg();
        return true;
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.FLOAT);
        return data;
    }
}
