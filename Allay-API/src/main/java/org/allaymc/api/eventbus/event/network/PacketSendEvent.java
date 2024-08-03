package org.allaymc.api.eventbus.event.network;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class PacketSendEvent extends PacketEvent {
    public PacketSendEvent(EntityPlayer player, BedrockPacket packet) {
        super(player, packet);
    }
}
