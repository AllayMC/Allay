package org.allaymc.server.network;

import org.cloudburstmc.protocol.bedrock.codec.v2193.Bedrock_v2193;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ProtocolInfoTest {

    @Test
    void shouldUseTheV2193CodecAsTheLatestCodec() {
        var codec = Bedrock_v2193.CODEC;

        assertSame(codec, ProtocolInfo.getLatestCodec());
        assertEquals(2193, codec.getProtocolVersion());
        assertEquals("1.26.50", codec.getMinecraftVersion());
    }
}
