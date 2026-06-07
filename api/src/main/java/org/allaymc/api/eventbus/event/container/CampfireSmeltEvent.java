package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityCampfire;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a campfire finishes cooking an item.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class CampfireSmeltEvent extends ContainerEvent implements CancellableEvent {
    /**
     * The campfire performing the smelt.
     */
    protected BlockEntityCampfire campfire;
    /**
     * The ingredient item stack being smelted.
     */
    protected ItemStack ingredient;
    /**
     * The output item stack.
     */
    @Setter
    protected ItemStack output;
}
