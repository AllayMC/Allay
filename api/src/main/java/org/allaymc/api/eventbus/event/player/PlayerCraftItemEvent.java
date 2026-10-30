package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.Recipe;

/**
 * {@code PlayerCraftItemEvent} describes recipe outputs proposed by a player's
 * crafting request. The normal and automatic crafting processors dispatch this event
 * before installing output stacks; cancellation rejects the request, and the event
 * retains the recipe and output array supplied by the processor.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class PlayerCraftItemEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Recipe selected for this request.
     */
    protected Recipe recipe;
    /**
     * {@code craftedItems} retains the processor's output array, which can be
     * {@code null} for recipes without an explicit output list.
     */
    protected ItemStack[] craftedItems;

    /**
     * Creates a {@code PlayerCraftItemEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerCraftItemEvent(EntityPlayer player, Recipe recipe, ItemStack[] craftedItems) {
        super(player);
        this.recipe = recipe;
        this.craftedItems = craftedItems;
    }
}
