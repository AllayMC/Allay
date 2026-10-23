package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * {@code StringNode} accepts one command token and stores its original string
 * as a parsed result. It performs no additional quoting, normalization, or
 * entity resolution; any earlier command-line tokenization has already decided
 * the token boundary. Client metadata identifies the parameter as a string.
 *
 * @author daoge_cmd
 */
public class StringNode extends BaseNode {
    /**
     * Creates an unattached string node retaining the supplied parent and
     * omission default. The caller attaches it separately, and the stored
     * default is used only after optionality has been enabled.
     */
    public StringNode(String name, CommandNode parent, String defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Consumes one remaining token and stores that same string as the result.
     * The caller must ensure input remains because {@code popArg} raises a
     * parse exception instead of returning a missing-token sentinel.
     */
    @Override
    public boolean match(CommandContext context) {
        context.putResult(context.popArg());
        return true;
    }

    /**
     * Creates client metadata identifying this parameter as
     * {@code CommandParam.STRING}.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.STRING);
        return data;
    }
}
