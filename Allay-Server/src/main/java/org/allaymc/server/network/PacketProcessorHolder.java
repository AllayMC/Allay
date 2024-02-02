package org.allaymc.server.network;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.*;
import org.allaymc.server.network.processor.login.*;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.EnumMap;
import java.util.function.BiConsumer;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public final class PacketProcessorHolder {

    private final EnumMap<BedrockPacketType, PacketProcessor<BedrockPacket>> processors = new EnumMap<>(BedrockPacketType.class);
    @Getter
    @Setter
    private BiConsumer<EntityPlayer, String> disconnectProcessor;

    public PacketProcessorHolder() {
        registerDefaultPacketProcessors(this);
    }

    public static void registerDefaultPacketProcessors(PacketProcessorHolder holder) {
        // Disconnect processor
        holder.setDisconnectProcessor(Server.getInstance()::onDisconnect);

        // Login packet processors
        holder.registerProcessor(new RequestNetworkSettingsPacketProcessor());
        holder.registerProcessor(new LoginPacketProcessor());
        holder.registerProcessor(new ClientToServerHandshakePacketProcessor());
        holder.registerProcessor(new ResourcePackClientResponsePacketProcessor());
        holder.registerProcessor(new SetLocalPlayerAsInitializedPacketProcessor());
        holder.registerProcessor(new ResourcePackChunkRequestPacketProcessor());

        // Common packet processors
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
        holder.registerProcessor(new SetDefaultGameTypePacketProcessor());
        holder.registerProcessor(new SetPlayerGameTypePacketProcessor());
        holder.registerProcessor(new SubChunkRequestPacketProcessor());
        holder.registerProcessor(new TextPacketProcessor());
        holder.registerProcessor(new SettingsCommandPacketProcessor());
    }

    public PacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet) {
        return processors.get(packet.getPacketType());
    }

    @SuppressWarnings("unchecked")
    public void registerProcessor(PacketProcessor<? extends BedrockPacket> processor) {
        processors.put(processor.getPacketType(), (PacketProcessor<BedrockPacket>) processor);
    }
}
