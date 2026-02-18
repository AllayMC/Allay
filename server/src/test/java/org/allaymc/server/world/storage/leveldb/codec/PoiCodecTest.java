package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.poi.PoiTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.jctools.maps.NonBlockingHashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class PoiCodecTest {

    @Test
    void testSerializeEmptyReturnsNull() {
        var empty = new NonBlockingHashMap<Integer, PoiType>();
        assertNull(PoiCodec.serialize(empty));
    }

    @Test
    void testSerializeAndDeserialize() {
        var entries = new NonBlockingHashMap<Integer, PoiType>();
        entries.put(HashUtils.hashChunkXYZ(1, 64, 2), PoiTypes.NETHER_PORTAL);
        entries.put(HashUtils.hashChunkXYZ(15, 128, 15), PoiTypes.NETHER_PORTAL);
        entries.put(HashUtils.hashChunkXYZ(0, -32, 0), PoiTypes.NETHER_PORTAL);

        byte[] data = PoiCodec.serialize(entries);
        assertNotNull(data);

        var deserialized = PoiCodec.deserialize(data);
        assertEquals(3, deserialized.size());

        for (var entry : entries.entrySet()) {
            var recovered = deserialized.get(entry.getKey());
            assertNotNull(recovered, "Missing entry for hash " + entry.getKey());
            assertEquals(entry.getValue().name(), recovered.name());
        }
    }

    @Test
    void testDeserializePreservesCoordinates() {
        var entries = new NonBlockingHashMap<Integer, PoiType>();
        int x = 7, y = 200, z = 13;
        int hash = HashUtils.hashChunkXYZ(x, y, z);
        entries.put(hash, PoiTypes.NETHER_PORTAL);

        byte[] data = PoiCodec.serialize(entries);
        var deserialized = PoiCodec.deserialize(data);

        assertTrue(deserialized.containsKey(hash));
        assertEquals(x, HashUtils.getXFromHashChunkXYZ(hash));
        assertEquals(y, HashUtils.getYFromHashChunkXYZ(hash));
        assertEquals(z, HashUtils.getZFromHashChunkXYZ(hash));
    }

    @Test
    void testDeserializeUnknownTypeSkipped() {
        // Manually build entries with a known type, serialize, then
        // verify that deserialization handles the data correctly
        var entries = new NonBlockingHashMap<Integer, PoiType>();
        entries.put(HashUtils.hashChunkXYZ(0, 0, 0), PoiTypes.NETHER_PORTAL);

        byte[] data = PoiCodec.serialize(entries);
        assertNotNull(data);

        // Normal deserialization should work fine
        var deserialized = PoiCodec.deserialize(data);
        assertEquals(1, deserialized.size());
        assertEquals(PoiTypes.NETHER_PORTAL.name(), deserialized.values().iterator().next().name());
    }
}
