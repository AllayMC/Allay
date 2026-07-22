package org.allaymc.server.network.protocol.v944;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.ingame.ServerboundDataDrivenScreenClosedPacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.ProtocolFeature;
import org.allaymc.server.network.protocol.v924.ProtocolV924;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v944.Bedrock_v944;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.Set;

/** International v944 protocol. */
public class ProtocolV944 extends ProtocolV924 {
    public ProtocolV944() {
        this(Bedrock_v944.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected ProtocolV944(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected void registerProcessors(PacketProcessorRegistry registry) {
        super.registerProcessors(registry);
        registry.register(
                ClientState.IN_GAME,
                BedrockPacketType.SERVERBOUND_DATA_DRIVEN_SCREEN_CLOSED,
                ServerboundDataDrivenScreenClosedPacketProcessor::new
        );
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoderV944(data);
    }

    @Override
    protected Set<ProtocolFeature> createFeatures() {
        return Set.of(ProtocolFeature.DATA_DRIVEN_UI);
    }
}
