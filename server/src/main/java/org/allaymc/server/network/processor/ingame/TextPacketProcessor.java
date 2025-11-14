package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerChatEvent;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

/**
 * @author Cool_Loong
 */
@Slf4j
public class TextPacketProcessor extends PacketProcessor<TextPacket> {
    @Override
    public void handleSync(EntityPlayer player, TextPacket packet, long receiveTime) {
        if (packet.getType() != TextPacket.Type.CHAT) {
            log.warn("Player {} attempted to send a non-chat text packet", player.getOriginName());
            return;
        }

        if (!player.hasPermission(Permissions.ABILITY_CHAT)) {
            return;
        }

        var event = new PlayerChatEvent(player, "<" + player.getDisplayName() + "> ", packet.getMessage());
        if (event.call()) {
            String text = event.buildChat();
            Server.getInstance().getMessageChannel().broadcastMessage(text);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TEXT;
    }
}
