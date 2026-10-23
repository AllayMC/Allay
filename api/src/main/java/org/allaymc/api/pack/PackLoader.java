package org.allaymc.api.pack;

import org.jetbrains.annotations.ApiStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * {@code PackLoader} supplies manifest files, archive bytes, and encryption keys
 * from a pack source. Loaders may own archive file systems until callers close
 * them. File streams returned by a loader have their own lifetimes and must be
 * closed by the consumer after reading.
 *
 * @author IWareQ, Cloudburst Server
 */
public interface PackLoader extends AutoCloseable {

    /**
     * Returns the source location used to identify this loader's pack.
     */
    Path getLocation();

    /**
     * Returns whether the named entry exists as a pack file.
     */
    boolean hasFile(String name);

    /**
     * Opens the named file for reading, or returns {@code null} when absent.
     * The caller owns a returned stream and must close it before closing the
     * loader that supplies its archive storage.
     *
     * @throws IOException if the loader reports a checked read failure
     */
    InputStream getFile(String name) throws IOException;

    /**
     * Returns the encryption key associated with the pack source.
     * An empty string denotes a source without a key; consumers must preserve
     * the supplied text rather than infer successful decryption from its presence.
     */
    String getContentKey();

    /**
     * Reads the complete archive representation used for hashing and client
     * transfer. The returned byte array contains the pack payload, which can be
     * synthesized from embedded files rather than copied from the source container.
     */
    byte[] readAllBytes();

    /**
     * {@code Factory} selects supported pack sources and creates their archive loaders.
     */
    @ApiStatus.OverrideOnly
    interface Factory {

        /**
         * Returns whether this factory accepts the supplied candidate path.
         * The registry selects the first accepting factory and then invokes
         * {@link #create(Path)}; acceptance does not guarantee that opening or
         * validating the pack succeeds.
         */
        boolean canLoad(Path path);

        /**
         * Opens a loader for a path accepted by this factory.
         * The caller owns the returned loader and must close its resources,
         * either directly or through a pack constructed from it.
         *
         * @throws IOException if the source cannot be opened through a checked failure
         */
        PackLoader create(Path path) throws IOException;
    }
}
