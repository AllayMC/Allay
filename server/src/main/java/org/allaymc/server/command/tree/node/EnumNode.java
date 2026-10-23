package org.allaymc.server.command.tree.node;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.*;

/**
 * {@code EnumNode} accepts a token from a configured enumeration and converts
 * it through an overridable result hook. Construction normalizes the supplied
 * values array in place to lowercase and retains it. Matching is case-sensitive
 * by default, while network metadata is rebuilt from the array's current
 * entries.
 *
 * @author daoge_cmd
 */
@Slf4j
public class EnumNode extends BaseNode {

    /**
     * Enumeration name included in generated client parameter metadata.
     */
    protected String enumName;
    /**
     * {@code values} retains the caller's array after in-place lowercase
     * normalization. Later array edits affect matching and metadata, so callers
     * must coordinate any changes with uses of this node.
     */
    protected String[] values;

    /**
     * Creates an unattached enumeration node using {@code name + "Enums"} as
     * its network enumeration name. The supplied values array is normalized in
     * place and retained; the default is stored without enabling optionality.
     */
    public EnumNode(String name, CommandNode parent, Object defaultValue, String[] values) {
        super(name, parent, defaultValue);
        this.enumName = name + "Enums";
        this.values = checkValues(values);
    }

    /**
     * Creates an unattached enumeration node with an explicit network
     * enumeration name. The constructor normalizes and retains the caller's
     * values array, while the supplied default remains inactive until
     * optionality is configured.
     */
    public EnumNode(String name, CommandNode parent, Object defaultValue, String enumName, String[] values) {
        super(name, parent, defaultValue);
        this.enumName = enumName;
        this.values = checkValues(values);
    }

    /**
     * Normalizes each supplied value to lowercase in the same array and returns
     * that array. Changed entries produce warnings; consumers retaining the
     * original array observe these constructor-time replacements directly.
     */
    protected String[] checkValues(String[] values) {
        for (int index = 0; index < values.length; index++) {
            var lowerCase = values[index].toLowerCase(Locale.ROOT);
            if (!values[index].equals(lowerCase)) {
                // Normalize the retained array itself so matching and later network
                // metadata use identical spellings; callers sharing this array observe
                // the same replacements rather than an independent normalized copy.
                log.warn("Upper case character is not allowed in command enum value! Value: {}", values[index]);
                values[index] = lowerCase;
            }
        }
        return values;
    }

    /**
     * Matches the current token against configured values through
     * {@code customEquals}. A match appends {@code argToResult(arg)} and
     * consumes one token; a mismatch leaves both cursors unchanged, and the
     * conversion result may be {@code null}.
     */
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

    /**
     * Returns the original matched argument as this enumeration node's default
     * result.
     */
    protected Object argToResult(String arg) {
        return arg;
    }

    /**
     * Creates enumeration metadata from the current values array in its
     * iteration order. Repeated spellings collapse into one map entry; the
     * returned metadata uses the configured enumeration name and retains this
     * node's optional flag.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        Map<String, Set<CommandEnumConstraint>> map = new LinkedHashMap<>();
        for (var value : values) map.put(value, Collections.emptySet());
        data.setEnumData(new CommandEnumData(enumName, map, false));
        return data;
    }

    /**
     * Returns whether the two enumeration spellings are exactly and
     * case-sensitively equal.
     */
    protected boolean customEquals(String s1, String s2) {
        return s1.equals(s2);
    }
}
