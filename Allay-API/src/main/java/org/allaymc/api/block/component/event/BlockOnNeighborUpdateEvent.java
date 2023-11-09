package org.allaymc.api.block.component.event;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.component.interfaces.ComponentEvent;
import org.allaymc.api.world.World;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public record BlockOnNeighborUpdateEvent(Vector3ic updated, Vector3ic neighbor, BlockFace face,
                                         World world) implements ComponentEvent {
}
