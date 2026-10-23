package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code BrewingStandConsumeFuelEvent} represents an attempt to consume blaze powder
 * when brewing fuel is empty. Cancellation prevents the fuel decrement and
 * replenishment; successful dispatch lets the brewing component consume one item and
 * initialize its fuel counters.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class BrewingStandConsumeFuelEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Brewing stand requesting a fuel refill.
     */
    protected BlockEntityBrewingStand brewingStand;
    /**
     * {@code fuel} references the stored fuel stack before consumption. The brewing
     * component reads and changes this same stack after successful event dispatch.
     */
    protected ItemStack fuel;
}
