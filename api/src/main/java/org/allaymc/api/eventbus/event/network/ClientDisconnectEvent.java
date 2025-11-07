package org.allaymc.api.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;

import java.net.SocketAddress;

/**
 * ClientDisconnectEvent will be called when the client disconnects.
 * <p>
 * Different from {@link org.allaymc.api.eventbus.event.player.PlayerQuitEvent}, this event will always
 * be called when the client disconnects, even if the client is not logged in.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.ANY)
public class ClientDisconnectEvent extends NetworkEvent {
    protected SocketAddress socketAddress;
    /**
     * The reason why the client disconnected.
     */
    protected String disconnectReason;
}
