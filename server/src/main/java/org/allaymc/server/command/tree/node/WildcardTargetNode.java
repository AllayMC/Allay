package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

/**
 * {@code WildcardTargetNode} stores a single target expression as text while
 * advertising wildcard-target syntax to clients. It inherits ordinary string
 * consumption rather than resolving selectors or player names, so an executor
 * decides how to interpret literals such as {@code "*"} and selector tokens.
 *
 * @author daoge_cmd
 */
public class WildcardTargetNode extends StringNode {
    /**
     * Creates an unattached text target node with the supplied omission
     * default. The default remains inactive until optionality is enabled, and
     * matching retains tokens without validating target syntax.
     */
    public WildcardTargetNode(String name, CommandNode parent, String defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Creates client metadata advertising {@code CommandParam.WILDCARD_TARGET}
     * for this text parameter.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.WILDCARD_TARGET);
        return data;
    }
}
