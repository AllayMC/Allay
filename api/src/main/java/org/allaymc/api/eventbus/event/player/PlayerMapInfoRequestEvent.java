package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityItemFrame;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;

/**
 * Called when a player requests map data for a filled map item.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerMapInfoRequestEvent extends PlayerEvent implements CancellableEvent {
    /**
     * The map item that the player requested data for.
     */
    protected ItemFilledMapStack mapItem;
    /**
     * The item frame that holds the map item, can be {@code null} if the map item is not in item frame.
     */
    protected BlockEntityItemFrame itemFrame;

    public PlayerMapInfoRequestEvent(EntityPlayer player, ItemFilledMapStack mapItem, BlockEntityItemFrame itemFrame) {
        super(player);
        this.mapItem = mapItem;
        this.itemFrame = itemFrame;
    }
}
