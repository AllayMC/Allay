package org.allaymc.api.pack;

import org.jetbrains.annotations.ApiStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Represents a loader for pack.
 *
 * @author IWareQ, Cloudburst Server
 */
public interface PackLoader extends AutoCloseable {

    /**
     * Get the location of the pack.
     *
     * @return the location of the pack
     */
    Path getLocation();

    /**
     * Check if the pack has a file.
     *
     * @param name the name of the file
     *
     * @return {@code true} if the pack has the file, {@code false} otherwise.
     */
    boolean hasFile(String name);

    /**
     * Get the input stream of a file.
     *
     * @param name the name of the file
     *
     * @return the input stream of the file
     *
     * @throws IOException if an I/O error occurs
     */
    InputStream getFile(String name) throws IOException;

    /**
     * Get the content key.
     *
     * @return the content key
     */
    String getContentKey();

    /**
     * Read all bytes of the pack.
     *
     * @return bytes of the pack
     */
    byte[] readAllBytes();

    /**
     * The factory for creating pack loaders.
     */
    @ApiStatus.OverrideOnly
    interface Factory {

        /**
         * Check if the factory can load a pack. If return {@code true}, method
         * {@link #create(Path)} will then be called.
         *
         * @param path the path of the pack
         *
         * @return {@code true} if the factory can load the pack, {@code false} otherwise.
         */
        boolean canLoad(Path path);

        /**
         * Create a pack loader.
         *
         * @param path the path of the pack
         *
         * @return the pack loader
         *
         * @throws IOException if an I/O error occurs
         */
        PackLoader create(Path path) throws IOException;
    }
}
