package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class TextPacketProcessor extends PacketProcessor<TextPacket> {
    @Override
    public void handleSync(EntityPlayer player, TextPacket packet) {
        if (packet.getType() == TextPacket.Type.CHAT) {
            var message = "<" + player.getDisplayName() + "> " + packet.getMessage();
            Server.getInstance().broadcastMessage(message);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TEXT;
    }
}
