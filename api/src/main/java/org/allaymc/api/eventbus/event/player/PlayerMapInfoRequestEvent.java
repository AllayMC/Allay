package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityItemFrame;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;

/**
 * {@code PlayerMapInfoRequestEvent} describes a request for the contents of a filled
 * map item. The map processor locates the item in inventory or an item frame before
 * dispatch; cancellation prevents that request from starting image rendering or
 * sending map data.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerMapInfoRequestEvent extends PlayerEvent implements CancellableEvent {
    /**
     * {@code mapItem} retains the filled map resolved by the request. The event
     * exposes the same mutable stack that the map processor will render after
     * approval.
     */
    protected ItemFilledMapStack mapItem;
    /**
     * {@code itemFrame} identifies the map's frame when found there. {@code null}
     * indicates that the request resolved an inventory map instead.
     */
    protected BlockEntityItemFrame itemFrame;

    /**
     * Creates a {@code PlayerMapInfoRequestEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerMapInfoRequestEvent(EntityPlayer player, ItemFilledMapStack mapItem, BlockEntityItemFrame itemFrame) {
        super(player);
        this.mapItem = mapItem;
        this.itemFrame = itemFrame;
    }
}
