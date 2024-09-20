package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * @author daoge_cmd
 */
public class LongNode extends BaseNode {
    public LongNode(String name, CommandNode parent, long defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        var number = 0L;
        try {
            number = Long.parseLong(arg);
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
