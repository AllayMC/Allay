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
 * {@code KeyNode} matches one literal subcommand key and contributes the
 * matched token to parsed results. Construction lowercases the accepted key
 * with {@code Locale.ROOT}, logging when normalization changes it. Matching
 * remains case-sensitive, and network metadata advertises the normalized
 * literal as an enumeration value.
 *
 * @author daoge_cmd
 */
@Slf4j
public class KeyNode extends BaseNode {

    /**
     * {@code key} stores the normalized accepted literal. {@code match}
     * compares it exactly, while the node name retains the original constructor
     * input plus its suffix.
     */
    protected String key;

    /**
     * Creates an unattached literal node and normalizes its accepted key to
     * lowercase. The parent and omission default are retained, while the
     * parameter name uses the original key followed by {@code "Key"}.
     */
    public KeyNode(String key, CommandNode parent, String defaultValue) {
        super(key + "Key", parent, defaultValue);
        this.key = checkKey(key);
    }

    /**
     * Returns a lowercase key using {@code Locale.ROOT}, logging a warning when
     * input changes. The returned string controls matching and advertisement;
     * this normalization does not change the stored parameter name.
     */
    protected String checkKey(String key) {
        var lowerCase = key.toLowerCase(Locale.ROOT);
        if (lowerCase.equals(key)) return key;

        // The accepted literal and advertised enum value must use the same lowercase
        // spelling; retain the warning so command authors can correct mixed-case keys.
        log.warn("Upper case character is not allowed in command key! key: {}", key);
        return lowerCase;
    }

    /**
     * Appends and consumes the current token only when it exactly equals the
     * normalized key. A mismatch leaves arguments and results unchanged,
     * allowing another sibling to inspect the same input.
     */
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

    /**
     * Creates enumeration metadata advertising the single normalized literal
     * accepted by this node.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setEnumData(new CommandEnumData(name, Map.of(key, Set.of()), false));
        return data;
    }
}
