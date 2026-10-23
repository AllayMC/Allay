package org.allaymc.server.network;

import org.cloudburstmc.protocol.bedrock.codec.v2193.Bedrock_v2193;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * {@code ProtocolInfoTest} checks the latest codec identity and advertised version metadata.
 */
class ProtocolInfoTest {

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
