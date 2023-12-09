package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.DataPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RequestChunkRadiusPacket;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class RequestChunkRadiusPacketProcessor extends DataPacketProcessor<RequestChunkRadiusPacket> {
    @Override
    public void handle(EntityPlayer player, RequestChunkRadiusPacket pk) {
        var radius = pk.getRadius();
        var maxRadius = pk.getMaxRadius();
        if (radius > maxRadius) {
            radius = maxRadius;
        }
        player.setChunkLoadingRadius(radius);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_CHUNK_RADIUS;
    }
}
