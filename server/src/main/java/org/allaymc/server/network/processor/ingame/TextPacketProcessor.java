package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.player.PlayerChatEvent;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.Player;
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
    public void handleSync(Player player, TextPacket packet, long receiveTime) {
        if (packet.getType() != TextPacket.Type.CHAT) {
            log.warn("Player {} attempted to send a non-chat text packet", player.getOriginName());
            return;
        }

        var entity = player.getControlledEntity();
        if (!entity.hasPermission(Permissions.ABILITY_CHAT).asBoolean()) {
            return;
        }

        var event = new PlayerChatEvent(entity, "<" + entity.getDisplayName() + "> ", packet.getMessage());
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
