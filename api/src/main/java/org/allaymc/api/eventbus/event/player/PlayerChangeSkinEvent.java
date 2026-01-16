package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.Skin;

/**
 * Called when a player changes their skin.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerChangeSkinEvent extends PlayerEvent implements CancellableEvent {
    protected Skin oldSkin;
    @Setter
    protected Skin newSkin;

    public PlayerChangeSkinEvent(EntityPlayer player, Skin oldSkin, Skin newSkin) {
        super(player);
        this.oldSkin = oldSkin;
        this.newSkin = newSkin;
    }
}
