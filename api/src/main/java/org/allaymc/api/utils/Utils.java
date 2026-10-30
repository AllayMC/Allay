package org.allaymc.api.utils;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/**
 * {@code Utils} supplies API helpers for bit widths, object-string conversion, text resources,
 * and executor-backed collection processing. Text reads own and close their inputs, resource
 * lookup requires an existing classpath entry, and parallel processing delegates completion
 * aggregation to {@code CompletableFuture.allOf}.
 *
 * @author daoge_cmd
 */
@UtilityClass
public class Utils {
    /**
     * Returns the unsigned binary width of the integer bit pattern. Zero needs zero bits and
     * negative values need thirty-two bits, so callers must not interpret this result as a
     * signed magnitude width.
     */
    public byte computeRequiredBits(int value) {
        return (byte) (Integer.SIZE - Integer.numberOfLeadingZeros(value));
    }

    /**
     * Returns a new string array containing each element's {@code toString} result in order.
     * The input and its elements must be non-{@code null}; a missing element fails conversion rather
     * than becoming the literal string {@code "null"}.
     */
    public String[] objectArrayToStringArray(Object[] objectArray) {
        return Arrays.stream(objectArray).map(Object::toString).toArray(String[]::new);
    }

    /**
     * Reads UTF-8 text using the reader overload and closes the supplied stream. Line boundaries
     * are normalized by that reader path, which discards leading empty lines and the final line
     * terminator rather than preserving exact input bytes.
     */
    public static String readString(InputStream inputStream) throws IOException {
        return readString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    /**
     * Reads lines into one string and closes the supplied reader. Leading empty lines disappear
     * because separators are added only after content exists; subsequent line boundaries use
     * {@code "\n"}, and a final line terminator is not retained.
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
     * Opens the classpath resource with this API class's loader. The caller owns and closes the
     * returned stream, and a missing resource throws {@code NullPointerException} rather than
     * returning an absent-stream sentinel.
     */
    public static InputStream getResource(String resourceName) {
        return Objects.requireNonNull(Utils.class.getClassLoader().getResourceAsStream(resourceName));
    }

    /**
     * Submits one consumer task per element to {@code executor} and returns their aggregate
     * future.
     * The aggregate completes after all submitted tasks finish and becomes exceptional when a
     * task fails; cancelling it does not cancel those tasks. Submission rejection can throw before
     * an aggregate is returned while earlier submissions remain active.
     */
    public static <T> CompletableFuture<Void> forEachInParallel(Collection<T> collection, Executor executor, Consumer<T> consumer) {
        CompletableFuture<?>[] futures = collection.stream()
                .map(element ->
                        CompletableFuture.runAsync(
                                () -> consumer.accept(element),
                                executor
                        )
                )
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(futures);
    }
}
