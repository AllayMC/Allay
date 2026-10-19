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

class SubChunkRequestPacketProcessorTest {

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

    @Test
    void heightMapWithoutDataCanAlsoBeReleased() {
        var subChunkData = new SubChunkData();
        SubChunkRequestPacketProcessor.setHeightMaps(subChunkData, HeightMapDataType.NO_DATA, null);

        assertNull(subChunkData.getHeightMapData());
        assertNull(subChunkData.getRenderHeightMapData());
        assertDoesNotThrow(() -> assertTrue(subChunkData.release()));
    }

    @ParameterizedTest
    @MethodSource("heightMapEncodingCases")
    void heightMapsRoundTripThroughProtocolCodecs(BedrockCodec codec, boolean cacheEnabled, HeightMapDataType type) {
        byte[] heights = null;
        if (type == HeightMapDataType.HAS_DATA) {
            heights = new byte[256];
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
                assertEquals(type, decodedSubChunk.getRenderHeightMapType());
                if (heights != null) {
                    assertArrayEquals(heights, ByteBufUtil.getBytes(decodedSubChunk.getRenderHeightMapData()));
                    assertEquals(0, subChunk.getRenderHeightMapData().readerIndex());
                }
            }
        } finally {
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

    @Test
    void emptyCacheBlobIdUsesTheVersionSpecificRepresentation() {
        assertEquals(0L, SubChunkRequestPacketProcessor.getEmptyBlobId(Bedrock_v975.CODEC));
        assertNull(SubChunkRequestPacketProcessor.getEmptyBlobId(Bedrock_v2168_hotfix4.CODEC));
    }
}
