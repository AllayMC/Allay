package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;
import org.joml.Vector3fc;

/**
 * @author daoge_cmd
 */
@Getter
public class PlayerInteractEntityEvent extends PlayerEvent implements CancellableEvent {

    protected Entity entity;
    protected ItemStack itemStack;
    protected Vector3fc clickPos;

    public PlayerInteractEntityEvent(EntityPlayer player, Entity entity, ItemStack itemStack, Vector3fc clickPos) {
        super(player);
        this.entity = entity;
        this.itemStack = itemStack;
        this.clickPos = clickPos;
    }
}
