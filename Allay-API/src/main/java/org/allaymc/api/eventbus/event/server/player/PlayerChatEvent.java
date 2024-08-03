package org.allaymc.api.eventbus.event.server.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.PlayerEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
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
