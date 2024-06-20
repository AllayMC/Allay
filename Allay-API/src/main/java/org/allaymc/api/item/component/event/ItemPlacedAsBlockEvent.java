package org.allaymc.api.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/6/21
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class ItemPlacedAsBlockEvent extends Event {
    protected Dimension dimension;
    protected Vector3ic placeBlockPos;
    protected ItemStack itemStack;
}
