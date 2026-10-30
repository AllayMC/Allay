package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.EmoteListPacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

/**
 * {@code EmoteListPacketProcessor} consumes emote-list packets without recording
 * their advertised entries.
 * Handling completes on the asynchronous receive path and does not enqueue
 * world work. This processor exposes no connection-local list state for later
 * emote handling to consult.
 *
 * @author daoge_cmd
 */
public class EmoteListPacketProcessor extends PacketProcessor<EmoteListPacket> {
    /**
     * Consumes the emote-list notification and returns {@code HANDLED} immediately. The
     * packet contents, player, and receive tick are not used, and no
     * synchronous continuation or server emote-list update is requested.
     */
    @Override
    public PacketSignal handleAsync(Player player, EmoteListPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    /**
     * Returns the emote list packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.EMOTE_LIST;
    }
}
