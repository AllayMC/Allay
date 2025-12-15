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
@CallerThread(ThreadType.WORLD)
public class FurnaceSmeltEvent extends ContainerEvent implements CancellableEvent {
    protected BlockEntityFurnace furnace;
    protected ItemStack ingredient;
    protected ItemStack output;
}
