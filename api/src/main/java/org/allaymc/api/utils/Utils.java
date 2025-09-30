package org.allaymc.api.utils;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author daoge_cmd
 */
@UtilityClass
public class Utils {
    /**
     * Calculates the number of bit that the specified value convert to binary.
     *
     * @param value the value
     * @return the bits
     */
    public byte computeRequiredBits(int value) {
        return (byte) (Integer.SIZE - Integer.numberOfLeadingZeros(value));
    }

    /**
     * Convert a {@code Object[]} array to a {@code String[]} array.
     *
     * @param objectArray the object array
     * @return the string array
     */
    public String[] objectArrayToStringArray(Object[] objectArray) {
        return Arrays.stream(objectArray).map(Object::toString).toArray(String[]::new);
    }

    /**
     * Read a string from the input stream.
     *
     * @param inputStream the input stream
     * @return the string
     * @throws IOException if an I/O error occurs
     */
    public static String readString(InputStream inputStream) throws IOException {
        return readString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    /**
     * Read a string from the reader.
     *
     * @param reader the reader
     * @return the string
     * @throws IOException if an I/O error occurs
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
     * @param resourceName the resource name
     * @return the input stream
     */
    public static InputStream getResource(String resourceName) {
        return Objects.requireNonNull(Utils.class.getClassLoader().getResourceAsStream(resourceName));
    }
}
