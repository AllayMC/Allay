package org.allaymc.api.network;

import lombok.experimental.UtilityClass;
import org.allaymc.api.datastruct.SemVersion;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v686.Bedrock_v686;

@UtilityClass
public final class ProtocolInfo {

    public static final BedrockCodec PACKET_CODEC = Bedrock_v686.CODEC;

    public static final SemVersion MINECRAFT_VERSION = new SemVersion(1, 21, 2, 3, 0);

    /**
     * Bedrock version of the most recent backwards-incompatible change to blockstates.
     * <p>
     * This is not the same as current game version. It should match the numbers in the
     * newest blockstate upgrade schema used in BedrockBlockUpgradeSchema.
     */
    public static final SemVersion BLOCK_STATE_VERSION = new SemVersion(1, 21, 0, 3, 0);

    public static final int BLOCK_STATE_VERSION_NUM_NO_REVISION = (BLOCK_STATE_VERSION.major() << 24) | // major
                                                                  (BLOCK_STATE_VERSION.minor() << 16) | // minor
                                                                  (BLOCK_STATE_VERSION.patch() << 8); // patch

    public static final int BLOCK_STATE_VERSION_NUM = BLOCK_STATE_VERSION_NUM_NO_REVISION | BLOCK_STATE_VERSION.revision(); // revision

    public static String getMinecraftVersionStr() {
        return MINECRAFT_VERSION.major() + "." + MINECRAFT_VERSION.minor() + "." + MINECRAFT_VERSION.patch();
    }
}
