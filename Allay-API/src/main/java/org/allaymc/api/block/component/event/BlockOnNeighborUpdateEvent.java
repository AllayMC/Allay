package org.allaymc.api.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class BlockOnNeighborUpdateEvent extends Event {
    private final Vector3ic updated;
    private final Vector3ic neighbor;
    private final BlockFace face;
    private final Dimension dimension;
}
