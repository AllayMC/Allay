package org.allaymc.api.network;

import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.SemVersion;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v776.Bedrock_v776;

/**
 * This class contains information about the current protocol version.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class ProtocolInfo {

    /**
     * The current used packet codec.
     */
    public static final BedrockCodec PACKET_CODEC = Bedrock_v776.CODEC;

    /**
     * The current minecraft version.
     */
    public static final SemVersion MINECRAFT_VERSION = new SemVersion(1, 21, 60, 0, 0);

    /**
     * Bedrock version of the most recent backwards-incompatible change to block states.
     * <p>
     * This is not the same as current game version. It should match the nearest version
     * that has block state changes.
     */
    public static final SemVersion BLOCK_STATE_VERSION = new SemVersion(1, 21, 60, 33, 0);

    /**
     * The encoded version number of the block state version (without revision).
     */
    public static final int BLOCK_STATE_VERSION_NUM_NO_REVISION = (BLOCK_STATE_VERSION.major() << 24) |
                                                                  (BLOCK_STATE_VERSION.minor() << 16) |
                                                                  (BLOCK_STATE_VERSION.patch() << 8);

    /**
     * The encoded version number of the block state version.
     */
    public static final int BLOCK_STATE_VERSION_NUM = BLOCK_STATE_VERSION_NUM_NO_REVISION | BLOCK_STATE_VERSION.revision(); // revision

    /**
     * Get the string style of the current minecraft version.
     *
     * @return the string style of the current minecraft version.
     */
    public static String getMinecraftVersionStr() {
        return MINECRAFT_VERSION.major() + "." + MINECRAFT_VERSION.minor() + "." + MINECRAFT_VERSION.patch();
    }
}
