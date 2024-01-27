package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class EnumNode extends BaseNode {
    protected String enumName;
    protected String[] values;

    public EnumNode(String name, CommandNode parent, Object defaultValue, String[] values) {
        super(name, parent, defaultValue);
        this.enumName = name + "Enums";
        this.values = values;
    }

    public EnumNode(String name, CommandNode parent, Object defaultValue, String enumName, String[] values) {
        super(name, parent, defaultValue);
        this.enumName = enumName;
        this.values = values;
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        boolean matched = false;
        for (String value : values) {
            if (customEquals(value, arg)) {
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

    protected boolean customEquals(String s1, String s2) {
        return s1.equals(s2);
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        var map = new LinkedHashMap<String, Set<CommandEnumConstraint>>();
        for (var value : values) {
            map.put(value, Collections.emptySet());
        }
        data.setEnumData(new CommandEnumData(enumName, map, false));
        return data;
    }
}
