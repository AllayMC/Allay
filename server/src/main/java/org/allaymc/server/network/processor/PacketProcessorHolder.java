package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.network.ClientStatus;
import org.allaymc.server.network.processor.impl.ingame.*;
import org.allaymc.server.network.processor.impl.login.*;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.EnumMap;

/**
 * @author Cool_Loong
 */
@Slf4j
public final class PacketProcessorHolder {
    private final EnumMap<ClientStatus, EnumMap<BedrockPacketType, PacketProcessor<BedrockPacket>>> processors;

    private ClientStatus clientStatus = ClientStatus.DISCONNECTED;
    private ClientStatus lastClientStatus = null;

    public PacketProcessorHolder() {
        this.processors = new EnumMap<>(ClientStatus.class);
        for (ClientStatus status : ClientStatus.values()) {
            processors.put(status, new EnumMap<>(BedrockPacketType.class));
        }

        this.registerConnectedPacketProcessors();
        this.registerLoggedInPacketProcessors();
        this.registerInGamePacketProcessors();
    }

    public PacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet) {
        var map = processors.get(clientStatus);
        return map != null ? map.get(packet.getPacketType()) : null;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public ClientStatus getLastClientStatus() {
        return lastClientStatus;
    }

    public boolean setClientStatus(ClientStatus clientStatus) {
        return setClientStatus(clientStatus, true);
    }

    // We use lock here because this method won't be called frequently
    // Instead, method getClientStatus() will be called frequently
    public synchronized boolean setClientStatus(ClientStatus clientStatus, boolean warnIfFailed) {
        // PreviousStatus != null means that we should check if the previous status is correct
        if (clientStatus.getPreviousStatus() != null && this.clientStatus != clientStatus.getPreviousStatus()) {
            if (warnIfFailed) {
                log.warn("Failed to set client status to {}. Current is {}", clientStatus, this.clientStatus);
            }
            return false;
        }

        this.lastClientStatus = this.clientStatus;
        this.clientStatus = clientStatus;
        return true;
    }

    private void registerConnectedPacketProcessors() {
        this.registerProcessor(ClientStatus.CONNECTED, new RequestNetworkSettingsPacketProcessor());
        this.registerProcessor(ClientStatus.CONNECTED, new LoginPacketProcessor());
        this.registerProcessor(ClientStatus.CONNECTED, new ClientToServerHandshakePacketProcessor());
    }

    private void registerLoggedInPacketProcessors() {
        this.registerProcessor(ClientStatus.LOGGED_IN, new ClientCacheStatusPacketProcessor());
        this.registerProcessor(ClientStatus.LOGGED_IN, new ResourcePackClientResponsePacketProcessor());
        this.registerProcessor(ClientStatus.LOGGED_IN, new ResourcePackChunkRequestPacketProcessor());
        this.registerProcessor(ClientStatus.LOGGED_IN, new RequestChunkRadiusPacketProcessor());
        this.registerProcessor(ClientStatus.LOGGED_IN, new EmoteListPacketProcessor());
        this.registerProcessor(ClientStatus.LOGGED_IN, new SetLocalPlayerAsInitializedPacketProcessor());
        // Client will send sub chunk request packet during
        // logged in stage if sub chunk sending system is enabled
        this.registerProcessor(ClientStatus.LOGGED_IN, new SubChunkRequestPacketProcessor());

        // Client will start sending auth input packet after logged in, however these packets will be ignored.
        // See PlayerAuthInputPacketProcessor#notReadyForInput()
        this.registerProcessor(ClientStatus.LOGGED_IN, new PlayerAuthInputPacketProcessor());
        this.registerProcessor(ClientStatus.LOGGED_IN, new ServerboundLoadingScreenPacketProcessor());
        // These three packets seem are also sent during initialize chunk sending stage, so we also added them
        this.registerProcessor(ClientStatus.LOGGED_IN, new MobEquipmentPacketProcessor());
        this.registerProcessor(ClientStatus.LOGGED_IN, new InteractPacketProcessor());
        this.registerProcessor(ClientStatus.LOGGED_IN, new MapInfoRequestPacketProcessor());
    }

    private void registerInGamePacketProcessors() {
        this.registerProcessor(ClientStatus.IN_GAME, new AnimatePacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new BlockPickRequestPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new CommandRequestPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new ContainerClosePacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new InteractPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new InventoryTransactionPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new ItemStackRequestPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new MobEquipmentPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new PlayerActionPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new PlayerAuthInputPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new RequestChunkRadiusPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new RespawnPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new SetDefaultGameTypePacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new SetPlayerGameTypePacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new SubChunkRequestPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new TextPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new SettingsCommandPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new ModalFormResponsePacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new ServerSettingsRequestProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new PlayerSkinPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new EntityEventPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new BlockEntityDataPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new EmotePacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new SetPlayerInventoryOptionsPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new BossEventPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new EntityPickRequestPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new ServerboundLoadingScreenPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new AnvilDamagePacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new BookEditPacketProcessor());
        this.registerProcessor(ClientStatus.IN_GAME, new MapInfoRequestPacketProcessor());
    }

    @SuppressWarnings("unchecked")
    public void registerProcessor(ClientStatus status, PacketProcessor<? extends BedrockPacket> processor) {
        processors.get(status).put(processor.getPacketType(), (PacketProcessor<BedrockPacket>) processor);
    }
}
