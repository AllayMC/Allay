package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code BrewingStandBrewEvent} represents proposed bottle replacements when brewing
 * completes. Listeners may change the result array or cancel the completion before
 * bottle assignment, reagent consumption, and the decrement of stored brewing fuel.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class BrewingStandBrewEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Brewing stand completing the potion batch.
     */
    protected BlockEntityBrewingStand brewingStand;
    /**
     * {@code result} is the shared array read after dispatch in bottle-index order. A
     * {@code null} entry preserves that bottle; replacement arrays must fit the three
     * bottle slots.
     */
    @Setter
    protected ItemStack[] result;
}
