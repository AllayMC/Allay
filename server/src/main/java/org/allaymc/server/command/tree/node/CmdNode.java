package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamOption;

/**
 * @author daoge_cmd
 */
public class CmdNode extends MessageNode {
    public CmdNode(String name, CommandNode parent, String defaultValue) {
        super(name, parent, defaultValue);
        paramOptions.add(CommandParamOption.HAS_SEMANTIC_CONSTRAINT);
    }

    @Override
    protected String processArg(String arg) {
        return arg.contains(" ") ? "\"" + arg + "\"" : arg;
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        // This is a mistake by protocol library
        // TODO: change back to COMMAND when protocol library fixes it
        data.setType(CommandParam.CODE_BUILDER_ARGS);
        return data;
    }

    @Override
    public CommandNode addLeaf(CommandNode leaf) {
        throw new UnsupportedOperationException("CmdNode can't add leaf");
    }
}
