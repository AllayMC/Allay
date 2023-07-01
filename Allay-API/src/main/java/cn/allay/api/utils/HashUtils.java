package cn.allay.api.utils;

import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.identifier.Identifier;
import lombok.experimental.UtilityClass;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/**
 * Allay Project 2023/6/1
 *
 * @author Cool_Loong
 */
@UtilityClass
public class HashUtils {
    //https://gist.github.com/Alemiz112/504d0f79feac7ef57eda174b668dd345
    private static final int FNV1_32_INIT = 0x811c9dc5;
    private static final int FNV1_PRIME_32 = 0x01000193;

    public int computeBlockStateHash(Identifier identifier, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        if (identifier.equals(VanillaBlockId.UNKNOWN.getIdentifier())) {
            return -2; // This is special case
        }

        var states = new TreeMap<String, Object>();
        for (var value : propertyValues) {
            states.put(value.getPropertyType().getName(), value.getSerializedValue());
        }

        var tag = NbtMap.builder()
                .putString("name", identifier.toString())
                .putCompound("states", NbtMap.fromMap(states))
                .build();

        return fnv1a_32_nbt(tag);
    }

    public int fnv1a_32_nbt(NbtMap tag) {
        byte[] bytes;
        try (var stream = new ByteArrayOutputStream();
             var outputStream = NbtUtils.createWriterLE(stream)) {
            outputStream.writeTag(tag);
            bytes = stream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fnv1a_32(bytes);
    }

    public int fnv1a_32(byte[] data) {
        int hash = FNV1_32_INIT;
        for (byte datum : data) {
            hash ^= (datum & 0xff);
            hash *= FNV1_PRIME_32;
        }
        return hash;
    }

    public long hashXZ(int x, int z) {
        return ( (long) x << 32 ) | ( z & 0xffffffffL );
    }

    public int getXFromHashXZ(long hashXZ) {
        return (int) ( hashXZ >> 32 );
    }

    public int getZFromHashXZ(long hashXZ) {
        return (int) hashXZ;
    }
}
