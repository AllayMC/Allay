package org.allaymc.server.network.protocol.v766;

import org.allaymc.api.block.data.TintMethod;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.player.ClientState;
import org.allaymc.server.block.type.CustomBlockStateDefinition;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.common.*;
import org.allaymc.server.network.processor.ingame.*;
import org.allaymc.server.network.processor.login.*;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.Protocol;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v766.Bedrock_v766;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.EnumSet;
import java.util.Locale;

/**
 * {@code Protocol_v766} supplies baseline packet processors and legacy
 * custom-block encoding for the international protocol inheritance chain. Its
 * processor registrations cover each client state, and its material and
 * collision hooks select the older component layouts. The public constructor
 * selects the v766 codec, but the default registry does not register an
 * international v766 instance. Concrete variants and later protocol classes
 * inherit this baseline while retaining their own negotiated identities.
 */
public abstract class Protocol_v766 extends Protocol {
    /**
     * Creates an uninitialized international protocol using the v766 codec. The
     * constructor records its identity without building payloads or processors;
     * callers must invoke {@code initialize} after all subtype construction has
     * finished.
     */
    public Protocol_v766() {
        this(Bedrock_v766.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v766(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Registers the baseline processors by client state and packet type. Some
     * factories span spawned and gameplay states, while selected packets
     * receive no-op processors during spawning; later protocol hooks may
     * replace individual registrations before the registry freezes.
     */
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
        // Spawn-time packets can arrive before gameplay admission; no-op
        // handlers consume these types until the in-game registrations become
        // selectable.
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

    /**
     * Encodes the legacy material fields with face dimming stored as an
     * individual boolean. Texture, rendering mode, ambient occlusion, and
     * lowercase tint are retained; newer packed flags are not emitted by this
     * baseline.
     */
    @Override
    protected NbtMap encodeMaterialInstance(
            CustomBlockStateDefinition.MaterialInstance material,
            TintMethod tintMethod
    ) {
        // Legacy material payloads have a separate face_dimming field, so the
        // later packed flag representation cannot be reused by this baseline.
        return NbtMap.builder()
                .putString("texture", material.texture())
                .putString("render_method", material.renderMethod().getId())
                .putFloat("ambient_occlusion", material.ambientOcclusionIntensity())
                .putBoolean("face_dimming", material.faceDimming())
                .putString("tint_method", tintMethod.name().toLowerCase(Locale.ROOT))
                .build();
    }

    /**
     * Encodes collision using the legacy merged-box representation.
     * {@code null} or empty shapes disable collision; separate solids become
     * one bounding box with the inherited horizontal-origin shift and one-block
     * height cap.
     */
    @Override
    protected NbtMap encodeCollisionBox(VoxelShape shape) {
        return encodeMergedBox(shape);
    }

    /**
     * Creates the {@code PacketEncoder_v766} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v766(data);
    }
}
