package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd | Dhaiven
 */
@Getter
@Setter
public class PlayerChatEvent extends PlayerEvent implements CancellableEvent {
    protected String header;
    protected String message;

    public PlayerChatEvent(EntityPlayer player, String header, String message) {
        super(player);
        this.header = header;
        this.message = message;
    }

    public String buildChat() {
        return header + message;
    }
}
