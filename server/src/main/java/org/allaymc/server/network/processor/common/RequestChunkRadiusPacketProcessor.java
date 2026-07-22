package org.allaymc.server.network.processor.common;

import org.allaymc.api.player.Player;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RequestChunkRadiusPacket;

/**
 * @author Cool_Loong
 */
public class RequestChunkRadiusPacketProcessor extends PacketProcessor<RequestChunkRadiusPacket> {
    @Override
    public void handleSync(Player player, RequestChunkRadiusPacket packet, long receiveTime) {
        player.getControlledEntity().setChunkLoadingRadius(
                Math.min(Math.min(packet.getRadius(), packet.getMaxRadius()), AllayServer.getSettings().worldSettings().viewDistance()));
        var allayPlayer = (AllayPlayer) player;
        allayPlayer.sendPacket(allayPlayer.getProtocol().getEncoder().encodeChunkRadiusUpdated(
                player.getControlledEntity().getChunkLoadingRadius()
        ));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_CHUNK_RADIUS;
    }
}
