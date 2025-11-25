package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.EmotePacket;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EmotePacketProcessor extends PacketProcessor<EmotePacket> {

    public static final int EMOTE_TIME_LIMIT = 20;

    protected long lastEmoteTime;

    @Override
    public PacketSignal handleAsync(Player player, EmotePacket packet, long receiveTime) {
        if (receiveTime - this.lastEmoteTime < EMOTE_TIME_LIMIT) {
            // Emote too fast
            return PacketSignal.HANDLED;
        }
        this.lastEmoteTime = receiveTime;

        var entity = player.getControlledEntity();
        if (packet.getRuntimeEntityId() != entity.getRuntimeId()) {
            log.warn("Player {} tried to send emote packet with wrong runtime entity id", player.getOriginName());
            return PacketSignal.HANDLED;
        }

        try {
            var emoteId = UUID.fromString(packet.getEmoteId());
            entity.forEachViewers(viewer -> viewer.viewPlayerEmote(entity, emoteId));
        } catch (Throwable t) {
            log.warn("Player {} tried to send emote packet with invalid emote id {}", player.getOriginName(), packet.getEmoteId());
        }

        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.EMOTE;
    }
}
