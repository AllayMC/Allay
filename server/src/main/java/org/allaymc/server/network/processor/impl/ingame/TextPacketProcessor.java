package org.allaymc.server.network.processor.impl.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerChatEvent;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

/**
 * @author Cool_Loong
 */
public class TextPacketProcessor extends PacketProcessor<TextPacket> {
    @Override
    public void handleSync(EntityPlayer player, TextPacket packet, long receiveTime) {
        if (packet.getType() != TextPacket.Type.CHAT) return;

        var event = new PlayerChatEvent(player, "<" + player.getDisplayName() + "> ", packet.getMessage());
        event.call();
        if (event.isCancelled()) return;

        Server.getInstance().broadcastText(event.buildChat());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TEXT;
    }
}
