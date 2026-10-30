package org.allaymc.api.eventbus.event.container;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.Container;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.type.ItemType;

/**
 * {@code ContainerItemMoveEvent} describes a proposed transfer between two container
 * slots. Hoppers and player stack-request processors dispatch it before applying their
 * transfer; cancellation prevents that transfer, while the supplied container and slot
 * references identify the live source and destination.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class ContainerItemMoveEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Container supplying the proposed item transfer.
     */
    protected Container source;
    /**
     * Zero-based source container slot index.
     */
    protected int sourceSlot;
    /**
     * Container receiving the proposed item transfer.
     */
    protected Container destination;
    /**
     * Zero-based destination container slot index.
     */
    protected int destinationSlot;
    /**
     * Item type selected for the transfer.
     */
    protected ItemType<?> itemType;
    /**
     * Proposed number of items to move.
     */
    protected int count;

    /**
     * Creates a transfer event with the supplied live containers and slot indices.
     * Construction records the proposal without validating capacity or moving items; the
     * dispatching processor owns those checks and subsequent mutations.
     */
    public ContainerItemMoveEvent(Container source, int sourceSlot, Container destination, int destinationSlot, ItemType<?> itemType, int count) {
        this.source = source;
        this.sourceSlot = sourceSlot;
        this.destination = destination;
        this.destinationSlot = destinationSlot;
        this.itemType = itemType;
        this.count = count;
    }
}
