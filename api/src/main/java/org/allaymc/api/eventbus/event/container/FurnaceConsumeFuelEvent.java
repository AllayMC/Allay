package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a furnace consumes fuel to continue smelting.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.WORLD)
public class FurnaceConsumeFuelEvent extends ContainerEvent implements CancellableEvent {
    protected BlockEntityFurnace furnace;
    protected ItemStack fuel;
}
