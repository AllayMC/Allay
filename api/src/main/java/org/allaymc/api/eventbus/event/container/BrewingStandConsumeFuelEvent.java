package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a brewing stand consumes fuel (blaze powder).
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class BrewingStandConsumeFuelEvent extends ContainerEvent implements CancellableEvent {
    /**
     * The brewing stand consuming the fuel.
     */
    protected BlockEntityBrewingStand brewingStand;
    /**
     * The fuel item stack being consumed.
     */
    protected ItemStack fuel;
}
