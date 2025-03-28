package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public class CBlockBeforePlacedEvent extends Event implements CancellableEvent {
    protected Dimension dimension;
    protected BlockState blockState;
    protected Vector3ic placeBlockPos;
    protected PlayerInteractInfo placementInfo;
}
