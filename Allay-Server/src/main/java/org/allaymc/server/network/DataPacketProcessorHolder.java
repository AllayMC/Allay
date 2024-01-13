package org.allaymc.server.network;

import org.allaymc.server.network.processor.*;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.EnumMap;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public final class DataPacketProcessorHolder {
    private final EnumMap<BedrockPacketType, DataPacketProcessor<BedrockPacket>> processorEnumMap = new EnumMap<>(BedrockPacketType.class);

    public DataPacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet) {
        return processorEnumMap.get(packet.getPacketType());
    }

    @SuppressWarnings("unchecked")
    public void registerProcessor(DataPacketProcessor<? extends BedrockPacket> processor) {
        processorEnumMap.put(processor.getPacketType(), (DataPacketProcessor<BedrockPacket>) processor);
    }

    public static void registerDefaultPacketProcessors(DataPacketProcessorHolder holder) {
        holder.registerProcessor(new AnimatePacketProcessor());
        holder.registerProcessor(new BlockPickRequestPacketProcessor());
        holder.registerProcessor(new CommandRequestProcessor());
        holder.registerProcessor(new ContainerClosePacketProcessor());
        holder.registerProcessor(new InteractPacketProcessor());
        holder.registerProcessor(new InventoryTransactionPacketProcessor());
        holder.registerProcessor(new ItemStackRequestPacketProcessor());
        holder.registerProcessor(new MobEquipmentPacketProcessor());
        holder.registerProcessor(new PlayerAuthInputPacketProcessor());
        holder.registerProcessor(new RequestChunkRadiusPacketProcessor());
        holder.registerProcessor(new SubChunkRequestPacketProcessor());
        holder.registerProcessor(new TextPacketProcessor());
    }
}
