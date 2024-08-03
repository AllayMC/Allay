package org.allaymc.api.eventbus.event.server.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class ClientConnectEvent extends NetworkEvent implements CancellableEvent {
    protected BedrockServerSession session;
}
