package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

import java.util.List;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CEntityCreateMovePacketEvent extends Event {
    protected final List<BedrockPacket> packets;
}
