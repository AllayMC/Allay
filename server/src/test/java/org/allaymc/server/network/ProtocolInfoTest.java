package org.allaymc.server.network;

import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168_hotfix4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ProtocolInfoTest {

    @Test
    void shouldUseTheV2168HotfixCodecAsTheLatestCodec() {
        var codec = Bedrock_v2168_hotfix4.CODEC;

        assertSame(codec, ProtocolInfo.getLatestCodec());
        assertEquals(2168, codec.getProtocolVersion());
        assertEquals("1.26.44", codec.getMinecraftVersion());
    }
}
