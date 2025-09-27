package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.Map;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BooleanNode extends BaseNode {
    public static Map<String, Set<CommandEnumConstraint>> BOOLEAN_ENUM_DATA_VALUE = Map.of(
            "true", Set.of(),
            "false", Set.of()
    );

    public static CommandEnumData BOOLEAN_ENUM_DATA = new CommandEnumData("Boolean", BOOLEAN_ENUM_DATA_VALUE, false);

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

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setEnumData(BOOLEAN_ENUM_DATA);
        return data;
    }
}
