package org.allaymc.server.eventbus.event.network;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * @author daoge_cmd
 */
public class PacketSendEvent extends PacketEvent {
    public PacketSendEvent(EntityPlayer player, BedrockPacket packet) {
        super(player, packet);
    }
}
