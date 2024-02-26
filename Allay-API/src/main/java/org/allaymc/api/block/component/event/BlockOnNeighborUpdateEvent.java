package org.allaymc.api.block.component.event;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public class BlockOnNeighborUpdateEvent extends Event {
    private final Vector3ic updated;
    private final Vector3ic neighbor;
    private final BlockFace face;
    private final Dimension dimension;

    public BlockOnNeighborUpdateEvent(
            Vector3ic updated,
            Vector3ic neighbor,
            BlockFace face,
            Dimension dimension
    ) {
        this.updated = updated;
        this.neighbor = neighbor;
        this.face = face;
        this.dimension = dimension;
    }

    public Vector3ic updated() {
        return updated;
    }

    public Vector3ic neighbor() {
        return neighbor;
    }

    public BlockFace face() {
        return face;
    }

    public Dimension dimension() {
        return dimension;
    }
}
