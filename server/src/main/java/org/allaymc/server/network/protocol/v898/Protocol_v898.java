package org.allaymc.server.network.protocol.v898;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.ingame.ServerboundDataStorePacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v860.Protocol_v860;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

public class Protocol_v898 extends Protocol_v860 {
    public Protocol_v898() {
        this(Bedrock_v898.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v898(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected void registerProcessors(PacketProcessorRegistry registry) {
        super.registerProcessors(registry);
        registry.register(
                ClientState.IN_GAME,
                BedrockPacketType.SERVERBOUND_DATA_STORE,
                ServerboundDataStorePacketProcessor::new
        );
    }

    @Override
    protected ProtocolData.Builder configureData(ProtocolData.Builder builder) {
        return super.configureData(builder).multipleCollisionBoxes();
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v898(data);
    }
}
