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
 * {@code TextPacketProcessor} handles permitted player chat messages through the
 * cancellable chat event.
 * Non-chat packet kinds are logged and rejected, and the controlled entity must
 * have chat permission. Accepted events build the final formatted message for
 * broadcast through the server message channel.
 *
 * @author Cool_Loong
 */
@Slf4j
public class TextPacketProcessor extends PacketProcessor<TextPacket> {
    /**
     * Broadcasts a {@code CHAT} message only when the controlled entity has chat
     * permission and its chat event is accepted. Listeners can alter the prefix
     * and message before {@code buildChat} produces the text sent to the message
     * channel.
     */
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
        // Build the message only after event approval so listener changes to
        // the prefix or content determine the text actually broadcast.
        if (event.call()) {
            String text = event.buildChat();
            Server.getInstance().getMessageChannel().broadcastMessage(text);
        }
    }

    /**
     * Returns the text packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TEXT;
    }
}
