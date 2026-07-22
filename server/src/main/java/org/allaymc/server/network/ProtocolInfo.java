package org.allaymc.server.network;

import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.SemVersion;
import org.allaymc.updater.block.BlockStateUpdater;
import org.allaymc.updater.block.BlockStateUpdater_1_21_110;
import org.allaymc.updater.item.ItemStateUpdater;
import org.allaymc.updater.item.ItemStateUpdater_1_26_20;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.Bedrock_v1001;
import org.cloudburstmc.protocol.bedrock.codec.v818.Bedrock_v818;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolRegistry;

/**
 * This class contains information about the current protocol version.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class ProtocolInfo {

    /**
     * Feature version is the version of the game from which vanilla features will be used.
     */
    public static final BedrockCodec FEATURE_VERSION = Bedrock_v1001.CODEC;

    /**
     * Bedrock version of the most recent backwards-incompatible change to block states.
     * <p>
     * This is different from the current game version. It should match the nearest version
     * that has block state changes.
     */
    public static final SemVersion BLOCK_STATE_VERSION = new SemVersion(1, 21, 110, 26, 0);

    /**
     * The currently used block state updater instance.
     */
    public static final BlockStateUpdater BLOCK_STATE_UPDATER = BlockStateUpdater_1_21_110.INSTANCE;

    /**
     * The currently used item state updater instance.
     */
    public static final ItemStateUpdater ITEM_STATE_UPDATER = ItemStateUpdater_1_26_20.INSTANCE;

    /**
     * The encoded version number of the block state version.
     */
    public static final int BLOCK_STATE_VERSION_NUM = (BLOCK_STATE_VERSION.major() << 24) |
                                                      (BLOCK_STATE_VERSION.minor() << 16) |
                                                      (BLOCK_STATE_VERSION.patch() << 8) | BLOCK_STATE_VERSION.revision();

    /**
     * Get the latest codec.
     *
     * @return the latest codec
     */
    public static BedrockCodec getLatestCodec() {
        if (ProtocolRegistry.hasDefault()) {
            return ProtocolRegistry.getDefault().getLatest(ClientVariant.INTERNATIONAL).getCodec();
        }
        return Bedrock_v1001.CODEC;
    }

    /**
     * Get the lowest codec.
     *
     * @return the lowest codec
     */
    public static BedrockCodec getLowestCodec() {
        if (ProtocolRegistry.hasDefault()) {
            return ProtocolRegistry.getDefault().getLowest(ClientVariant.INTERNATIONAL).getCodec();
        }
        return Bedrock_v818.CODEC;
    }

    /**
     * Get the feature minecraft version.
     *
     * @return the feature minecraft version
     */
    public static SemVersion getFeatureMinecraftVersion() {
        return SemVersion.from(FEATURE_VERSION.getMinecraftVersion());
    }

    /**
     * Get the latest minecraft version.
     *
     * @return the latest minecraft version
     */
    public static SemVersion getLatestMinecraftVersion() {
        return SemVersion.from(getLatestCodec().getMinecraftVersion());
    }

    /**
     * Get the lowest minecraft version.
     *
     * @return the lowest minecraft version
     */
    public static SemVersion getLowestMinecraftVersion() {
        return SemVersion.from(getLowestCodec().getMinecraftVersion());
    }
}
