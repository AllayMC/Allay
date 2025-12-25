package org.allaymc.api.eventbus.event.container;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.Container;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.type.ItemType;

/**
 * Called when an item entity is picked up into a container, such as by a hopper.
 *
 * @author ClexaGod
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ContainerItemPickupEvent extends ContainerEvent implements CancellableEvent {
    protected EntityItem itemEntity;
    protected Container destination;
    protected int destinationSlot;
    protected ItemType<?> itemType;
    protected int count;

    public ContainerItemPickupEvent(EntityItem itemEntity, Container destination, int destinationSlot,
                                    ItemType<?> itemType, int count) {
        this.itemEntity = itemEntity;
        this.destination = destination;
        this.destinationSlot = destinationSlot;
        this.itemType = itemType;
        this.count = count;
    }
}
