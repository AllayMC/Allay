package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.Map;
import java.util.Set;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class KeyNode extends BaseNode {

    public KeyNode(String key, CommandNode parent) {
        super(key, parent);
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        if (name.equals(arg)) {
            context.putResult(arg);
            context.popArg();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setEnumData(new CommandEnumData(name, Map.of(name, Set.of()), false));
        data.setType(CommandParam.TEXT);
        return data;
    }
}
