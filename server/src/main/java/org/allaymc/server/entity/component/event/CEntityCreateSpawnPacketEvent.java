package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CEntityCreateSpawnPacketEvent extends Event {
    protected final BedrockPacket packet;
}
