package org.allaymc.api.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.HashMap;
import java.util.Set;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class EnumNode extends BaseNode {
    protected String[] values;
    public EnumNode(String name, CommandNode parent, Object defaultValue, String... values) {
        super(name, parent, defaultValue);
        this.values = values;
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        boolean matched = false;
        for (String value : values) {
            if (value.equals(arg)) {
                matched = true;
                break;
            }
        }
        if (matched) {
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
        var map = new HashMap<String, Set<CommandEnumConstraint>>();
        for (var value : values) {
            map.put(value, Set.of());
        }
        data.setEnumData(new CommandEnumData(name, map, false));
        data.setType(CommandParam.TEXT);
        return data;
    }
}
