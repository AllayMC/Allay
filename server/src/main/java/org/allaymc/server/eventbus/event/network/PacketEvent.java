package org.allaymc.server.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.network.NetworkEvent;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class PacketEvent extends NetworkEvent implements CancellableEvent {
    protected EntityPlayer player;
    @Setter
    protected BedrockPacket packet;
}
