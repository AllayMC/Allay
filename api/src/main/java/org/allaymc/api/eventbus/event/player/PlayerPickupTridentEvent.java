package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityThrownTrident;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player is about to pick up a trident entity from the ground.
 * <p>
 * This event is called before the trident is added to the player's inventory.
 * Cancelling this event will prevent the trident from being picked up.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerPickupTridentEvent extends PlayerEvent implements CancellableEvent {
    /**
     * The trident entity being picked up.
     */
    protected EntityThrownTrident trident;
    /**
     * The trident item that will be added to the player's inventory.
     */
    protected ItemStack tridentItem;

    public PlayerPickupTridentEvent(EntityPlayer player, EntityThrownTrident trident, ItemStack tridentItem) {
        super(player);
        this.trident = trident;
        this.tridentItem = tridentItem;
    }
}
