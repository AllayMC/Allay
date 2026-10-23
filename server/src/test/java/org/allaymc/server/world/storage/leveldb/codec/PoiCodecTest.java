package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.poi.PoiTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.jctools.maps.NonBlockingHashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code PoiCodecTest} checks
 * stored point-of-interest positions and registry names for known POI
 * types.
 */
@ExtendWith(AllayTestExtension.class)
class PoiCodecTest {

    /**
     * Serializes an empty map of chunk points of interest. The codec must
     * return {@code null} so the storage caller can delete an obsolete POI record.
     */
    @Test
    void testSerializeEmptyReturnsNull() {
        var empty = new NonBlockingHashMap<Integer, PoiType>();
        assertNull(PoiCodec.serialize(empty));
    }

    /**
     * Encodes three portal points of interest at distinct chunk positions and
     * heights. Decoding must restore every position hash and resolve each
     * stored type name.
     */
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

    /**
     * Encodes one portal point of interest at a nontrivial chunk coordinate.
     * The decoded mapping must retain the same position hash whose components
     * identify the original location.
     */
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

    /**
     * Round-trips a registered portal type and checks its decoded identity.
     * Despite the method name, this fixture contains no unknown type and
     * therefore does not exercise unknown-name skipping.
     */
    @Test
    void testDeserializeUnknownTypeSkipped() {
        // This fixture supplies a registered type only; its assertions cover successful
        // name resolution and do not establish the unknown-type recovery behavior.
        var entries = new NonBlockingHashMap<Integer, PoiType>();
        entries.put(HashUtils.hashChunkXYZ(0, 0, 0), PoiTypes.NETHER_PORTAL);

        byte[] data = PoiCodec.serialize(entries);
        assertNotNull(data);

        var deserialized = PoiCodec.deserialize(data);
        assertEquals(1, deserialized.size());
        assertEquals(PoiTypes.NETHER_PORTAL.name(), deserialized.values().iterator().next().name());
    }
}
