package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.message.MayContainTrKey;

/**
 * @author daoge_cmd
 */
@CallerThread(ThreadType.NETWORK)
public class PlayerLoginEvent extends PlayerEvent implements CancellableEvent {

    /**
     * The reason that will be shown to the player if the event is cancelled.
     * If the event is not cancelled, this field will be ignored.
     */
    @MayContainTrKey
    @Setter
    @Getter
    protected String disconnectReason;

    /**
     * The message that will be broadcast if the event is not cancelled.
     * If the event is cancelled, this field will be ignored.
     */
    @MayContainTrKey
    @Setter
    @Getter
    protected String joinMessage;

    public PlayerLoginEvent(EntityPlayer player, String disconnectReason, String joinMessage) {
        super(player);
        this.disconnectReason = disconnectReason;
        this.joinMessage = joinMessage;
    }
}
