package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.Recipe;

/**
 * Called when a player crafts an item using 3x3 crafting table or 2x2 crafting grid in the inventory.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class PlayerCraftItemEvent extends PlayerEvent implements CancellableEvent {
    /**
     * The recipe that the player used to craft the item.
     */
    protected Recipe recipe;
    /**
     * The crafted items.
     */
    protected ItemStack[] craftedItems;

    public PlayerCraftItemEvent(EntityPlayer player, Recipe recipe, ItemStack[] craftedItems) {
        super(player);
        this.recipe = recipe;
        this.craftedItems = craftedItems;
    }
}
