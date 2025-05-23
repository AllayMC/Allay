package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.MayContainTrKey;

/**
 * PlayerQuitEvent will be called when a player quit the server.
 * <p>
 * Different from {@link org.allaymc.api.eventbus.event.network.ClientDisconnectEvent}, this event
 * will only be called when an already logged in player quit the server. If the client disconnect
 * without logging in, this event will not be called.
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerQuitEvent extends PlayerEvent {
    /**
     * The message that will be broadcast.
     */
    @MayContainTrKey
    @Setter
    @Getter
    protected String quitMessage;

    public PlayerQuitEvent(EntityPlayer player, String quitMessage) {
        super(player);
        this.quitMessage = quitMessage;
    }
}
