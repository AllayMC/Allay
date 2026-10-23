package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.List;

/**
 * {@code PlayerTargetNode} restricts ordinary target resolution to player
 * entities while retaining selector and player-name syntax. Its filtering hook
 * removes non-player candidates in place before the list becomes a parsed
 * result. Empty selections remain valid, leaving target-count requirements to
 * the executor.
 *
 * @author daoge_cmd
 */
public class PlayerTargetNode extends TargetNode {
    /**
     * Creates an unattached player-target node retaining its parent and
     * omission default list. Normal matching filters resolved entities to
     * players; optional omission inserts the supplied list directly without
     * running that filter.
     */
    public PlayerTargetNode(String name, CommandNode parent, List<EntityPlayer> defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Removes every non-player entity from the supplied resolved list in place.
     * The remaining list and entity objects are retained by the parsed result;
     * an empty filtered selection does not cause matching to fail.
     */
    @Override
    protected void filterResult(List<Entity> entities) {
        entities.removeIf(entity -> !(entity instanceof EntityPlayer));
    }
}
