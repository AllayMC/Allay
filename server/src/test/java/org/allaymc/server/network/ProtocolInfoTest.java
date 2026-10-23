package org.allaymc.server.network;

import org.allaymc.api.utils.SemVersion;
import org.allaymc.updater.block.BlockStateUpdater_1_26_50;
import org.allaymc.updater.item.ItemStateUpdater_1_26_50;
import org.cloudburstmc.protocol.bedrock.codec.v2193.Bedrock_v2193;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * {@code ProtocolInfoTest} checks feature and client codec identities alongside
 * the state updaters selected for persisted vanilla data.
 */
class ProtocolInfoTest {

    /**
     * Reads the feature baseline independently of the negotiated client registry.
     * Its codec, serialized block-state version, and state updaters must describe
     * the imported 1.26.50 data set so older storage versions trigger migration.
     */
    @Test
    void shouldUseThe12650FeatureBaselineAndUpdaters() {
        assertSame(Bedrock_v2193.CODEC, ProtocolInfo.FEATURE_VERSION);
        assertEquals(SemVersion.from("1.26.50"), ProtocolInfo.getFeatureMinecraftVersion());
        assertEquals(new SemVersion(1, 26, 50, 0, 0), ProtocolInfo.BLOCK_STATE_VERSION);
        assertEquals(BlockStateUpdater_1_26_50.INSTANCE.getVersion(), ProtocolInfo.BLOCK_STATE_VERSION_NUM);
        assertSame(BlockStateUpdater_1_26_50.INSTANCE, ProtocolInfo.BLOCK_STATE_UPDATER);
        assertSame(ItemStateUpdater_1_26_50.INSTANCE, ProtocolInfo.ITEM_STATE_UPDATER);
    }

    /**
     * Queries the latest international codec and compares its advertised version.
     * The result must identify {@code Bedrock_v2193} with protocol {@code 2193}
     * and game version {@code "1.26.50"}.
     */
    @Test
    void shouldUseTheV2193CodecAsTheLatestCodec() {
        var codec = Bedrock_v2193.CODEC;

        assertSame(codec, ProtocolInfo.getLatestCodec());
        assertEquals(2193, codec.getProtocolVersion());
        assertEquals("1.26.50", codec.getMinecraftVersion());
    }
}
