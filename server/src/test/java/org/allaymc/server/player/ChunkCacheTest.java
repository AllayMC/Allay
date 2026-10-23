package org.allaymc.server.player;

import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * {@code ChunkCacheTest} verifies that independently encoded cache-miss responses do
 * not share mutable bytes with stored blobs.
 */
class ChunkCacheTest {

    /**
     * Creates two cache-miss responses for the same registered blob and mutates the
     * first response buffer. Cached source bytes and the second response must retain
     * their original contents, and both packets must be released.
     */
    @Test
    void missResponseMutationDoesNotPolluteCachedBlob() throws ReflectiveOperationException {
        // Construct a private cache instance for this test so its blob mutations and transaction state
        // do not depend on the process-wide singleton.
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
            // Release the first response even when a mutation assertion fails, because the encoded packet
            // owns reference-counted buffers independent of the cache.
            assertTrue(first.release());
        }

        var second = cache.handleBlobStatus(encoder, playerId, new long[0], new long[]{hash});
        assertNotNull(second);
        try {
            byte[] responseBytes = new byte[second.getBlobs().get(hash).readableBytes()];
            second.getBlobs().get(hash).getBytes(second.getBlobs().get(hash).readerIndex(), responseBytes);
            assertArrayEquals(new byte[]{1, 2, 3, 4}, responseBytes);
        } finally {
            // Release the second response after checking its copied bytes so the independently allocated
            // packet buffers are not retained by this test.
            assertTrue(second.release());
        }
    }
}
