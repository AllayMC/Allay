package org.allaymc.api.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class ClientConnectEvent extends NetworkEvent implements CancellableEvent {
    protected BedrockServerSession session;
}
