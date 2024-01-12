package org.allaymc.api.utils;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.identifier.Identifier;
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

    public int computeBlockStateHash(Identifier identifier, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
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

    /**
     * Shift int x to the left by 32 bits and int z to form a long value
     *
     * @param x the int x
     * @param z the int z
     * @return the long
     */
    public long hashXZ(int x, int z) {
        return ((long) x << 32) | (z & 0xffffffffL);
    }

    /**
     * Gets x from {@link #hashXZ(int, int)}
     *
     * @param hashXZ a long value
     */
    public int getXFromHashXZ(long hashXZ) {
        return (int) (hashXZ >> 32);
    }

    /**
     * Gets z from {@link #hashXZ(int, int)}
     *
     * @param hashXZ a long value
     */
    public int getZFromHashXZ(long hashXZ) {
        return (int) hashXZ;
    }

    public int hashChunkXYZ(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= 0 && y <= 16777216);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        //Make sure x and z are in the range of 0-15
        x &= 0xF;  //4 bits
        z &= 0xF;  //4 bits
        //Use the int type to store the result
        int result = 0;
        //Place x in the top 4 digits
        result |= (x << 28);
        //Place y in the middle 24 bits
        result |= (y & 0xFFFFFF) << 4;
        //Place z in the lowest 4 digits
        result |= z;
        return result;
    }

    /**
     * Extract the value of x from the hash chunk xyz.
     * x occupies the highest 4 bits.
     *
     * @param encoded Encoded int containing x, y, and z.
     * @return The value of x.
     */
    public int getXFromHashChunkXYZ(int encoded) {
        return (encoded >>> 28);
    }

    /**
     * Extract the value of y from the hash chunk xyz.
     * y occupies the middle 24 bits.
     *
     * @param encoded Encoded int containing x, y, and z.
     * @return The value of y.
     */
    public int getYFromHashChunkXYZ(int encoded) {
        return (encoded >>> 4) & 0xFFFFFF;
    }

    /**
     * Extract the value of z from the hash chunk xyz.
     * z occupies the lowest 4 bits.
     *
     * @param encoded Encoded int containing x, y, and z.
     * @return The value of z.
     */
    public static int getZFromHashChunkXYZ(int encoded) {
        return encoded & 0xF;
    }
}
