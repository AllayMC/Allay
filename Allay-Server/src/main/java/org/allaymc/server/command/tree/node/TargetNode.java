package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.entity.Entity;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.Collections;
import java.util.List;

/**
 * Allay Project 2024/1/6
 *
 * @author daoge_cmd
 */
public class TargetNode extends BaseNode {

    public TargetNode(String name, CommandNode parent, List<Entity> defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    public boolean match(CommandContext context) {
        //TODO: support entity selector
        var dimension = context.getSender().getCmdExecuteLocation().dimension();
        var playerName = context.queryArg();
        for (var player : dimension.getPlayers()) {
            if (player.getOriginName().equals(playerName)) {
                context.putResult(List.of(player));
                context.popArg();
                return true;
            }
        }
        context.putResult(Collections.emptyList());
        context.popArg();
        // 未找到目标不认为是非法的，这在一些命令里面有特殊用处 (eg: /testfor)
        return true;
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.TARGET);
        return data;
    }
}
