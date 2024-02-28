package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * Allay Project 2024/2/28
 *
 * @author daoge_cmd
 */
public class WildcardTargetNode extends StringNode {
    public WildcardTargetNode(String name, CommandNode parent, String defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.WILDCARD_TARGET);
        return data;
    }
}
