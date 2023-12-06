package org.allaymc.api.network;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v622.Bedrock_v622;

public final class ProtocolInfo {

    public static final int BLOCK_STATE_VERSION_NO_REVISION = (1 << 24) | //major
                                                               (20 << 16) | //minor
                                                               (40 << 8); //patch

    public static final int BLOCK_STATE_VERSION = BLOCK_STATE_VERSION_NO_REVISION | (3); //revision

    private ProtocolInfo() {
    }

    public static final BedrockCodec PACKET_CODEC = Bedrock_v622.CODEC;
}
