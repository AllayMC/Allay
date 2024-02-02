package org.allaymc.api.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

import java.util.function.BiConsumer;

/**
 * Allay Project 2024/2/2
 *
 * @author daoge_cmd
 */
public interface PacketProcessorHolder {

    PacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet);

    void registerProcessor(PacketProcessor<? extends BedrockPacket> processor);

    BiConsumer<EntityPlayer, String> getDisconnectProcessor();

    void setDisconnectProcessor(BiConsumer<EntityPlayer, String> disconnectProcessor);
}
