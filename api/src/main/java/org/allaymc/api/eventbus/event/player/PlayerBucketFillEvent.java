package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.interfaces.ItemBucketStack;

/**
 * {@code PlayerBucketFillEvent} represents a player's attempt to collect liquid or
 * powder snow with a bucket. The filling implementation dispatches this event before
 * replacing the world block and changing inventory contents; cancellation prevents
 * that implementation from completing the fill operation.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.WORLD)
public class PlayerBucketFillEvent extends PlayerBucketEvent {
    /**
     * Creates a {@code PlayerBucketFillEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerBucketFillEvent(EntityPlayer player, ItemBucketStack bucketStack, PlayerInteractInfo interactInfo) {
        super(player, bucketStack, interactInfo);
    }
}
