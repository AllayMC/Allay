package org.allaymc.server.command.tree.node;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author daoge_cmd
 */
@Slf4j
public class KeyNode extends BaseNode {

    protected String key;

    public KeyNode(String key, CommandNode parent, String defaultValue) {
        super(key + "Key", parent, defaultValue);
        this.key = checkKey(key);
    }

    protected String checkKey(String key) {
        var lowerCase = key.toLowerCase(Locale.ROOT);
        if (lowerCase.equals(key)) return key;

        log.warn("Upper case character is not allowed in command key! key: {}", key);
        return lowerCase;
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        if (key.equals(arg)) {
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
        data.setEnumData(new CommandEnumData(name, Map.of(key, Set.of()), false));
        return data;
    }
}
