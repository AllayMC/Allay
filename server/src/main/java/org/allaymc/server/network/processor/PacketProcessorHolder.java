package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.network.ClientStatus;
import org.allaymc.server.network.processor.impl.ingame.*;
import org.allaymc.server.network.processor.impl.login.*;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Cool_Loong
 */
@Slf4j
public final class PacketProcessorHolder {

    private final EnumMap<ClientStatus, EnumMap<BedrockPacketType, PacketProcessor<BedrockPacket>>> processors;
    private final AtomicReference<ClientStatus> clientStatus = new AtomicReference<>(ClientStatus.NEW);

    public PacketProcessorHolder() {
        this.processors = new EnumMap<>(ClientStatus.class);
        for (ClientStatus status : ClientStatus.values()) {
            if (status == ClientStatus.NEW) {
                continue;
            }
            processors.put(status, new EnumMap<>(BedrockPacketType.class));
        }

        registerConnectedPacketProcessors();
        registerLoggedInPacketProcessors();
        registerInGamePacketProcessors();
    }

    public PacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet) {
        var map = processors.get(clientStatus.get());
        return map != null ? map.get(packet.getPacketType()) : null;
    }

    public ClientStatus getClientStatus() {
        return clientStatus.get();
    }

    public boolean setClientStatus(ClientStatus clientStatus) {
        if (!this.clientStatus.compareAndSet(clientStatus.getPreviousStatus(), clientStatus)) {
            log.warn("Failed to set client status to {}. Current is {}", clientStatus, this.clientStatus.get());
            return false;
        }
        return true;
    }

    private void registerConnectedPacketProcessors() {
        registerConnectedProcessor(new RequestNetworkSettingsPacketProcessor());
        registerConnectedProcessor(new LoginPacketProcessor());
        registerConnectedProcessor(new ClientToServerHandshakePacketProcessor());
    }

    private void registerLoggedInPacketProcessors() {
        registerLoggedInProcessor(new ClientCacheStatusPacketProcessor());
        registerLoggedInProcessor(new ResourcePackClientResponsePacketProcessor());
        registerLoggedInProcessor(new ResourcePackChunkRequestPacketProcessor());
        registerLoggedInProcessor(new RequestChunkRadiusPacketProcessor());
        registerLoggedInProcessor(new EmoteListPacketProcessor());
        registerLoggedInProcessor(new SetLocalPlayerAsInitializedPacketProcessor());

        // Client will start sending auth input packet after logged in, however these packets will be ignored.
        // See PlayerAuthInputPacketProcessor#notReadyForInput()
        registerLoggedInProcessor(new PlayerAuthInputPacketProcessor());
        registerLoggedInProcessor(new ServerboundLoadingScreenPacketProcessor());
        // These two packets seem are also sent during initialize chunk sending stage, so we also added them
        registerLoggedInProcessor(new MobEquipmentPacketProcessor());
        registerLoggedInProcessor(new InteractPacketProcessor());
    }

    private void registerInGamePacketProcessors() {
        registerInGameProcessor(new AnimatePacketProcessor());
        registerInGameProcessor(new BlockPickRequestPacketProcessor());
        registerInGameProcessor(new CommandRequestPacketProcessor());
        registerInGameProcessor(new ContainerClosePacketProcessor());
        registerInGameProcessor(new InteractPacketProcessor());
        registerInGameProcessor(new InventoryTransactionPacketProcessor());
        registerInGameProcessor(new ItemStackRequestPacketProcessor());
        registerInGameProcessor(new MobEquipmentPacketProcessor());
        registerInGameProcessor(new PlayerActionPacketProcessor());
        registerInGameProcessor(new PlayerAuthInputPacketProcessor());
        registerInGameProcessor(new RequestChunkRadiusPacketProcessor());
        registerInGameProcessor(new RespawnPacketProcessor());
        registerInGameProcessor(new SetDefaultGameTypePacketProcessor());
        registerInGameProcessor(new SetPlayerGameTypePacketProcessor());
        registerInGameProcessor(new SubChunkRequestPacketProcessor());
        registerInGameProcessor(new TextPacketProcessor());
        registerInGameProcessor(new SettingsCommandPacketProcessor());
        registerInGameProcessor(new ModalFormResponsePacketProcessor());
        registerInGameProcessor(new ServerSettingsRequestProcessor());
        registerInGameProcessor(new PlayerSkinPacketProcessor());
        registerInGameProcessor(new LevelSoundEvent2PacketProcessor());
        registerInGameProcessor(new EntityEventPacketProcessor());
        registerInGameProcessor(new BlockEntityDataPacketProcessor());
        registerInGameProcessor(new EmotePacketProcessor());
        registerInGameProcessor(new SetPlayerInventoryOptionsPacketProcessor());
        registerInGameProcessor(new BossEventPacketProcessor());
    }

    private void registerConnectedProcessor(PacketProcessor<? extends BedrockPacket> processor) {
        processors.get(ClientStatus.CONNECTED).put(processor.getPacketType(), (PacketProcessor<BedrockPacket>) processor);
    }

    private void registerLoggedInProcessor(PacketProcessor<? extends BedrockPacket> processor) {
        processors.get(ClientStatus.LOGGED_IN).put(processor.getPacketType(), (PacketProcessor<BedrockPacket>) processor);
    }

    private void registerInGameProcessor(PacketProcessor<? extends BedrockPacket> processor) {
        processors.get(ClientStatus.IN_GAME).put(processor.getPacketType(), (PacketProcessor<BedrockPacket>) processor);
    }
}
