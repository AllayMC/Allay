package org.allaymc.server.player;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChunkCacheTest {

    @Test
    void missResponseMutationDoesNotPolluteCachedBlob() throws ReflectiveOperationException {
        var constructor = ChunkCache.class.getDeclaredConstructor(int.class);
        constructor.setAccessible(true);
        var cache = constructor.newInstance(16);
        var encoder = new PacketEncoder_v766(mock(ProtocolData.class));
        var playerId = UUID.randomUUID();
        byte[] cachedBytes = {1, 2, 3, 4};
        long hash = cache.tryStoreBlobsAndOpenTransaction(playerId, 0, cachedBytes)[0];

        var first = cache.handleBlobStatus(encoder, playerId, new long[0], new long[]{hash});
        assertNotNull(first);
        try {
            first.getBlobs().get(hash).setByte(0, 99);
            assertArrayEquals(new byte[]{1, 2, 3, 4}, cachedBytes);
        } finally {
            assertTrue(first.release());
        }

        var second = cache.handleBlobStatus(encoder, playerId, new long[0], new long[]{hash});
        assertNotNull(second);
        try {
            byte[] responseBytes = new byte[second.getBlobs().get(hash).readableBytes()];
            second.getBlobs().get(hash).getBytes(second.getBlobs().get(hash).readerIndex(), responseBytes);
            assertArrayEquals(new byte[]{1, 2, 3, 4}, responseBytes);
        } finally {
            assertTrue(second.release());
        }
    }
}
