package org.allaymc.api.eventbus.event.world.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.PlayerEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerDropItemEvent extends PlayerEvent implements CancellableEvent {
    protected ItemStack itemStack;

    public PlayerDropItemEvent(EntityPlayer player, ItemStack itemStack) {
        super(player);
        this.itemStack = itemStack;
    }
}
