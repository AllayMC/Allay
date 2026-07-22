package org.allaymc.server.network.protocol.v975;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v944.ProtocolV944;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v975.Bedrock_v975;

/** International v975 protocol. */
public class ProtocolV975 extends ProtocolV944 {
    public ProtocolV975() {
        this(Bedrock_v975.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected ProtocolV975(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected ProtocolData.Builder configureData(ProtocolData.Builder builder) {
        return super.configureData(builder).furnaceRecipesAreShapeless();
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoderV975(data);
    }
}
