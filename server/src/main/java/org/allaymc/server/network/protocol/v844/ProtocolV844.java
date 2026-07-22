package org.allaymc.server.network.protocol.v844;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v827.ProtocolV827;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;

/** International v844 protocol. */
public class ProtocolV844 extends ProtocolV827 {
    public ProtocolV844() {
        this(Bedrock_v844.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected ProtocolV844(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected ProtocolData.Builder configureData(ProtocolData.Builder builder) {
        return super.configureData(builder).materialInstancesUsePackedBooleans();
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoderV844(data);
    }
}
