package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Event triggered when a player interacts with a block.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerInteractBlockEvent extends PlayerEvent implements CancellableEvent {

    /**
     * The information about the interaction, including the block being interacted with.
     */
    protected final PlayerInteractInfo interactInfo;
    /**
     * The action performed by the player.
     */
    protected final Action action;

    public PlayerInteractBlockEvent(EntityPlayer player, PlayerInteractInfo interactInfo, Action action) {
        super(player);
        this.interactInfo = interactInfo;
        this.action = action;
    }

    public enum Action {
        LEFT_CLICK,
        RIGHT_CLICK
    }
}
