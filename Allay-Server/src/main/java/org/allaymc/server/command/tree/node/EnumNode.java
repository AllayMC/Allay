package org.allaymc.server.command.tree.node;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
@Slf4j
public class EnumNode extends BaseNode {

    protected String enumName;
    protected String[] values;

    public EnumNode(String name, CommandNode parent, Object defaultValue, String[] values) {
        super(name, parent, defaultValue);
        this.enumName = name + "Enums";
        this.values = checkValues(values);
    }

    public EnumNode(String name, CommandNode parent, Object defaultValue, String enumName, String[] values) {
        super(name, parent, defaultValue);
        this.enumName = enumName;
        this.values = checkValues(values);
    }

    protected String[] checkValues(String[] values) {
        for (int index = 0; index < values.length; index++) {
            var lowerCase = values[index].toLowerCase();
            if (!values[index].equals(lowerCase)) {
                log.warn("Upper case character is not allowed in command enum value! Value: {}", values[index]);
                values[index] = lowerCase;
            }
        }
        return values;
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

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        Map<String, Set<CommandEnumConstraint>> map = new LinkedHashMap<>();
        for (var value : values) map.put(value, Collections.emptySet());
        data.setEnumData(new CommandEnumData(enumName, map, false));
        return data;
    }

    protected boolean customEquals(String s1, String s2) {
        return s1.equals(s2);
    }
}
