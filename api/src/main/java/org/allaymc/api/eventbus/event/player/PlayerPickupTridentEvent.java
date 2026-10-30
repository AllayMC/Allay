package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityThrownTrident;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code PlayerPickupTridentEvent} describes a thrown trident considered for a
 * player's inventory. The pickup component dispatches it before inserting the
 * retained trident stack; cancellation skips insertion and removal, while an
 * accepted successful insertion clears the projectile's item before removing it.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerPickupTridentEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Ground trident entity being considered.
     */
    protected EntityThrownTrident trident;
    /**
     * {@code tridentItem} retains the projectile's item stack. The pickup component
     * inserts this mutable reference before clearing it from the projectile.
     */
    protected ItemStack tridentItem;

    /**
     * Creates a {@code PlayerPickupTridentEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerPickupTridentEvent(EntityPlayer player, EntityThrownTrident trident, ItemStack tridentItem) {
        super(player);
        this.trident = trident;
        this.tridentItem = tridentItem;
    }
}
