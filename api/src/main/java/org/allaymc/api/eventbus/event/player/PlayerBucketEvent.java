package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.interfaces.ItemBucketStack;

/**
 * Represents an event related to a player using a bucket. This is the base class for bucket events.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class PlayerBucketEvent extends PlayerEvent implements CancellableEvent {

    protected ItemBucketStack bucketStack;
    protected PlayerInteractInfo interactInfo;

    public PlayerBucketEvent(EntityPlayer player, ItemBucketStack bucketStack, PlayerInteractInfo interactInfo) {
        super(player);
        this.bucketStack = bucketStack;
        this.interactInfo = interactInfo;
    }

}
