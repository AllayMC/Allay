package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player eat an edible item, such as food, potion, etc...
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerEatEvent extends PlayerEvent implements CancellableEvent {
    protected ItemStack edible;

    public PlayerEatEvent(EntityPlayer player, ItemStack edible) {
        super(player);
        this.edible = edible;
    }
}
