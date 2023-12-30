package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.DataPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class TextPacketProcessor extends DataPacketProcessor<TextPacket> {
    @Override
    public void handle(EntityPlayer player, TextPacket pk) {
        if (pk.getType() == TextPacket.Type.CHAT) {
            var message = pk.getMessage();
            Server.getInstance().broadcastChat(player, message);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TEXT;
    }
}
