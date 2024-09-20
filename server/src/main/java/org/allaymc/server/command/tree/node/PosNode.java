package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.joml.Vector3f;

/**
 * @author daoge_cmd
 */
public class PosNode extends BaseNode {
    public PosNode(String name, CommandNode parent, Object defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    public boolean match(CommandContext context) {
        var basePos = context.getSender().getCmdExecuteLocation();
        var baseX = basePos.x();
        var baseY = basePos.y();
        var baseZ = basePos.z();

        float x, y, z;
        try {
            var index = context.getCurrentArgIndex();
            x = getRelative(context.queryArg(index), baseX);
            y = getRelative(context.queryArg(index + 1), baseY);
            z = getRelative(context.queryArg(index + 2), baseZ);
        } catch (Exception e) {
            return false;
        }
        context.putResult(new Vector3f(x, y, z));
        context.popArg();
        context.popArg();
        context.popArg();
        return true;
    }

    protected float getRelative(String token, float base) {
        if (!token.contains("~")) return Float.parseFloat(token);

        if (token.length() > 1) return base + Float.parseFloat(token.substring(1));
        else return base;
    }

    @Override
    public int getMaxArgCost() {
        return 3;
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.POSITION);
        return data;
    }
}
