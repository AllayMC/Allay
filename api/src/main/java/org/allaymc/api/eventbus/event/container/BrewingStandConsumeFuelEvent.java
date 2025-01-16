package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public class BrewingStandConsumeFuelEvent extends ContainerEvent implements CancellableEvent {
    protected BlockEntityBrewingStand blockEntityBrewingStand;
    protected ItemStack fuel;
}
