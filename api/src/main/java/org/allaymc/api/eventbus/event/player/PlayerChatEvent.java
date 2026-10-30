package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerChatEvent} holds a player's chat prefix and message before server
 * broadcast. Listeners may change either string or cancel the event; the chat
 * processor calls {@code buildChat} after approval so the final concatenated text is
 * the message delivered to recipients.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@Setter
@CallerThread(ThreadType.WORLD)
public class PlayerChatEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Prefix concatenated before the message.
     */
    protected String header;
    /**
     * Unformatted message content after the prefix.
     */
    protected String message;

    /**
     * Creates a {@code PlayerChatEvent} for the supplied player and event data. The
     * constructor retains reference-valued arguments without copying them and does
     * not dispatch or apply the represented action.
     */
    public PlayerChatEvent(EntityPlayer player, String header, String message) {
        super(player);
        this.header = header;
        this.message = message;
    }

    /**
     * Returns the final chat text by concatenating {@code header} and
     * {@code message} without inserting additional spacing or formatting.
     */
    public String buildChat() {
        return header + message;
    }
}
