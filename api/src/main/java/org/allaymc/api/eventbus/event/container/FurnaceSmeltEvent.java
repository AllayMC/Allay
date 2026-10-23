package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code FurnaceSmeltEvent} represents furnace output after the ingredient has already
 * been consumed. Cancellation prevents result insertion and experience accumulation, but
 * the furnace does not restore the consumed ingredient or its reset cooking progress.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class FurnaceSmeltEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Furnace completing the smelting operation.
     */
    protected BlockEntityFurnace furnace;
    /**
     * {@code ingredient} retains the input stack reference used before consumption. Its
     * count may already be reduced, or its former container slot may already hold air.
     */
    protected ItemStack ingredient;
    /**
     * {@code output} references the recipe output read by the furnace after dispatch. It
     * has no replacement setter, and listeners receive the same mutable stack reference.
     */
    protected ItemStack output;
}
