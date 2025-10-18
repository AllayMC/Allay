package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Event triggered when a player interacts with air (right-click in air).
 * 
 * This is a specialized event for air interactions, separate from the general
 * PlayerInteractEvent which handles all types of interactions.
 *
 * @author galyckyj
 */
@Getter
public class PlayerInteractAirEvent extends PlayerEvent implements CancellableEvent {

    /**
     * The item held by the player during interaction.
     */
    protected final ItemStack itemStack;

    public PlayerInteractAirEvent(EntityPlayer player, ItemStack itemStack) {
        super(player);
        this.itemStack = itemStack;
    }
}
