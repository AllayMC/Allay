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
 * {@code CampfireSmeltEvent} represents a cooked campfire item before it is dropped into
 * the world. Listeners may replace the output or cancel completion; cancellation leaves
 * the ingredient in its campfire slot for a later cooking tick.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class CampfireSmeltEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Campfire completing the cooking slot.
     */
    protected BlockEntityCampfire campfire;
    /**
     * {@code ingredient} references the campfire's stored input stack. The slot retains
     * this stack when listeners cancel the proposed cooked-item drop.
     */
    protected ItemStack ingredient;
    /**
     * {@code output} is the stack passed to cooked-item dropping after dispatch.
     * Listeners may replace this reference before the campfire clears its input slot.
     */
    @Setter
    protected ItemStack output;
}
