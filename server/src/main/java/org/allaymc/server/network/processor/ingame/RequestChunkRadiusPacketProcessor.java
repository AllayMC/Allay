package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ChunkRadiusUpdatedPacket;
import org.cloudburstmc.protocol.bedrock.packet.RequestChunkRadiusPacket;

/**
 * @author Cool_Loong
 */
public class RequestChunkRadiusPacketProcessor extends PacketProcessor<RequestChunkRadiusPacket> {
    @Override
    public void handleSync(Player player, RequestChunkRadiusPacket packet, long receiveTime) {
        player.getControlledEntity().setChunkLoadingRadius(Math.min(packet.getRadius(), packet.getMaxRadius()));
        var p = new ChunkRadiusUpdatedPacket();
        p.setRadius(player.getControlledEntity().getChunkLoadingRadius());
        player.sendPacket(p);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_CHUNK_RADIUS;
    }
}
