package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/8/18
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CItemRightClickOnBlockEvent extends Event {
    protected Dimension dimension;
    protected Vector3ic placeBlockPos;
    protected PlayerInteractInfo interactInfo;
}
