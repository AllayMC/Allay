package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.interfaces.ItemBucketStack;

/**
 * Called when a player fills a bucket with liquid.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.DIMENSION)
public class PlayerBucketFillEvent extends PlayerBucketEvent {
    public PlayerBucketFillEvent(EntityPlayer player, ItemBucketStack bucketStack, PlayerInteractInfo interactInfo) {
        super(player, bucketStack, interactInfo);
    }
}
