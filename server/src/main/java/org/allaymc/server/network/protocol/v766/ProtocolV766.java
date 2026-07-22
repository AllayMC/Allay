package org.allaymc.server.network.protocol.v766;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.NetworkData;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.common.*;
import org.allaymc.server.network.processor.ingame.*;
import org.allaymc.server.network.processor.login.*;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.Protocol;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v766.Bedrock_v766;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.EnumSet;

/**
 * Oldest complete protocol baseline. The international v766 variant is intentionally not registered.
 */
public abstract class ProtocolV766 extends Protocol {
    public ProtocolV766() {
        this(Bedrock_v766.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected ProtocolV766(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected void registerProcessors(PacketProcessorRegistry registry) {
        registry.register(
                EnumSet.of(ClientState.CONNECTED, ClientState.LOGGED_IN, ClientState.SPAWNED, ClientState.IN_GAME),
                BedrockPacketType.PACKET_VIOLATION_WARNING,
                PacketViolationWarningPacketProcessor::new
        );

        registry.register(ClientState.CONNECTED, BedrockPacketType.LOGIN, LoginPacketProcessor::new);
        registry.register(ClientState.CONNECTED, BedrockPacketType.CLIENT_TO_SERVER_HANDSHAKE, ClientToServerHandshakePacketProcessor::new);

        registry.register(ClientState.LOGGED_IN, BedrockPacketType.CLIENT_CACHE_STATUS, ClientCacheStatusPacketProcessor::new);
        registry.register(ClientState.LOGGED_IN, BedrockPacketType.RESOURCE_PACK_CLIENT_RESPONSE, ResourcePackClientResponsePacketProcessor::new);
        registry.register(ClientState.LOGGED_IN, BedrockPacketType.RESOURCE_PACK_CHUNK_REQUEST, ResourcePackChunkRequestPacketProcessor::new);

        registry.register(
                EnumSet.of(ClientState.SPAWNED, ClientState.IN_GAME),
                BedrockPacketType.REQUEST_CHUNK_RADIUS,
                RequestChunkRadiusPacketProcessor::new
        );
        registry.register(ClientState.SPAWNED, BedrockPacketType.EMOTE_LIST, EmoteListPacketProcessor::new);
        registry.register(ClientState.SPAWNED, BedrockPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED, SetLocalPlayerAsInitializedPacketProcessor::new);
        registry.register(
                EnumSet.of(ClientState.SPAWNED, ClientState.IN_GAME),
                BedrockPacketType.SUB_CHUNK_REQUEST,
                SubChunkRequestPacketProcessor::new
        );
        registry.register(
                EnumSet.of(ClientState.SPAWNED, ClientState.IN_GAME),
                BedrockPacketType.CLIENT_CACHE_BLOB_STATUS,
                ClientCacheBlobStatusPacketProcessor::new
        );
        registry.register(ClientState.SPAWNED, BedrockPacketType.PLAYER_AUTH_INPUT, () -> new NoopPacketProcessor(BedrockPacketType.PLAYER_AUTH_INPUT));
        registry.register(ClientState.SPAWNED, BedrockPacketType.MOB_EQUIPMENT, () -> new NoopPacketProcessor(BedrockPacketType.MOB_EQUIPMENT));
        registry.register(ClientState.SPAWNED, BedrockPacketType.INTERACT, () -> new NoopPacketProcessor(BedrockPacketType.INTERACT));
        registry.register(
                EnumSet.of(ClientState.SPAWNED, ClientState.IN_GAME),
                BedrockPacketType.SERVERBOUND_LOADING_SCREEN,
                () -> new NoopPacketProcessor(BedrockPacketType.SERVERBOUND_LOADING_SCREEN)
        );
        registry.register(
                EnumSet.of(ClientState.SPAWNED, ClientState.IN_GAME),
                BedrockPacketType.SERVERBOUND_DIAGNOSTICS,
                () -> new NoopPacketProcessor(BedrockPacketType.SERVERBOUND_DIAGNOSTICS)
        );
        registry.register(
                EnumSet.of(ClientState.SPAWNED, ClientState.IN_GAME),
                BedrockPacketType.MAP_INFO_REQUEST,
                MapInfoRequestPacketProcessor::new
        );

        registry.register(ClientState.IN_GAME, BedrockPacketType.ANIMATE, AnimatePacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.BLOCK_PICK_REQUEST, BlockPickRequestPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.COMMAND_REQUEST, CommandRequestPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.CONTAINER_CLOSE, ContainerClosePacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.INTERACT, InteractPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.INVENTORY_TRANSACTION, InventoryTransactionPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.ITEM_STACK_REQUEST, ItemStackRequestPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.MOB_EQUIPMENT, MobEquipmentPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.PLAYER_ACTION, PlayerActionPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.PLAYER_AUTH_INPUT, PlayerAuthInputPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.RESPAWN, RespawnPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.SET_DEFAULT_GAME_TYPE, SetDefaultGameTypePacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.SET_PLAYER_GAME_TYPE, SetPlayerGameTypePacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.SET_DIFFICULTY, SetDifficultyPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.TEXT, TextPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.SETTINGS_COMMAND, SettingsCommandPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.MODAL_FORM_RESPONSE, ModalFormResponsePacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.SERVER_SETTINGS_REQUEST, ServerSettingsRequestProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.PLAYER_SKIN, PlayerSkinPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.ENTITY_EVENT, EntityEventPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.BLOCK_ENTITY_DATA, BlockEntityDataPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.EMOTE, EmotePacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.SET_PLAYER_INVENTORY_OPTIONS, SetPlayerInventoryOptionsPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.BOSS_EVENT, BossEventPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.ENTITY_PICK_REQUEST, EntityPickRequestPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.ANVIL_DAMAGE, AnvilDamagePacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.BOOK_EDIT, BookEditPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.REQUEST_ABILITY, RequestAbilityPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.REQUEST_PERMISSIONS, RequestPermissionsPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.NPC_REQUEST, NPCRequestPacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.LECTERN_UPDATE, LecternUpdatePacketProcessor::new);
        registry.register(ClientState.IN_GAME, BedrockPacketType.COMMAND_BLOCK_UPDATE, CommandBlockUpdatePacketProcessor::new);
    }

    @Override
    protected ProtocolData createData(NetworkData source) {
        return configureData(ProtocolData.builder(source, getProtocolVersion(), getVariant())).build();
    }

    /**
     * Applies cumulative data-format changes for this protocol inheritance branch.
     */
    protected ProtocolData.Builder configureData(ProtocolData.Builder builder) {
        return builder;
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoderV766(data);
    }
}
