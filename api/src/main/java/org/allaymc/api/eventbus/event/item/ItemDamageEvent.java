package org.allaymc.api.eventbus.event.item;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;

/**
 * Called when an item is about to be damaged.
 *
 * @author zernix2077
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ItemDamageEvent extends Event implements CancellableEvent {

    /**
     * The item stack that is about to be damaged.
     */
    protected final ItemStack itemStack;
    /**
     * The amount of damage to apply to the item stack.
     * This value can be modified to change how much damage the item will receive.
     * Setting this to zero will effectively prevent any damage from being applied.
     */
    @Setter
    protected int damage;

    public ItemDamageEvent(ItemStack itemStack, int damage) {
        this.itemStack = itemStack;
        this.damage = damage;
    }
}
