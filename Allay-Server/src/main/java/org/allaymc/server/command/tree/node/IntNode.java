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
public class IntNode extends BaseNode {

    public IntNode(String name, CommandNode parent) {
        super(name, parent);
    }

    @Override
    public Object getDefaultValue() {
        return 0;
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        var number = 0;
        try {
            number = Integer.parseInt(arg);
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
        data.setType(CommandParam.INT);
        return data;
    }
}