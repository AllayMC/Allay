package org.allaymc.server.block.hash;

import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.ByteArrayOutputStream;
import java.util.TreeMap;

/**
 * {@code PaletteHashTest} prints sample palette hashes and exposes the exploratory
 * FNV-1a calculation.
 *
 * @author daoge_cmd
 */
public class PaletteHashTest {

    // FNV1_32_INIT provides the initial hash and FNV1_PRIME_32 advances each byte.
    // Java integer overflow retains the low 32 bits used by FNV-1a.
    /**
     * FNV-1a offset basis, 32-bit.
     */
    private static final int FNV1_32_INIT = 0x811c9dc5;
    /**
     * FNV-1a per-byte multiplication factor.
     */
    private static final int FNV1_PRIME_32 = 0x01000193;

    /**
     * Prints signed or unsigned hashes for several hardcoded state compounds. This
     * diagnostic entry point compares representative encodings, including the
     * reserved unknown-block value, but contains no assertions about the printed
     * results.
     */
    public static void main(String[] args) {
        NbtMap b1 = NbtMap.builder()
                .putString("name", "minecraft:polished_basalt")
                .putCompound("states", NbtMap.builder()
                        .putString("pillar_axis", "x")
                        .build())
                .build();

        System.out.printf("b1 hash: %s%n", createHash(b1));

        NbtMap b2 = NbtMap.builder()
                .putString("name", "minecraft:blue_candle")
                .putCompound("states", NbtMap.builder()
                        .putInt("candles", 2)
                        .putBoolean("lit", false)
                        .build())
                .build();

        System.out.printf("b2 hash: %s%n", Integer.toUnsignedLong(createHash(b2)));

        NbtMap b3 = NbtMap.builder()
                .putString("name", "minecraft:coral_fan")
                .putCompound("states", NbtMap.builder()
                        .putString("coral_color", "blue")
                        .putInt("coral_fan_direction", 0)
                        .build())
                .build();

        System.out.printf("b3 hash: %s%n", Integer.toUnsignedLong(createHash(b3)));

        NbtMap b4 = NbtMap.builder()
                .putString("name", "minecraft:unknown")
                .putCompound("states", NbtMap.fromMap(new TreeMap<>()))
                .build();

        System.out.printf("b4 hash: %s%n", Integer.toUnsignedLong(createHash(b4)));
    }

    /**
     * Computes a normalized sample block-state hash from the name and property
     * compound. The unknown block returns its reserved signed hash without
     * serializing the supplied properties. Other states use only the name and
     * states, ignoring extra fields such as a version number.
     *
     * <p>
     * Property names are copied into sorted order before little-endian NBT
     * serialization. This ordering keeps the hash stable when otherwise equivalent
     * input compounds enumerate their properties differently, while byte mixing
     * follows the 32-bit FNV-1a calculation.
     */
    @SneakyThrows
    public static int createHash(NbtMap block) {
        if (block.getString("name").equals("minecraft:unknown")) {
            // The unknown palette entry reserves unsigned 4294967294, so use its
            // signed representation instead of hashing the NBT.
            return -2;
        }

        // Normalize property order before serialization so compound insertion order
        // cannot change the hash of an equivalent block state.
        var states = new TreeMap<>(block.getCompound("states"));

        NbtMap tag = NbtMap.builder()
                .putString("name", block.getString("name"))
                .putCompound("states", NbtMap.fromMap(states))
                .build();

        byte[] bytes;
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream();
             NBTOutputStream outputStream = NbtUtils.createWriterLE(stream)) {
            outputStream.writeTag(tag);
            bytes = stream.toByteArray();
        }

        return fnv1a_32(bytes);
    }

    /**
     * Returns the 32-bit FNV-1a hash of the supplied byte array.
     */
    public static int fnv1a_32(byte[] data) {
        int hash = FNV1_32_INIT;
        for (byte datum : data) {
            // Treat serialized NBT bytes as unsigned values before mixing them into
            // the hash.
            hash ^= (datum & 0xff);
            hash *= FNV1_PRIME_32;
        }
        return hash;
    }
}
