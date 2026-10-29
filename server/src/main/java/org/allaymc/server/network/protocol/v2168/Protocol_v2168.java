package org.allaymc.server.network.protocol.v2168;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v1001.Protocol_v1001;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168_hotfix4;

/**
 * International protocol v2168, covering Bedrock 1.26.40 and the 1.26.44 hotfix.
 */
public class Protocol_v2168 extends Protocol_v1001 {

    public Protocol_v2168() {
        this(Bedrock_v2168_hotfix4.CODEC, ClientVariant.INTERNATIONAL);
    }

    protected Protocol_v2168(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v2168(data);
    }
}
