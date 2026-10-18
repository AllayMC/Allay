package org.allaymc.server.network;

import org.allaymc.server.network.protocol.v2169.Protocol_v2169;
import org.cloudburstmc.protocol.bedrock.codec.v2169.Bedrock_v2169;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ProtocolInfoTest {

    @Test
    void shouldUseTheV2169CodecAsTheLatestCodec() {
        var codec = Bedrock_v2169.CODEC;

        assertSame(codec, ProtocolInfo.getLatestCodec());
        assertEquals(2169, codec.getProtocolVersion());
        assertEquals("1.26.45", codec.getMinecraftVersion());
    }

    @Test
    void v2169ProtocolUsesTheV2169Codec() {
        assertSame(Bedrock_v2169.CODEC, new Protocol_v2169().getCodec());
    }
}
