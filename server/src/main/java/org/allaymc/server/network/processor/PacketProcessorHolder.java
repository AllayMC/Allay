package org.allaymc.server.network.processor;

import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * @author daoge_cmd
 */
public interface PacketProcessorHolder {

    PacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet);

    void registerProcessor(PacketProcessor<? extends BedrockPacket> processor);
}
