package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.interfaces.ItemBucketStack;

/**
 * {@code PlayerBucketEmptyEvent} represents a player's attempt to empty a filled
 * bucket into the world. The bucket implementation dispatches this event before
 * changing blocks and consuming the held bucket, so cancellation prevents that
 * implementation from applying the bucket operation.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class PlayerBucketEmptyEvent extends PlayerBucketEvent {
    /**
     * Creates a {@code PlayerBucketEmptyEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerBucketEmptyEvent(EntityPlayer player, ItemBucketStack bucketStack, PlayerInteractInfo interactInfo) {
        super(player, bucketStack, interactInfo);
    }
}
