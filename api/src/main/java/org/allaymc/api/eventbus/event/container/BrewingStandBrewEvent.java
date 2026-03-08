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
 * Called when a brewing stand finishes brewing potions.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class BrewingStandBrewEvent extends ContainerEvent implements CancellableEvent {
    protected BlockEntityBrewingStand brewingStand;
    @Setter
    protected ItemStack[] result;
}
