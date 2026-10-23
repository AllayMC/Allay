package org.allaymc.server.world.storage.leveldb.codec;

import io.netty.buffer.ByteBufOutputStream;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.poi.PoiType;
import org.allaymc.api.world.storage.WorldStorageException;
import org.allaymc.server.world.storage.leveldb.LevelDBUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.jctools.maps.NonBlockingHashMap;

import java.io.IOException;
import java.util.Map;

/**
 * {@code PoiCodec} stores
 * chunk points of interest as coordinates and registered type names in
 * little-endian NBT. Decoding resolves names against the active POI registry;
 * unknown names are omitted so remaining known entries can still be loaded.
 *
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public final class PoiCodec {

    /**
     * {@code TAG_POI_LIST} names the compound list containing the saved point-of-interest
     * coordinates and registered type names.
     */
    private static final String TAG_POI_LIST = "poiList";
    /**
     * {@code TAG_X} names
     * the saved local X coordinate used to reconstruct the chunk position
     * hash.
     */
    private static final String TAG_X = "x";
    /**
     * {@code TAG_Y} names
     * the saved world Y coordinate used to reconstruct the chunk position
     * hash.
     */
    private static final String TAG_Y = "y";
    /**
     * {@code TAG_Z} names
     * the saved local Z coordinate used to reconstruct the chunk position
     * hash.
     */
    private static final String TAG_Z = "z";
    /**
     * {@code TAG_TYPE} names
     * the registered POI type string used for lookup when records are
     * loaded.
     */
    private static final String TAG_TYPE = "type";

    /**
     * Serializes position hashes and POI type names into a little-endian NBT
     * record. An empty map returns {@code null} so the storage caller removes any
     * previous record rather than retaining stale entries.
     */
    public static byte[] serialize(Map<Integer, PoiType> poiEntries) {
        if (poiEntries.isEmpty()) {
            return null;
        }

        return LevelDBUtils.withByteBufToArray(buffer -> {
            try (var writerLE = NbtUtils.createWriterLE(new ByteBufOutputStream(buffer))) {
                var list = poiEntries.entrySet().stream().map(entry -> {
                    int hash = entry.getKey();
                    return NbtMap.builder()
                            .putInt(TAG_X, HashUtils.getXFromHashChunkXYZ(hash))
                            .putInt(TAG_Y, HashUtils.getYFromHashChunkXYZ(hash))
                            .putInt(TAG_Z, HashUtils.getZFromHashChunkXYZ(hash))
                            .putString(TAG_TYPE, entry.getValue().name())
                            .build();
                }).toList();
                var nbt = NbtMap.builder()
                        .putList(TAG_POI_LIST, NbtType.COMPOUND, list)
                        .build();
                writerLE.writeTag(nbt);
            } catch (IOException e) {
                throw new WorldStorageException(e);
            }
        });
    }

    /**
     * Restores chunk-position hashes and resolves saved POI names against the
     * active registry. Unknown names are logged and skipped; later entries with
     * the same reconstructed position replace earlier entries.
     */
    public static NonBlockingHashMap<Integer, PoiType> deserialize(byte[] data) {
        var nbt = AllayNBTUtils.bytesToNbtLE(data);
        var poiList = nbt.getList(TAG_POI_LIST, NbtType.COMPOUND);
        var poiEntries = new NonBlockingHashMap<Integer, PoiType>(poiList.size());
        for (var entry : poiList) {
            int x = entry.getInt(TAG_X);
            int y = entry.getInt(TAG_Y);
            int z = entry.getInt(TAG_Z);
            String typeName = entry.getString(TAG_TYPE);
            var poiType = findPoiTypeByName(typeName);
            if (poiType != null) {
                poiEntries.put(HashUtils.hashChunkXYZ(x, y, z), poiType);
            } else {
                // An unregistered name cannot supply a usable POI type; skip
                // that entry while retaining other recognized positions from
                // this chunk record.
                log.warn("Unknown POI type '{}' at ({}, {}, {}), skipping", typeName, x, y, z);
            }
        }
        return poiEntries;
    }

    /**
     * Finds the first registered POI type whose name exactly matches the
     * supplied string. Returns {@code null} when no registered type matches; matching
     * uses the type's name rather than its registry key.
     */
    private static PoiType findPoiTypeByName(String name) {
        for (var poiType : Registries.POI_TYPES.getContent().values()) {
            if (poiType.name().equals(name)) {
                return poiType;
            }
        }
        return null;
    }
}
