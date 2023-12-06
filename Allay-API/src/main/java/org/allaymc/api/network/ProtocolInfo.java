package org.allaymc.api.network;

import org.allaymc.api.datastruct.SemVersion;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v622.Bedrock_v622;

public final class ProtocolInfo {

    public static final BedrockCodec PACKET_CODEC = Bedrock_v622.CODEC;

    public static final SemVersion MINECRAFT_VERSION = new SemVersion(1, 20, 40, 3, 0);

    public static final int BLOCK_STATE_VERSION_NO_REVISION = (MINECRAFT_VERSION.major() << 24) | //major
                                                               (MINECRAFT_VERSION.minor() << 16) | //minor
                                                               (MINECRAFT_VERSION.patch() << 8); //patch

    public static final int BLOCK_STATE_VERSION = BLOCK_STATE_VERSION_NO_REVISION | MINECRAFT_VERSION.revision(); //revision

    private ProtocolInfo() {
    }
}
