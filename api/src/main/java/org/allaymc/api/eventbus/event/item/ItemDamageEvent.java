package org.allaymc.api.eventbus.event.item;

import com.google.common.base.Preconditions;
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
    protected final ItemStack itemStack;
    @Setter
    protected int increase;

    public ItemDamageEvent(ItemStack itemStack, int increase) {
        this.itemStack = itemStack;
        this.increase = increase;
    }
}
