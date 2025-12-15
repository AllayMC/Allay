package org.allaymc.api.eventbus.event.container;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.Container;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.type.ItemType;

/**
 * Called when an item is moved between containers, such as by a hopper.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
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
