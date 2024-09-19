package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.EmotePacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EmotePacketProcessor extends PacketProcessor<EmotePacket> {

    public static final int EMOTE_TIME_LIMIT = 20;

    protected long lastEmoteTime;

    @Override
    public PacketSignal handleAsync(EntityPlayer player, EmotePacket packet, long receiveTime) {
        if (receiveTime - lastEmoteTime < EMOTE_TIME_LIMIT) {
            // Emote too fast
            return PacketSignal.HANDLED;
        }
        lastEmoteTime = receiveTime;

        if (packet.getRuntimeEntityId() != player.getRuntimeId()) {
            log.warn("Player {} tried to send emote packet with wrong runtime entity id", player.getOriginName());
        }
        if (isInvalidEmoteId(packet)) {
            log.warn("Player {} tried to send emote packet with invalid emote id {}", player.getOriginName(), packet.getEmoteId());
        }

        player.sendPacketToViewers(packet);

        return PacketSignal.HANDLED;
    }

    protected boolean isInvalidEmoteId(EmotePacket packet) {
        return packet.getEmoteId() == null || packet.getEmoteId().isEmpty() || packet.getEmoteId().length() > 100;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.EMOTE;
    }
}
