package cn.allay.server.block.hash;

import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/30 <br>
 * Allay Project <br>
 */
public class PaletteHashTest {

    private static final int FNV1_32_INIT = 0x811c9dc5;
    private static final int FNV1_PRIME_32 = 0x01000193;

    /**
     * Some info:
     * <p>
     * The compute method "Block#computeRawSerializationIdHashForNetwork()" does not account for collision and special cases.
     * That's why you should use instead the property I mentioned.
     * <p>
     * The special cases are just hardcoded as I wrote.
     * Other thing to account for is that when there is a collision one of the collided hashes is increased by 1.
     * <p>
     * Special cases:
     * <p>
     * minecraft:coral_fan;coral_color=blue;coral_fan_direction=0
     * minecraft:coral_fan_dead;coral_color=blue;coral_fan_direction=0
     * minecraft:unknown
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

        //781710940
        System.out.printf("b3 hash: %s%n", Integer.toUnsignedLong(createHash(b3)));

        NbtMap b4 = NbtMap.builder()
                .putString("name", "minecraft:unknown")
                .putCompound("states", NbtMap.fromMap(new TreeMap<>()))
                .build();

        System.out.printf("b4 hash: %s%n", Integer.toUnsignedLong(createHash(b4)));
    }

    public static int createHash(NbtMap block) {
        if (block.getString("name").equals("minecraft:unknown")) {
            return -2; // This is special case
        }

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fnv1a_32(bytes);
    }

    public static int fnv1a_32(byte[] data) {
        int hash = FNV1_32_INIT;
        for (byte datum : data) {
            hash ^= (datum & 0xff);
            hash *= FNV1_PRIME_32;
        }
        return hash;
    }
}
