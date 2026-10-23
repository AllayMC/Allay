package org.allaymc.server.network.processor.common;

import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.Bedrock_v1001;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168_hotfix4;
import org.cloudburstmc.protocol.bedrock.codec.v2169.Bedrock_v2169;
import org.cloudburstmc.protocol.bedrock.codec.v2193.Bedrock_v2193;
import org.cloudburstmc.protocol.bedrock.codec.v766_netease.Bedrock_v766_NetEase;
import org.cloudburstmc.protocol.bedrock.codec.v818.Bedrock_v818;
import org.cloudburstmc.protocol.bedrock.codec.v975.Bedrock_v975;
import org.cloudburstmc.protocol.bedrock.data.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.SubChunkData;
import org.cloudburstmc.protocol.bedrock.data.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * {@code SubChunkRequestPacketProcessorTest} checks height-map payload ownership
 * and codec-specific sub-chunk representations across supported protocol versions.
 */
class SubChunkRequestPacketProcessorTest {

    /**
     * Assigns one height-map array to logical and render fields through retained
     * views. Releasing their owning sub-chunk must release both references
     * without a double-release failure.
     */
    @Test
    void heightMapBuffersHaveIndependentReferenceCountOwnership() {
        var subChunkData = new SubChunkData();
        SubChunkRequestPacketProcessor.setHeightMaps(
                subChunkData,
                HeightMapDataType.HAS_DATA,
                new byte[]{1, 2, 3}
        );

        var heightMap = subChunkData.getHeightMapData();
        var renderHeightMap = subChunkData.getRenderHeightMapData();
        assertNotSame(heightMap, renderHeightMap);
        assertEquals(2, heightMap.refCnt());
        assertEquals(2, renderHeightMap.refCnt());
        assertEquals(heightMap, renderHeightMap);

        assertDoesNotThrow(() -> assertTrue(subChunkData.release()));
        assertEquals(0, heightMap.refCnt());
        assertEquals(0, renderHeightMap.refCnt());
    }

    /**
     * Assigns a height-map type that carries no byte payload.
     * Both buffer fields must remain absent and the owning sub-chunk must still
     * release successfully.
     */
    @Test
    void heightMapWithoutDataCanAlsoBeReleased() {
        var subChunkData = new SubChunkData();
        SubChunkRequestPacketProcessor.setHeightMaps(subChunkData, HeightMapDataType.NO_DATA, null);

        assertNull(subChunkData.getHeightMapData());
        assertNull(subChunkData.getRenderHeightMapData());
        assertDoesNotThrow(() -> assertTrue(subChunkData.release()));
    }

    /**
     * Round-trips each supplied codec, cache mode, and height-map representation.
     * Decoding must preserve supported map bytes and identifiers without consuming
     * source readers, and all packet-owned buffers must release afterward.
     */
    @ParameterizedTest
    @MethodSource("heightMapEncodingCases")
    void heightMapsRoundTripThroughProtocolCodecs(BedrockCodec codec, boolean cacheEnabled, HeightMapDataType type) {
        byte[] heights = null;
        if (type == HeightMapDataType.HAS_DATA) {
            heights = new byte[256];
            // Cycle below-section, in-section, and above-section height values.
            for (int index = 0; index < heights.length; index++) {
                heights[index] = (byte) (index % 18 - 1);
            }
        }

        var subChunk = new SubChunkData();
        subChunk.setPosition(Vector3i.from(1, -1, 0));
        subChunk.setResult(type == HeightMapDataType.NO_DATA
                ? SubChunkRequestResult.CHUNK_NOT_FOUND : SubChunkRequestResult.SUCCESS_ALL_AIR);
        subChunk.setData(Unpooled.EMPTY_BUFFER);
        subChunk.setBlobId(SubChunkRequestPacketProcessor.getEmptyBlobId(codec));
        SubChunkRequestPacketProcessor.setHeightMaps(subChunk, type, heights);

        var encoder = new PacketEncoder_v766(mock(ProtocolData.class));
        var packet = encoder.encodeSubChunk(0, Vector3i.from(2, 4, 6), cacheEnabled, List.of(subChunk));
        var buffer = Unpooled.buffer();
        SubChunkPacket decoded = null;
        try {
            codec.tryEncode(codec.createHelper(), buffer, packet);
            decoded = assertInstanceOf(SubChunkPacket.class, codec.tryDecode(
                    codec.createHelper(), buffer, codec.getPacketDefinition(SubChunkPacket.class).id()
            ));

            assertFalse(buffer.isReadable());
            assertEquals(cacheEnabled, decoded.isCacheEnabled());
            assertEquals(packet.getCenterPosition(), decoded.getCenterPosition());
            assertEquals(1, decoded.getSubChunks().size());
            var decodedSubChunk = decoded.getSubChunks().getFirst();
            assertEquals(subChunk.getPosition(), decodedSubChunk.getPosition());
            assertEquals(subChunk.getResult(), decodedSubChunk.getResult());
            assertEquals(type, decodedSubChunk.getHeightMapType());
            if (heights == null) {
                assertNull(decodedSubChunk.getHeightMapData());
                assertNull(decodedSubChunk.getRenderHeightMapData());
            } else {
                assertArrayEquals(heights, ByteBufUtil.getBytes(decodedSubChunk.getHeightMapData()));
                assertEquals(0, subChunk.getHeightMapData().readerIndex());
            }
            if (codec.getProtocolVersion() >= 818) {
                // Render height maps enter these codec assertions from v818;
                // older formats still verify logical maps without requiring an
                // additional render payload they do not carry.
                assertEquals(type, decodedSubChunk.getRenderHeightMapType());
                if (heights != null) {
                    assertArrayEquals(heights, ByteBufUtil.getBytes(decodedSubChunk.getRenderHeightMapData()));
                    assertEquals(0, subChunk.getRenderHeightMapData().readerIndex());
                }
            }
        } finally {
            // Packets own their sub-chunk views, while the wire buffer belongs
            // to this test; release all owners even when encoding or assertions
            // fail before the post-cleanup checks.
            ReferenceCountUtil.release(decoded);
            packet.release();
            buffer.release();
        }

        assertEquals(0, subChunk.refCnt());
        assertEquals(0, buffer.refCnt());
        if (heights != null) {
            assertEquals(0, subChunk.getHeightMapData().refCnt());
            assertEquals(0, subChunk.getRenderHeightMapData().refCnt());
        }
    }

    /**
     * Returns the codec, cache-mode, and height-map-type combinations for encoding
     * checks. Every selected codec exercises both cache settings and every enum
     * representation, including types without payload arrays.
     */
    private static Stream<Arguments> heightMapEncodingCases() {
        return Stream.of(
                Bedrock_v766_NetEase.CODEC,
                Bedrock_v818.CODEC,
                Bedrock_v1001.CODEC,
                Bedrock_v2168.CODEC,
                Bedrock_v2168_hotfix4.CODEC,
                Bedrock_v2169.CODEC,
                Bedrock_v2193.CODEC
        ).flatMap(codec -> Stream.of(false, true).flatMap(cacheEnabled ->
                Arrays.stream(HeightMapDataType.values()).map(type -> Arguments.of(codec, cacheEnabled, type))));
    }

    /**
     * Compares a pre-optional-identifier codec with the {@code v2168} hotfix codec.
     * Empty cache entries must use numeric zero for the older format and
     * {@code null} for the newer optional identifier.
     */
    @Test
    void emptyCacheBlobIdUsesTheVersionSpecificRepresentation() {
        assertEquals(0L, SubChunkRequestPacketProcessor.getEmptyBlobId(Bedrock_v975.CODEC));
        assertNull(SubChunkRequestPacketProcessor.getEmptyBlobId(Bedrock_v2168_hotfix4.CODEC));
    }
}
