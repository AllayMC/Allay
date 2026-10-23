package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.BossEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

/**
 * {@code BossEventPacketProcessor} consumes incoming boss-event packets without
 * modifying server boss bars. The asynchronous hook marks each packet handled,
 * leaving server-driven boss-bar
 * presentation to outbound encoding. No synchronous handler is scheduled for a
 * packet accepted by this processor.
 *
 * @author daoge_cmd
 */
public class BossEventPacketProcessor extends PacketProcessor<BossEventPacket> {

    /**
     * Consumes an incoming boss event without applying its payload to server
     * state. Returning {@code HANDLED} ends processing on the receive path, so the
     * inherited synchronous hook is not requested.
     */
    @Override
    public PacketSignal handleAsync(Player player, BossEventPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    /**
     * Returns the boss event packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BOSS_EVENT;
    }
}
