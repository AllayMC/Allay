package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.Map;
import java.util.Set;

/**
 * {@code BooleanNode} parses the exact command tokens {@code "true"} and
 * {@code "false"} into a boolean result. Other spellings leave the input
 * untouched for sibling selection. Shared enumeration metadata advertises those
 * two tokens to clients, while the constructor stores a separate default for
 * optional omission.
 *
 * @author daoge_cmd
 */
public class BooleanNode extends BaseNode {
    /**
     * {@code BOOLEAN_ENUM_DATA_VALUE} contains the two advertised boolean
     * spellings and their empty constraint sets. Replacing this reference alone
     * does not rebuild the existing {@code BOOLEAN_ENUM_DATA}.
     */
    public static Map<String, Set<CommandEnumConstraint>> BOOLEAN_ENUM_DATA_VALUE = Map.of(
            "true", Set.of(),
            "false", Set.of()
    );

    /**
     * {@code BOOLEAN_ENUM_DATA} is the shared boolean enumeration attached to
     * generated parameter metadata. Replacing it affects subsequent
     * {@code toNetworkData} calls, which retain that object directly.
     */
    public static CommandEnumData BOOLEAN_ENUM_DATA = new CommandEnumData("Boolean", BOOLEAN_ENUM_DATA_VALUE, false);

    /**
     * Creates an unattached boolean node retaining its name, parent, and
     * omission default. The default applies only after the caller attaches the
     * node and separately marks it optional.
     */
    public BooleanNode(String name, CommandNode parent, boolean defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Accepts only the exact lowercase tokens {@code "true"} and
     * {@code "false"}, then appends a boolean and consumes one argument. Other
     * tokens return {@code false} without changing either parsing cursor.
     */
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

    /**
     * Creates parameter metadata referencing the shared two-value boolean
     * enumeration definition.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setEnumData(BOOLEAN_ENUM_DATA);
        return data;
    }
}
