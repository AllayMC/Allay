package org.allaymc.server.network.protocol.v944;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.ingame.ServerboundDataDrivenScreenClosedPacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v924.Protocol_v924;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v944.Bedrock_v944;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * {@code Protocol_v944} configures wire protocol 944 within the international
 * protocol inheritance chain. This version adds the gameplay notification for a
 * closed data-driven screen. Existing processor registrations remain inherited,
 * and initialization selects the v944 encoder using the same protocol data
 * bundle supplied by the base initialization lifecycle.
 */
public class Protocol_v944 extends Protocol_v924 {
    /**
     * Creates an uninitialized international protocol using the v944 codec. The
     * constructor records its identity without building payloads or processors;
     * callers must invoke {@code initialize} after all subtype construction has
     * finished.
     */
    public Protocol_v944() {
        this(Bedrock_v944.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v944(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Adds the data-driven screen-close processor for clients in the gameplay
     * state. Inherited processors are registered first, so the new notification
     * extends the existing state-specific handlers before initialization
     * freezes the registry.
     */
    @Override
    protected void registerProcessors(PacketProcessorRegistry registry) {
        super.registerProcessors(registry);
        registry.register(
                ClientState.IN_GAME,
                BedrockPacketType.SERVERBOUND_DATA_DRIVEN_SCREEN_CLOSED,
                ServerboundDataDrivenScreenClosedPacketProcessor::new
        );
    }

    /**
     * Creates the {@code PacketEncoder_v944} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v944(data);
    }
}
