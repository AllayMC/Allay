package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.server.world.chunk.ScheduledUpdateInfo;
import org.allaymc.testutils.AllayTestExtension;
import org.joml.Vector3i;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ScheduledUpdateCodecTest} checks
 * pending-update deletion markers and preservation of stored positions
 * and times.
 */
@ExtendWith(AllayTestExtension.class)
class ScheduledUpdateCodecTest {

    /**
     * Serializes an empty update collection with a nonzero current world tick.
     * The codec must return {@code null} instead of retaining a record that contains
     * only tick metadata.
     */
    @Test
    void testSerializeEmptyReturnsNull() {
        assertNull(ScheduledUpdateCodec.serialize(List.of(), 100L));
    }

    /**
     * Encodes three pending updates at distinct heights with different
     * execution ticks. Decoding must preserve each world position and its
     * absolute execution time under the expected position hash.
     */
    @Test
    void testSerializeAndDeserialize() {
        var info1 = new ScheduledUpdateInfo(new Vector3i(1, 64, 2), 100L);
        var info2 = new ScheduledUpdateInfo(new Vector3i(5, 128, 10), 200L);
        var info3 = new ScheduledUpdateInfo(new Vector3i(15, -32, 15), 300L);
        var updates = List.of(info1, info2, info3);

        byte[] data = ScheduledUpdateCodec.serialize(updates, 50L);
        assertNotNull(data);

        var deserialized = ScheduledUpdateCodec.deserialize(data);
        assertEquals(3, deserialized.size());

        for (var original : updates) {
            var pos = original.getPos();
            int key = HashUtils.hashChunkXYZ(pos.x() & 15, pos.y(), pos.z() & 15);
            var recovered = deserialized.get(key);
            assertNotNull(recovered, "Missing entry for position " + pos);
            assertEquals(pos.x(), recovered.getPos().x());
            assertEquals(pos.y(), recovered.getPos().y());
            assertEquals(pos.z(), recovered.getPos().z());
            assertEquals(original.getTime(), recovered.getTime());
        }
    }
}
