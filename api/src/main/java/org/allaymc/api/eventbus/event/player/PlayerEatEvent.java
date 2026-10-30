package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code PlayerEatEvent} represents a player's attempt to finish consuming an edible
 * item. The edible component dispatches this event before applying its eating
 * behavior and consuming the hand item; cancellation prevents those effects through
 * that component's consumption path.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerEatEvent extends PlayerEvent implements CancellableEvent {
    /**
     * {@code edible} retains the mutable edible stack supplied by the consumption
     * component before its eating behavior and hand consumption occur.
     */
    protected ItemStack edible;

    /**
     * Creates a {@code PlayerEatEvent} for the supplied player and event data. The
     * constructor retains reference-valued arguments without copying them and does
     * not dispatch or apply the represented action.
     */
    public PlayerEatEvent(EntityPlayer player, ItemStack edible) {
        super(player);
        this.edible = edible;
    }
}
