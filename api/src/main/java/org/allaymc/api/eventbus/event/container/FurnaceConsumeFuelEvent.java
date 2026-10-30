package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code FurnaceConsumeFuelEvent} represents a furnace fuel refill before an item is
 * consumed. Cancellation prevents that refill; successful dispatch lets the furnace
 * consume or replace the stored fuel and initialize the burn duration from its item
 * metadata.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class FurnaceConsumeFuelEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Furnace requesting additional burn time.
     */
    protected BlockEntityFurnace furnace;
    /**
     * {@code fuel} references the stored stack selected for consumption. The furnace
     * continues reading that shared stack after successful dispatch to consume fuel and
     * select burn duration.
     */
    protected ItemStack fuel;
}
