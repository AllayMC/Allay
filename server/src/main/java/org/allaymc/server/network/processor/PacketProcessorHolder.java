package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.ingame.*;
import org.allaymc.server.network.processor.login.*;
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

        registerConnectedPacketProcessors();
        registerLoggedInPacketProcessors();
        registerInGamePacketProcessors();
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
        registerProcessor(ClientState.CONNECTED, new RequestNetworkSettingsPacketProcessor());
        registerProcessor(ClientState.CONNECTED, new LoginPacketProcessor());
        registerProcessor(ClientState.CONNECTED, new ClientToServerHandshakePacketProcessor());
    }

    private void registerLoggedInPacketProcessors() {
        registerProcessor(ClientState.LOGGED_IN, new ClientCacheStatusPacketProcessor());
        registerProcessor(ClientState.LOGGED_IN, new ResourcePackClientResponsePacketProcessor());
        registerProcessor(ClientState.LOGGED_IN, new ResourcePackChunkRequestPacketProcessor());
        registerProcessor(ClientState.LOGGED_IN, new RequestChunkRadiusPacketProcessor());
        registerProcessor(ClientState.LOGGED_IN, new EmoteListPacketProcessor());
        registerProcessor(ClientState.LOGGED_IN, new SetLocalPlayerAsInitializedPacketProcessor());

        // Client will send sub chunk request packets during logged-in stage if the sub chunk
        // sending system is enabled
        registerProcessor(ClientState.LOGGED_IN, new SubChunkRequestPacketProcessor());

        // Client will start sending the auth input packet after logged in, however, these packets will be ignored.
        // See PlayerAuthInputPacketProcessor#notReadyForInput()
        registerProcessor(ClientState.LOGGED_IN, new PlayerAuthInputPacketProcessor());
        registerProcessor(ClientState.LOGGED_IN, new ServerboundLoadingScreenPacketProcessor());

        // These three packets seem are also sent during initialize chunk sending stage, so we also added them
        registerProcessor(ClientState.LOGGED_IN, new MobEquipmentPacketProcessor());
        registerProcessor(ClientState.LOGGED_IN, new InteractPacketProcessor());
        registerProcessor(ClientState.LOGGED_IN, new MapInfoRequestPacketProcessor());
        registerProcessor(ClientState.LOGGED_IN, new ServerboundDiagnosticsPacketProcessor());
    }

    private void registerInGamePacketProcessors() {
        registerProcessor(ClientState.IN_GAME, new AnimatePacketProcessor());
        registerProcessor(ClientState.IN_GAME, new BlockPickRequestPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new CommandRequestPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new ContainerClosePacketProcessor());
        registerProcessor(ClientState.IN_GAME, new InteractPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new InventoryTransactionPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new ItemStackRequestPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new MobEquipmentPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new PlayerActionPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new PlayerAuthInputPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new RequestChunkRadiusPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new RespawnPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new SetDefaultGameTypePacketProcessor());
        registerProcessor(ClientState.IN_GAME, new SetPlayerGameTypePacketProcessor());
        registerProcessor(ClientState.IN_GAME, new SetDifficultyPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new SubChunkRequestPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new TextPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new SettingsCommandPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new ModalFormResponsePacketProcessor());
        registerProcessor(ClientState.IN_GAME, new ServerSettingsRequestProcessor());
        registerProcessor(ClientState.IN_GAME, new PlayerSkinPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new EntityEventPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new BlockEntityDataPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new EmotePacketProcessor());
        registerProcessor(ClientState.IN_GAME, new SetPlayerInventoryOptionsPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new BossEventPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new EntityPickRequestPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new ServerboundLoadingScreenPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new AnvilDamagePacketProcessor());
        registerProcessor(ClientState.IN_GAME, new BookEditPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new MapInfoRequestPacketProcessor());
        registerProcessor(ClientState.IN_GAME, new ServerboundDiagnosticsPacketProcessor());
    }

    @SuppressWarnings("unchecked")
    public void registerProcessor(ClientState state, PacketProcessor<? extends BedrockPacket> processor) {
        processors.get(state).put(processor.getPacketType(), (PacketProcessor<BedrockPacket>) processor);
    }
}
