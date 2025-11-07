package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.UNKNOWN)
public class PlayerKickEvent extends PlayerEvent implements CancellableEvent {

    protected String reason;

    public PlayerKickEvent(EntityPlayer player, String reason) {
        super(player);
        this.reason = reason;
    }
}
