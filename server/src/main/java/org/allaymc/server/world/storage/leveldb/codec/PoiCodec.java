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
 * Codec for serializing/deserializing POI (Point of Interest) data.
 *
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public final class PoiCodec {

    private static final String TAG_POI_LIST = "poiList";
    private static final String TAG_X = "x";
    private static final String TAG_Y = "y";
    private static final String TAG_Z = "z";
    private static final String TAG_TYPE = "type";

    /**
     * Serialize POI entries to bytes.
     *
     * @return the serialized bytes, or {@code null} if the map is empty.
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
     * Deserialize POI entries from bytes.
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
                log.warn("Unknown POI type '{}' at ({}, {}, {}), skipping", typeName, x, y, z);
            }
        }
        return poiEntries;
    }

    private static PoiType findPoiTypeByName(String name) {
        for (var poiType : Registries.POI_TYPES.getContent().values()) {
            if (poiType.name().equals(name)) {
                return poiType;
            }
        }
        return null;
    }
}
