package org.allaymc.server.network.protocol.v898;

import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.ingame.ServerboundDataStorePacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.NetworkItemPalette;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v860.Protocol_v860;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * {@code Protocol_v898} configures wire protocol 898 within the international
 * protocol inheritance chain. This version adds the gameplay data-store
 * processor and advertises custom collision shapes as individual boxes. Other
 * processor registrations remain inherited, and initialization selects the v898
 * encoder using this protocol's completed payload data.
 */
public class Protocol_v898 extends Protocol_v860 {
    /**
     * Creates an uninitialized international protocol using the v898 codec. The
     * constructor records its identity without building payloads or processors;
     * callers must invoke {@code initialize} after all subtype construction has
     * finished.
     */
    public Protocol_v898() {
        this(Bedrock_v898.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v898(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Adds the data-store processor for clients already in the gameplay state.
     * Parent registration runs first, preserving existing login and gameplay
     * handlers before this additional packet-type mapping is installed.
     */
    @Override
    protected void registerProcessors(PacketProcessorRegistry registry) {
        super.registerProcessors(registry);
        registry.register(
                ClientState.IN_GAME,
                BedrockPacketType.SERVERBOUND_DATA_STORE,
                ServerboundDataStorePacketProcessor::new
        );
    }

    /**
     * Encodes custom collision shapes as individual solid boxes in
     * sixteenth-block coordinates. {@code null} or empty shapes disable
     * collision; using the shared box-list implementation preserves separate
     * solids instead of merging their bounds.
     */
    @Override
    protected NbtMap encodeCollisionBox(VoxelShape shape) {
        return encodeBoxListCollision(shape);
    }

    /**
     * Creates the {@code PacketEncoder_v898} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected NetworkItemPalette createItemPalette() {
        return NetworkItemPalette.of("898");
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v898(data);
    }
}
