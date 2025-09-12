package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.ClientState;
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
    private final EnumMap<ClientState, EnumMap<BedrockPacketType, PacketProcessor<BedrockPacket>>> processors;

    private ClientState clientState = ClientState.DISCONNECTED;
    private ClientState lastClientState = null;

    public PacketProcessorHolder() {
        this.processors = new EnumMap<>(ClientState.class);
        for (ClientState state : ClientState.values()) {
            processors.put(state, new EnumMap<>(BedrockPacketType.class));
        }

        this.registerConnectedPacketProcessors();
        this.registerLoggedInPacketProcessors();
        this.registerInGamePacketProcessors();
    }

    public PacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet) {
        var map = processors.get(clientState);
        return map != null ? map.get(packet.getPacketType()) : null;
    }

    public ClientState getClientState() {
        return clientState;
    }

    public ClientState getLastClientState() {
        return lastClientState;
    }

    public boolean setClientState(ClientState clientState) {
        return setClientState(clientState, true);
    }

    // We use lock here because this method won't be called frequently
    // Instead, method getClientState() will be called frequently
    public synchronized boolean setClientState(ClientState clientState, boolean warnIfFailed) {
        // PreviousState != null means that we should check if the previous state is correct
        if (clientState.getPreviousState() != null && this.clientState != clientState.getPreviousState()) {
            if (warnIfFailed) {
                log.warn("Failed to set client state to {}. Current is {}", clientState, this.clientState);
            }
            return false;
        }

        this.lastClientState = this.clientState;
        this.clientState = clientState;
        return true;
    }

    private void registerConnectedPacketProcessors() {
        this.registerProcessor(ClientState.CONNECTED, new RequestNetworkSettingsPacketProcessor());
        this.registerProcessor(ClientState.CONNECTED, new LoginPacketProcessor());
        this.registerProcessor(ClientState.CONNECTED, new ClientToServerHandshakePacketProcessor());
    }

    private void registerLoggedInPacketProcessors() {
        this.registerProcessor(ClientState.LOGGED_IN, new ClientCacheStatusPacketProcessor());
        this.registerProcessor(ClientState.LOGGED_IN, new ResourcePackClientResponsePacketProcessor());
        this.registerProcessor(ClientState.LOGGED_IN, new ResourcePackChunkRequestPacketProcessor());
        this.registerProcessor(ClientState.LOGGED_IN, new RequestChunkRadiusPacketProcessor());
        this.registerProcessor(ClientState.LOGGED_IN, new EmoteListPacketProcessor());
        this.registerProcessor(ClientState.LOGGED_IN, new SetLocalPlayerAsInitializedPacketProcessor());
        // Client will send sub chunk request packet during
        // logged in stage if sub chunk sending system is enabled
        this.registerProcessor(ClientState.LOGGED_IN, new SubChunkRequestPacketProcessor());

        // Client will start sending auth input packet after logged in, however these packets will be ignored.
        // See PlayerAuthInputPacketProcessor#notReadyForInput()
        this.registerProcessor(ClientState.LOGGED_IN, new PlayerAuthInputPacketProcessor());
        this.registerProcessor(ClientState.LOGGED_IN, new ServerboundLoadingScreenPacketProcessor());
        // These three packets seem are also sent during initialize chunk sending stage, so we also added them
        this.registerProcessor(ClientState.LOGGED_IN, new MobEquipmentPacketProcessor());
        this.registerProcessor(ClientState.LOGGED_IN, new InteractPacketProcessor());
        this.registerProcessor(ClientState.LOGGED_IN, new MapInfoRequestPacketProcessor());
    }

    private void registerInGamePacketProcessors() {
        this.registerProcessor(ClientState.IN_GAME, new AnimatePacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new BlockPickRequestPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new CommandRequestPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new ContainerClosePacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new InteractPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new InventoryTransactionPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new ItemStackRequestPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new MobEquipmentPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new PlayerActionPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new PlayerAuthInputPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new RequestChunkRadiusPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new RespawnPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new SetDefaultGameTypePacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new SetPlayerGameTypePacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new SubChunkRequestPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new TextPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new SettingsCommandPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new ModalFormResponsePacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new ServerSettingsRequestProcessor());
        this.registerProcessor(ClientState.IN_GAME, new PlayerSkinPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new EntityEventPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new BlockEntityDataPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new EmotePacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new SetPlayerInventoryOptionsPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new BossEventPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new EntityPickRequestPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new ServerboundLoadingScreenPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new AnvilDamagePacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new BookEditPacketProcessor());
        this.registerProcessor(ClientState.IN_GAME, new MapInfoRequestPacketProcessor());
    }

    @SuppressWarnings("unchecked")
    public void registerProcessor(ClientState state, PacketProcessor<? extends BedrockPacket> processor) {
        processors.get(state).put(processor.getPacketType(), (PacketProcessor<BedrockPacket>) processor);
    }
}
