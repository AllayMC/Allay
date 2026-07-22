package org.allaymc.server.network.protocol.v844;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v827.Protocol_v827;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;

/** International v844 protocol. */
public class Protocol_v844 extends Protocol_v827 {
    public Protocol_v844() {
        this(Bedrock_v844.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v844(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected ProtocolData.Builder configureData(ProtocolData.Builder builder) {
        return super.configureData(builder).materialInstancesUsePackedBooleans();
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v844(data);
    }
}
