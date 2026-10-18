package org.allaymc.server.network.protocol.v2169;

import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168_hotfix4;
import org.cloudburstmc.protocol.bedrock.codec.v2169.Bedrock_v2169;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;
import org.cloudburstmc.protocol.bedrock.packet.SetScorePacket;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SetScoreCodecRevisionTest {

    @Test
    void v2169RevertsTheDoubleOptionalObjectiveOfThe12644Hotfix() {
        // 1.26.44 wrapped the removed objective name in a second presence flag; 1.26.45 (v2169)
        // went back to a single optional, so it must match the v2168 base serializer.
        var base = encodeRemoval(Bedrock_v2168.CODEC);
        var hotfix = encodeRemoval(Bedrock_v2168_hotfix4.CODEC);
        var v2169 = encodeRemoval(Bedrock_v2169.CODEC);

        assertArrayEquals(base, v2169);
        assertEquals(base.length + 1, hotfix.length);
    }

    private static byte[] encodeRemoval(BedrockCodec codec) {
        var packet = new SetScorePacket();
        // An INVALID scorer type marks a removal since v2168.
        packet.setInfos(List.of(new ScoreInfo(1L, "objective", 0)));

        var buffer = Unpooled.buffer();
        try {
            codec.tryEncode(codec.createHelper(), buffer, packet);
            return ByteBufUtil.getBytes(buffer);
        } finally {
            buffer.release();
        }
    }
}
