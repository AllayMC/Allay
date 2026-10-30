package org.allaymc.api.eventbus.event.item;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;

/**
 * {@code ItemDamageEvent} carries a proposed durability change for an item stack.
 * The damage attempt dispatches this event after its unbreaking check succeeds;
 * listeners can cancel the attempt or change the amount passed to the stack's
 * damage setter.
 *
 * @author zernix2077
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ItemDamageEvent extends Event implements CancellableEvent {

    /**
     * Stack receiving the durability change.
     */
    protected final ItemStack itemStack;
    /**
     * {@code damage} holds the proposed durability increase. The damage attempt adds
     * the listener-modified value to the stack's current damage after dispatch.
     */
    @Setter
    protected int damage;

    /**
     * Creates a durability-change event retaining the supplied stack and amount.
     * The constructor does not apply damage; the caller uses the possibly modified
     * amount only after an uncancelled dispatch.
     */
    public ItemDamageEvent(ItemStack itemStack, int damage) {
        this.itemStack = itemStack;
        this.damage = damage;
    }
}
