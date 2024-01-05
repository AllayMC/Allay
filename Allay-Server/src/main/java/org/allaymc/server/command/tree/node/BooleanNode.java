package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
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
public class BooleanNode extends BaseNode {
    public BooleanNode(String name, CommandNode parent, boolean defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        var bool = false;
        if (arg.equals("true")) {
            bool = true;
        } else if (!arg.equals("false")) {
            return false;
        }
        context.putResult(bool);
        context.popArg();
        return true;
    }

    public static Map<String, Set<CommandEnumConstraint>> BOOLEAN_ENUM_DATA_VALUE = Map.of(
            "true", Set.of(),
            "false", Set.of()
    );

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setEnumData(new CommandEnumData(name, BOOLEAN_ENUM_DATA_VALUE, false));
        data.setType(CommandParam.TEXT);
        return data;
    }
}
