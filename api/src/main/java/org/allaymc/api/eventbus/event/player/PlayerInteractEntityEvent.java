package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;
import org.joml.Vector3fc;

/**
 * {@code PlayerInteractEntityEvent} describes a player's interaction with a target
 * entity using the held stack. The inventory transaction processor dispatches it
 * before invoking item or target interaction behavior; cancellation suppresses those
 * calls, and the event retains the supplied target, stack, and click vector.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerInteractEntityEvent extends PlayerEvent implements CancellableEvent {

    /**
     * Entity targeted by the interaction.
     */
    protected Entity entity;
    /**
     * {@code itemStack} retains the player's mutable held stack. The transaction
     * processor invokes interaction behavior on that same item after approval.
     */
    protected ItemStack itemStack;
    /**
     * {@code clickPos} retains the packet-derived click vector. The constructor does
     * not copy or normalize the supplied coordinates.
     */
    protected Vector3fc clickPos;

    /**
     * Creates a {@code PlayerInteractEntityEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerInteractEntityEvent(EntityPlayer player, Entity entity, ItemStack itemStack, Vector3fc clickPos) {
        super(player);
        this.entity = entity;
        this.itemStack = itemStack;
        this.clickPos = clickPos;
    }
}
