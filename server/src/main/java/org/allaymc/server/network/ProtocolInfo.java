package org.allaymc.server.network;

import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.SemVersion;
import org.allaymc.updater.block.BlockStateUpdater;
import org.allaymc.updater.block.BlockStateUpdater_1_21_110;
import org.allaymc.updater.item.ItemStateUpdater;
import org.allaymc.updater.item.ItemStateUpdater_1_21_110;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;
import org.cloudburstmc.protocol.bedrock.codec.v818.Bedrock_v818;
import org.cloudburstmc.protocol.bedrock.codec.v819.Bedrock_v819;
import org.cloudburstmc.protocol.bedrock.codec.v827.Bedrock_v827;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;
import org.cloudburstmc.protocol.bedrock.codec.v859.Bedrock_v859;
import org.cloudburstmc.protocol.bedrock.codec.v860.Bedrock_v860;
import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898;

import java.util.List;

/**
 * This class contains information about the current protocol version.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class ProtocolInfo {

    /**
     * A list which contains the supported protocol versions, and the first element is the latest version.
     */
    public static final List<BedrockCodec> SUPPORTED_VERSIONS = List.of(
            // Order is important. The first codec is the latest supported version.
            Bedrock_v898.CODEC,
            Bedrock_v860.CODEC,
            Bedrock_v859.CODEC,
            Bedrock_v844.CODEC,
            Bedrock_v827.CODEC,
            Bedrock_v819.CODEC,
            Bedrock_v818.CODEC,
            Bedrock_v800.CODEC
    );

    /**
     * Feature version is the version of the game from which vanilla features will be used.
     */
    public static final BedrockCodec FEATURE_VERSION = Bedrock_v898.CODEC;

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
    public static final ItemStateUpdater ITEM_STATE_UPDATER = ItemStateUpdater_1_21_110.INSTANCE;

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
        return SUPPORTED_VERSIONS.getFirst();
    }

    /**
     * Get the lowest codec.
     *
     * @return the lowest codec
     */
    public static BedrockCodec getLowestCodec() {
        return SUPPORTED_VERSIONS.getLast();
    }

    /**
     * Find the codec by protocol version.
     *
     * @param protocolVersion the protocol version
     * @return the codec, or {@code null} if not found
     */
    public static BedrockCodec findCodec(int protocolVersion) {
        for (var codec : SUPPORTED_VERSIONS) {
            if (codec.getProtocolVersion() == protocolVersion) {
                return codec;
            }
        }

        return null;
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
