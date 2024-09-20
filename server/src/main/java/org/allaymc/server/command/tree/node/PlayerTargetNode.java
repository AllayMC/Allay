package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class PlayerTargetNode extends TargetNode {
    public PlayerTargetNode(String name, CommandNode parent, List<EntityPlayer> defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    protected void filterResult(List<Entity> entities) {
        entities.removeIf(entity -> !(entity instanceof EntityPlayer));
    }
}
