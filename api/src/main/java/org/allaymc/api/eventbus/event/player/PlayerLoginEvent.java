package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.i18n.MayContainTrKey;

/**
 * @author daoge_cmd
 */
public class PlayerLoginEvent extends PlayerEvent implements CancellableEvent {

    /**
     * The reason that will be shown to the player if the event is cancelled.
     * If the event is not cancelled, this field will be ignored.
     */
    @MayContainTrKey
    @Setter
    @Getter
    protected String disconnectReason;

    public PlayerLoginEvent(EntityPlayer player, String disconnectReason) {
        super(player);
        this.disconnectReason = disconnectReason;
    }
}
