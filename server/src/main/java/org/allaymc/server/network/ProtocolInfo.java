package org.allaymc.server.network;

import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.SemVersion;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolRegistry;
import org.allaymc.updater.block.BlockStateUpdater;
import org.allaymc.updater.block.BlockStateUpdater_1_26_50;
import org.allaymc.updater.item.ItemStateUpdater;
import org.allaymc.updater.item.ItemStateUpdater_1_26_50;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v818.Bedrock_v818;
import org.cloudburstmc.protocol.bedrock.codec.v2193.Bedrock_v2193;

/**
 * {@code ProtocolInfo} exposes the feature baseline, state-format versions, and
 * supported international codec bounds used by server networking. Codec bounds
 * come from the installed protocol registry, with built-in values available
 * during bootstrap. State updater selections remain independent of whichever
 * client codec is negotiated.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class ProtocolInfo {

    /**
     * {@code FEATURE_VERSION} supplies the game version used as the server's
     * vanilla feature baseline, independently of supported client codec bounds.
     */
    public static final BedrockCodec FEATURE_VERSION = Bedrock_v2193.CODEC;

    /**
     * {@code BLOCK_STATE_VERSION} identifies the block-state format written by
     * server encoders. The value follows block-state compatibility changes rather
     * than the latest supported client's game version.
     */
    public static final SemVersion BLOCK_STATE_VERSION = new SemVersion(1, 21, 110, 26, 0);

    /**
     * {@code BLOCK_STATE_UPDATER} converts stored block-state data through the
     * selected updater's supported migrations before the server consumes it.
     */
    public static final BlockStateUpdater BLOCK_STATE_UPDATER = BlockStateUpdater_1_26_50.INSTANCE;

    /**
     * {@code ITEM_STATE_UPDATER} converts stored item data through the selected
     * updater's supported migrations before server item deserialization consumes it.
     */
    public static final ItemStateUpdater ITEM_STATE_UPDATER = ItemStateUpdater_1_26_50.INSTANCE;

    /**
     * {@code BLOCK_STATE_VERSION_NUM} packs {@code BLOCK_STATE_VERSION} into the
     * protocol integer, with major, minor, patch, and revision occupying successive
     * bytes from most to least significant.
     */
    public static final int BLOCK_STATE_VERSION_NUM = (BLOCK_STATE_VERSION.major() << 24) |
                                                      (BLOCK_STATE_VERSION.minor() << 16) |
                                                      (BLOCK_STATE_VERSION.patch() << 8) | BLOCK_STATE_VERSION.revision();

    /**
     * Returns the latest international codec from the installed registry.
     * Before default registry installation, the built-in {@code Bedrock_v2193}
     * codec supplies a bootstrap result for advertisement and version queries.
     * The installed registry determines all subsequent results.
     *
     * @throws NullPointerException if the installed registry has no international protocols
     */
    public static BedrockCodec getLatestCodec() {
        if (ProtocolRegistry.hasDefault()) {
            return ProtocolRegistry.getDefault().getLatest(ClientVariant.INTERNATIONAL).getCodec();
        }
        // Advertisements can request a version before registry installation;
        // retain a bootstrap codec until registered international bounds are available.
        return Bedrock_v2193.CODEC;
    }

    /**
     * Returns the oldest international codec from the installed registry.
     * Before default registry installation, the built-in {@code Bedrock_v818}
     * codec supplies the bootstrap lower bound; installing a registry replaces
     * that fallback with its registered international minimum.
     *
     * @throws NullPointerException if the installed registry has no international protocols
     */
    public static BedrockCodec getLowestCodec() {
        if (ProtocolRegistry.hasDefault()) {
            return ProtocolRegistry.getDefault().getLowest(ClientVariant.INTERNATIONAL).getCodec();
        }
        // Early version queries have no installed registry to inspect; expose
        // the built-in lower bound until registration supplies the negotiated range.
        return Bedrock_v818.CODEC;
    }

    /**
     * Returns the semantic game version of the fixed vanilla feature baseline.
     */
    public static SemVersion getFeatureMinecraftVersion() {
        return SemVersion.from(FEATURE_VERSION.getMinecraftVersion());
    }

    /**
     * Returns the semantic game version of the latest international codec.
     * The result follows the installed registry, or the bootstrap fallback from
     * {@link #getLatestCodec()} when no default registry is installed.
     */
    public static SemVersion getLatestMinecraftVersion() {
        return SemVersion.from(getLatestCodec().getMinecraftVersion());
    }

    /**
     * Returns the semantic game version of the oldest international codec.
     * The result follows the installed registry, or the bootstrap fallback from
     * {@link #getLowestCodec()} when no default registry is installed.
     */
    public static SemVersion getLowestMinecraftVersion() {
        return SemVersion.from(getLowestCodec().getMinecraftVersion());
    }
}
