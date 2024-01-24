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

    private final EnumMap<BedrockPacketType, DataPacketProcessor<BedrockPacket>> processors = new EnumMap<>(BedrockPacketType.class);

    public static void registerDefaultPacketProcessors(DataPacketProcessorHolder holder) {
        holder.registerProcessor(new AnimatePacketProcessor());
        holder.registerProcessor(new BlockPickRequestPacketProcessor());
        holder.registerProcessor(new CommandRequestPacketProcessor());
        holder.registerProcessor(new ContainerClosePacketProcessor());
        holder.registerProcessor(new InteractPacketProcessor());
        holder.registerProcessor(new InventoryTransactionPacketProcessor());
        holder.registerProcessor(new ItemStackRequestPacketProcessor());
        holder.registerProcessor(new MobEquipmentPacketProcessor());
        holder.registerProcessor(new PlayerActionPacketProcessor());
        holder.registerProcessor(new PlayerAuthInputPacketProcessor());
        holder.registerProcessor(new RequestChunkRadiusPacketProcessor());
        holder.registerProcessor(new RespawnPacketProcessor());
        holder.registerProcessor(new SubChunkRequestPacketProcessor());
        holder.registerProcessor(new TextPacketProcessor());
    }

    public DataPacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet) {
        return processors.get(packet.getPacketType());
    }

    @SuppressWarnings("unchecked")
    public void registerProcessor(DataPacketProcessor<? extends BedrockPacket> processor) {
        processors.put(processor.getPacketType(), (DataPacketProcessor<BedrockPacket>) processor);
    }
}
