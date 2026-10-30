package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code PlayerRightClickItemInAirEvent} represents an immediate item right-click
 * after sustained use could not begin. The inventory transaction processor
 * dispatches it when {@code canUseItemInAir} returns {@code false}; cancellation
 * suppresses the item's {@code rightClickItemInAir} callback for this request.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class PlayerRightClickItemInAirEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Creates a {@code PlayerRightClickItemInAirEvent} for the supplied player and
     * event data. The constructor retains reference-valued arguments without copying
     * them and does not dispatch or apply the represented action.
     */
    public PlayerRightClickItemInAirEvent(EntityPlayer player) {
        super(player);
    }
}
