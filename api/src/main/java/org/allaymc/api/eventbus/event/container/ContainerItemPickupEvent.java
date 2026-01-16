package org.allaymc.api.eventbus.event.container;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.Container;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when an item entity is picked up into a container, such as by a hopper.
 *
 * @author ClexaGod
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class ContainerItemPickupEvent extends ContainerEvent implements CancellableEvent {
    /**
     * The item entity being picked up.
     */
    protected EntityItem itemEntity;
    /**
     * The destination container.
     */
    protected Container destination;
    /**
     * The slot in the destination container.
     */
    protected int destinationSlot;
    /**
     * The count of items picked up.
     */
    protected int count;

    public ContainerItemPickupEvent(EntityItem itemEntity, Container destination, int destinationSlot, int count) {
        this.itemEntity = itemEntity;
        this.destination = destination;
        this.destinationSlot = destinationSlot;
        this.count = count;
    }
}
