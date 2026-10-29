package org.allaymc.server.network.protocol;

import org.allaymc.api.pack.Pack;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourcePackEncoderTest {

    @Test
    void resourcePackPacketsOwnTheirMutablePayloads() {
        var id = UUID.fromString("00000000-0000-0000-0000-000000000020");
        var content = new byte[]{10, 20, 30, 40, 50};
        var hash = new byte[]{1, 2, 3, 4};
        var pack = mock(Pack.class);
        when(pack.getId()).thenReturn(id);
        when(pack.getStringVersion()).thenReturn("1.0.0");
        when(pack.getSize()).thenReturn(content.length);
        when(pack.getType()).thenReturn(Pack.Type.RESOURCES);
        when(pack.getHash()).thenReturn(hash);
        when(pack.getChunk(anyInt(), anyInt())).thenAnswer(invocation -> {
            int offset = invocation.getArgument(0);
            int length = invocation.getArgument(1);
            return Arrays.copyOfRange(content, offset, Math.min(offset + length, content.length));
        });

        var encoder = new PacketEncoder_v766(mock(ProtocolData.class));

        var firstInfo = encoder.encodeResourcePackDataInfo(pack, 3);
        firstInfo.getHash()[0] = 99;
        var secondInfo = encoder.encodeResourcePackDataInfo(pack, 3);
        assertArrayEquals(hash, secondInfo.getHash());
        assertEquals(2, secondInfo.getChunkCount());

        var firstChunk = encoder.encodeResourcePackChunkData(pack, 0, 3);
        var secondChunk = encoder.encodeResourcePackChunkData(pack, 0, 3);
        var finalChunk = encoder.encodeResourcePackChunkData(pack, 1, 3);
        try {
            assertNotSame(firstChunk.getData(), secondChunk.getData());
            firstChunk.getData().setByte(0, 99);
            assertEquals(10, secondChunk.getData().getByte(0));
            assertEquals(0, firstChunk.getProgress());
            assertEquals(2, finalChunk.getData().readableBytes());
            assertEquals(3, finalChunk.getProgress());
            assertEquals(40, finalChunk.getData().getByte(0));
            assertThrows(
                    IllegalArgumentException.class,
                    () -> encoder.encodeResourcePackChunkData(pack, 2, 3)
            );
        } finally {
            firstChunk.getData().release();
            secondChunk.getData().release();
            finalChunk.getData().release();
        }
    }
}
