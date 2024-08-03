package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerItemHeldEvent extends PlayerEvent {
    protected ItemStack itemStack;
    protected int slot;

    public PlayerItemHeldEvent(EntityPlayer player, ItemStack itemStack, int slot) {
        super(player);
        this.itemStack = itemStack;
        this.slot = slot;
    }
}
