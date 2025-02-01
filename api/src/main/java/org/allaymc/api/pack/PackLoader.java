package org.allaymc.api.pack;

import org.jetbrains.annotations.ApiStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Represents a loader for pack.
 *
 * @author IWareQ, Cloudburst Server
 */
public interface PackLoader extends AutoCloseable {

    /**
     * Get the location of the pack.
     *
     * @return the location of the pack.
     */
    Path getLocation();

    /**
     * Check if the pack has a file.
     *
     * @param path the path of the file.
     *
     * @return {@code true} if the pack has the file, {@code false} otherwise.
     */
    boolean hasFile(Path path);

    /**
     * Get the input stream of a file.
     *
     * @param path the path of the file.
     *
     * @return the input stream of the file.
     *
     * @throws IOException if an I/O error occurs.
     */
    InputStream getFile(Path path) throws IOException;

    /**
     * Check if the pack has a folder.
     *
     * @param folder the path of the folder.
     *
     * @return {@code true} if the pack has the folder, {@code false} otherwise.
     */
    boolean hasFolder(Path folder);

    /**
     * For each file in a folder.
     *
     * @param path     the path of the folder.
     * @param consumer the consumer.
     * @param recurse  {@code true} to recurse into subfolders, {@code false} otherwise.
     */
    void forEachIn(Path path, Consumer<Path> consumer, boolean recurse);

    /**
     * Get the prepared file for network.
     *
     * @return the prepared file for network.
     */
    CompletableFuture<Path> getNetworkPreparedFile();

    /**
     * Get the content key.
     *
     * @return the content key.
     */
    String findContentKey();

    /**
     * The factory for creating pack loaders.
     */
    @ApiStatus.OverrideOnly
    interface Factory {

        /**
         * Check if the factory can load a pack. If return {@code true}, method
         * {@link #create(Path)} will then be called.
         *
         * @param path the path of the pack.
         *
         * @return {@code true} if the factory can load the pack, {@code false} otherwise.
         */
        boolean canLoad(Path path);

        /**
         * Create a pack loader.
         *
         * @param path the path of the pack.
         *
         * @return the pack loader.
         *
         * @throws IOException if an I/O error occurs.
         */
        PackLoader create(Path path) throws IOException;
    }
}
