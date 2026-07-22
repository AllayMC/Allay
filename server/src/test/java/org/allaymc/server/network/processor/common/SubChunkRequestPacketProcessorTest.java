package org.allaymc.server.network.processor.common;

import org.cloudburstmc.protocol.bedrock.data.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.SubChunkData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

        assertDoesNotThrow(() -> assertTrue(subChunkData.release()));
    }
}
