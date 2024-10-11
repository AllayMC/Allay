package org.allaymc.api.utils;

import com.google.common.base.Preconditions;
import eu.okaeri.configs.OkaeriConfigInitializer;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import io.netty.buffer.ByteBuf;
import lombok.experimental.UtilityClass;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.tag.BlockTag;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.tag.ItemTag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author daoge_cmd
 */
@UtilityClass
public class Utils {

    // Some empty constants

    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final BlockState[][][] EMPTY_BLOCK_STATE_ARRAY_3D = new BlockState[0][0][0];
    public static final BlockTag[] EMPTY_BLOCK_TAG_ARRAY = new BlockTag[0];
    public static final ItemTag[] EMPTY_ITEM_TAG_ARRAY = new ItemTag[0];
    public static final ItemId[] EMPTY_ITEM_ID_ARRAY = new ItemId[0];
    public static final BlockId[] EMPTY_BLOCK_ID_ARRAY = new BlockId[0];
    public static final ItemStack[] EMPTY_ITEM_STACK_ARRAY = new ItemStack[0];
    public static final Set<ItemStack> EMPTY_ITEM_STACK_SET = Set.of();

    /**
     * Merge multiple byte arrays into one byte array.
     *
     * @param bytes1 the first byte array.
     * @param bytes2 the other byte arrays.
     * @return the merged byte array.
     */
    public byte[] appendBytes(byte[] bytes1, byte[]... bytes2) {
        int length = bytes1.length;
        for (byte[] bytes : bytes2) {
            length += bytes.length;
        }

        byte[] appendedBytes = new byte[length];
        System.arraycopy(bytes1, 0, appendedBytes, 0, bytes1.length);
        int index = bytes1.length;

        for (byte[] b : bytes2) {
            System.arraycopy(b, 0, appendedBytes, index, b.length);
            index += b.length;
        }
        return appendedBytes;
    }

    /**
     * Calculates the number of bit that the specified value convert to binary.
     *
     * @param value the value.
     * @return the bits.
     */
    public byte computeRequiredBits(int value) {
        Preconditions.checkArgument(value >= 0);
        if (value <= 1) return 1;
        byte bits = 1;
        while (value >= (1 << bits)) {
            bits++;
        }
        return bits;
    }

    /**
     * Convert a netty byte buffer to a byte array.
     *
     * @param buf the byte buffer.
     * @return the byte array.
     */
    public byte[] convertByteBuf2Array(ByteBuf buf) {
        byte[] payload = new byte[buf.readableBytes()];
        buf.readBytes(payload);
        return payload;
    }

    /**
     * Convert a {@code Object[]} array to a {@code String[]} array.
     *
     * @param objectArray the object array.
     * @return the string array.
     */
    public String[] objectArrayToStringArray(Object[] objectArray) {
        String[] stringArray = new String[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            stringArray[i] = objectArray[i].toString();
        }
        return stringArray;
    }

    /**
     * Read a string from the input stream.
     *
     * @param inputStream the input stream.
     * @return the string.
     * @throws IOException if an I/O error occurs.
     */
    public static String readString(InputStream inputStream) throws IOException {
        return readString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    /**
     * Read a string from the reader.
     *
     * @param reader the reader.
     * @return the string.
     * @throws IOException if an I/O error occurs.
     */
    public static String readString(Reader reader) throws IOException {
        try (BufferedReader br = new BufferedReader(reader)) {
            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            temp = br.readLine();
            while (temp != null) {
                if (!stringBuilder.isEmpty()) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(temp);
                temp = br.readLine();
            }
            return stringBuilder.toString();
        }
    }

    /**
     * Get a specified resource in the jar file.
     *
     * @param resourceName the resource name.
     * @return the input stream.
     */
    public static InputStream getResource(String resourceName) {
        return Objects.requireNonNull(Utils.class.getClassLoader().getResourceAsStream(resourceName));
    }

    /**
     * Create a default config initializer.
     *
     * @param file the file path.
     * @return the config initializer.
     */
    public static OkaeriConfigInitializer createConfigInitializer(Path file) {
        return it -> {
            // Specify configurer implementation, optionally additional serdes packages
            it.withConfigurer(new YamlSnakeYamlConfigurer());
            // Specify Path, File or pathname
            it.withBindFile(file);
            // Automatic removal of undeclared keys
            it.withRemoveOrphans(true);
            // Save file if it does not exist
            it.saveDefaults();
            // Load and save to update comments/new fields
            it.load(true);
        };
    }
}
