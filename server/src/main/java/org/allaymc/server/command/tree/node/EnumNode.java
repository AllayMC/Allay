package org.allaymc.server.command.tree.node;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.*;

/**
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
            var lowerCase = values[index].toLowerCase(Locale.ROOT);
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
        var matched = Arrays.stream(values).anyMatch(value -> customEquals(value, arg));
        if (matched) {
            context.putResult(argToResult(arg));
            context.popArg();
            return true;
        }
        return false;
    }

    protected Object argToResult(String arg) {
        // In default implementation, the result is the same as the arg
        return arg;
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
