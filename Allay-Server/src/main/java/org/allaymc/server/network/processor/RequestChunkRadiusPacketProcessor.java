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
    // TODO: check it
    // It seems that we should use `maxRadius` instead of `Radius`
    // `maxRadius` is always smaller than `Radius` and it is seems to be the real chunk loading radius
    @Override
    public void handle(EntityPlayer player, RequestChunkRadiusPacket pk) {
        player.setChunkLoadingRadius(pk.getMaxRadius());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_CHUNK_RADIUS;
    }
}
