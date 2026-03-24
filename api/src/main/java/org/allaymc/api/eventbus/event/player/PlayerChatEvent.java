package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player sends a chat message.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@Setter
@CallerThread(ThreadType.WORLD)
public class PlayerChatEvent extends PlayerEvent implements CancellableEvent {
    /**
     * The chat header.
     */
    protected String header;
    /**
     * The chat message.
     */
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
