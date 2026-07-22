package org.allaymc.server.network.protocol.v844;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v827.Protocol_v827;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;

public class Protocol_v844 extends Protocol_v827 {
    public Protocol_v844() {
        this(Bedrock_v844.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v844(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected NbtMap encodeCustomBlockDefinition(NbtMap definition) {
        return transformBlockDefinition(definition, Protocol_v844::downgradeCollisionBox);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v844(data);
    }
}
