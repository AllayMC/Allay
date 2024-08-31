package org.allaymc.api.eventbus.event.container;

import lombok.Getter;
import org.allaymc.api.container.Container;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.type.ItemType;

/**
 * Allay Project 2024/8/31
 *
 * @author daoge_cmd
 */
@Getter
public class ContainerItemMoveEvent extends ContainerEvent implements CancellableEvent {
    protected Container source;
    protected int sourceSlot;
    protected Container destination;
    protected int destinationSlot;
    protected ItemType<?> itemType;
    protected int count;

    public ContainerItemMoveEvent(Container source, int sourceSlot, Container destination, int destinationSlot, ItemType<?> itemType, int count) {
        this.source = source;
        this.sourceSlot = sourceSlot;
        this.destination = destination;
        this.destinationSlot = destinationSlot;
        this.itemType = itemType;
        this.count = count;
    }
}
