package org.allaymc.api.eventbus.event.container;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.Container;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code ContainerItemPickupEvent} describes a proposed pickup from a dropped item
 * entity into one container slot. Hopper transfer dispatches it before changing either
 * source or destination, and cancellation prevents that proposed pickup while leaving
 * its live entity and container references available to listeners.
 *
 * @author ClexaGod
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class ContainerItemPickupEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Dropped entity supplying the proposed items.
     */
    protected EntityItem itemEntity;
    /**
     * Container receiving the proposed item pickup.
     */
    protected Container destination;
    /**
     * Zero-based destination container slot index.
     */
    protected int destinationSlot;
    /**
     * Proposed number of items to pick up.
     */
    protected int count;

    /**
     * Creates a pickup event retaining the supplied item entity and destination
     * container. Construction records the proposed slot and count without validating
     * space, changing stacks, or removing the item entity.
     */
    public ContainerItemPickupEvent(EntityItem itemEntity, Container destination, int destinationSlot, int count) {
        this.itemEntity = itemEntity;
        this.destination = destination;
        this.destinationSlot = destinationSlot;
        this.count = count;
    }
}
