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
@CallerThread(ThreadType.DIMENSION)
public class ContainerItemMoveEvent extends ContainerEvent implements CancellableEvent {
    /**
     * The source container.
     */
    protected Container source;
    /**
     * The source slot index.
     */
    protected int sourceSlot;
    /**
     * The destination container.
     */
    protected Container destination;
    /**
     * The destination slot index.
     */
    protected int destinationSlot;
    /**
     * The type of item being moved.
     */
    protected ItemType<?> itemType;
    /**
     * The number of items being moved.
     */
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
