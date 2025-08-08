package org.allaymc.api.network;

import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.SemVersion;
import org.allaymc.updater.block.BlockStateUpdater;
import org.allaymc.updater.block.BlockStateUpdater_1_21_60;
import org.allaymc.updater.item.ItemStateUpdater;
import org.allaymc.updater.item.ItemStateUpdater_1_21_50;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;

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
    public static final BedrockCodec PACKET_CODEC = Bedrock_v827.CODEC;

    /**
     * The current minecraft version.
     */
    public static final SemVersion MINECRAFT_VERSION = new SemVersion(1, 21, 100, 6, 0);

    /**
     * Bedrock version of the most recent backwards-incompatible change to block states.
     * <p>
     * This is not the same as current game version. It should match the nearest version
     * that has block state changes.
     */
    public static final SemVersion BLOCK_STATE_VERSION = new SemVersion(1, 21, 60, 33, 0);

    /**
     * The currently used block state updater instance.
     */
    public static final BlockStateUpdater BLOCK_STATE_UPDATER = BlockStateUpdater_1_21_60.INSTANCE;

    /**
     * The currently used item state updater instance.
     */
    public static final ItemStateUpdater ITEM_STATE_UPDATER = ItemStateUpdater_1_21_50.INSTANCE;

    /**
     * The encoded version number of the block state version (without revision).
     */
    public static final int BLOCK_STATE_VERSION_NUM_NO_REVISION = (BLOCK_STATE_VERSION.major() << 24) |
                                                                  (BLOCK_STATE_VERSION.minor() << 16) |
                                                                  (BLOCK_STATE_VERSION.patch() << 8);

    /**
     * The encoded version number of the block state version.
     */
    public static final int BLOCK_STATE_VERSION_NUM = BLOCK_STATE_VERSION_NUM_NO_REVISION | BLOCK_STATE_VERSION.revision(); // 18168865

    /**
     * Get the string style of the current minecraft version.
     *
     * @return the string style of the current minecraft version.
     */
    public static String getMinecraftVersionStr() {
        return MINECRAFT_VERSION.major() + "." + MINECRAFT_VERSION.minor() + "." + MINECRAFT_VERSION.patch();
    }
}
