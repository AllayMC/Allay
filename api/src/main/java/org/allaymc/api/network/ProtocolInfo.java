package org.allaymc.api.network;

import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.SemVersion;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v729.Bedrock_v729;

/**
 * @author daoge_cmd
 */
@UtilityClass
public final class ProtocolInfo {

    public static final BedrockCodec PACKET_CODEC = Bedrock_v729.CODEC;

    public static final SemVersion MINECRAFT_VERSION = new SemVersion(1, 21, 30, 0, 0);

    /**
     * Bedrock version of the most recent backwards-incompatible change to block states.
     * <p>
     * This is not the same as current game version. It should match the numbers in the
     * newest block state upgrade schema used in BedrockBlockUpgradeSchema.
     */
    public static final SemVersion BLOCK_STATE_VERSION = new SemVersion(1, 21, 30, 7, 0);

    public static final int BLOCK_STATE_VERSION_NUM_NO_REVISION = (BLOCK_STATE_VERSION.major() << 24) |
                                                                  (BLOCK_STATE_VERSION.minor() << 16) |
                                                                  (BLOCK_STATE_VERSION.patch() << 8);

    public static final int BLOCK_STATE_VERSION_NUM = BLOCK_STATE_VERSION_NUM_NO_REVISION | BLOCK_STATE_VERSION.revision(); // revision

    public static String getMinecraftVersionStr() {
        return MINECRAFT_VERSION.major() + "." + MINECRAFT_VERSION.minor() + "." + MINECRAFT_VERSION.patch();
    }
}
