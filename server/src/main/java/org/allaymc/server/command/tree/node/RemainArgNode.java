package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.ArrayList;

/**
 * @author daoge_cmd
 */
public class RemainArgNode extends BaseNode {
    public RemainArgNode(String name, CommandNode parent, Object defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    public boolean match(CommandContext context) {
        var args = new ArrayList<String>();
        while (context.haveUnhandledArg()) {
            args.add(context.popArg());
        }
        context.putResult(args);
        return true;
    }

    @Override
    public int getMaxArgCost() {
        return Short.MAX_VALUE;
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.MESSAGE_ROOT);
        return data;
    }

    @Override
    public CommandNode addLeaf(CommandNode leaf) {
        throw new UnsupportedOperationException("RemainArgNode can't add leaf");
    }
}
