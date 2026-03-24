package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a furnace finishes smelting an item.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class FurnaceSmeltEvent extends ContainerEvent implements CancellableEvent {
    /**
     * The furnace performing the smelt.
     */
    protected BlockEntityFurnace furnace;
    /**
     * The ingredient item stack being smelted.
     */
    protected ItemStack ingredient;
    /**
     * The output item stack.
     */
    protected ItemStack output;
}
