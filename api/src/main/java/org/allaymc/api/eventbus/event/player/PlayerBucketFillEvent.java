package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.interfaces.ItemBucketStack;

/**
 * @author daoge_cmd
 */
public class PlayerBucketFillEvent extends PlayerBucketEvent {
    public PlayerBucketFillEvent(EntityPlayer player, ItemBucketStack bucketStack, PlayerInteractInfo interactInfo) {
        super(player, bucketStack, interactInfo);
    }
}
