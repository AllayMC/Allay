package org.allaymc.server.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class PacketEvent extends NetworkEvent implements CancellableEvent {
    protected Player player;
    @Setter
    protected BedrockPacket packet;
}
