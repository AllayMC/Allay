package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.interfaces.ItemBucketStack;

/**
 * {@code PlayerBucketEvent} provides the player, bucket stack, and block interaction
 * shared by cancellable bucket operations. Subclasses distinguish filling from
 * emptying; the constructor retains the supplied objects so listeners inspect the
 * same bucket and interaction used by the caller.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class PlayerBucketEvent extends PlayerEvent implements CancellableEvent {

    /**
     * {@code bucketStack} retains the bucket involved in this operation. The event
     * does not copy the mutable item supplied by its producer.
     */
    protected ItemBucketStack bucketStack;
    /**
     * {@code interactInfo} retains the producer's block interaction context. The
     * caller uses that same context to select affected positions.
     */
    protected PlayerInteractInfo interactInfo;

    /**
     * Creates a {@code PlayerBucketEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerBucketEvent(EntityPlayer player, ItemBucketStack bucketStack, PlayerInteractInfo interactInfo) {
        super(player);
        this.bucketStack = bucketStack;
        this.interactInfo = interactInfo;
    }

}
