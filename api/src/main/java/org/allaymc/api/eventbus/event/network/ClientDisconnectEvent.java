package org.allaymc.api.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

/**
 * ClientDisconnectEvent will be called when client disconnect.
 * <p>
 * Different from {@link org.allaymc.api.eventbus.event.player.PlayerQuitEvent}, this event will always
 * be called when client disconnect, even if the client is not logged in.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class ClientDisconnectEvent extends NetworkEvent {
    protected BedrockServerSession session;
    /**
     * The reason why the client disconnected.
     */
    protected String disconnectReason;
}
