package org.allaymc.server.pack.loader;

import lombok.SneakyThrows;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.server.pack.PackUtils;
import org.allaymc.server.pack.ResourcePackException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

/**
 * {@code ZipPackLoader} reads pack entries from an opened ZIP file system and
 * supplies the original archive bytes for transfer. A sibling key file supplies
 * optional encryption metadata. The loader owns its file system until closed,
 * while consumers own any entry streams returned during that lifetime.
 */
public class ZipPackLoader implements PackLoader {

    /**
     * {@code FACTORY} selects ZIP-shaped pack paths and opens their archive file
     * systems when the registry chooses this loader implementation.
     */
    public static final PackLoader.Factory FACTORY = new ZipFactory();

    /**
     * {@code path} retains the source archive location. Key lookup requires its
     * parent directory to resolve the sibling key file.
     */
    private final Path path;
    /**
     * {@code zipFileSystem} owns the opened archive used for entry access until
     * {@code close} ends that file system's lifetime.
     */
    private final FileSystem zipFileSystem;
    /** Opened archive root path. */
    private final Path root;

    /**
     * Opens the supplied archive as a ZIP file system for entry access.
     * The constructor retains {@code path} for complete-byte reads and key
     * lookup; the caller must close the loader after consuming entry streams.
     */
    @SneakyThrows
    private ZipPackLoader(Path path) {
        this.path = path;
        this.zipFileSystem = FileSystems.newFileSystem(URI.create("jar:" + path.toUri()), Collections.singletonMap("create", "false"));
        this.root = zipFileSystem.getPath("/");
    }

    /**
     * Returns the source archive path retained by this loader instance.
     */
    @Override
    public Path getLocation() {
        return this.path;
    }

    /**
     * Returns whether the resolved archive path exists as a regular file.
     */
    @Override
    public boolean hasFile(String name) {
        Path zipPath = root.resolve(name);
        return Files.exists(zipPath) && Files.isRegularFile(zipPath);
    }

    /**
     * Opens the resolved archive entry, or returns {@code null} when absent.
     * Callers own returned streams and close them before closing this loader;
     * existing entries that cannot be opened as files produce an I/O failure.
     */
    @Override
    public InputStream getFile(String name) throws IOException {
        Path zipPath = root.resolve(name);
        return Files.exists(zipPath) ? Files.newInputStream(zipPath) : null;
    }

    /**
     * Reads the original source archive into a newly allocated byte array.
     * This operation reads {@code path} directly rather than rebuilding entries,
     * and I/O failures throw {@code ResourcePackException} instead of returning
     * partial archive bytes.
     */
    @Override
    public byte[] readAllBytes() {
        try {
            return Files.readAllBytes(this.path);
        } catch (IOException e) {
            throw new ResourcePackException(e);
        }
    }

    /**
     * Returns UTF-8 text from the archive's sibling {@code .key} file.
     * The filename appends {@code .key} to the complete archive name, and missing
     * files return an empty string. The retained source path must have a parent;
     * key text is returned without trimming.
     */
    @Override
    public String getContentKey() {
        var keyFilePath = path.getParent().resolve(path.getFileName() + ".key");
        try {
            return Files.exists(keyFilePath) ? Files.readString(keyFilePath) : "";
        } catch (IOException e) {
            throw new ResourcePackException(e);
        }
    }

    /**
     * Closes the archive file system used by this loader's entry operations.
     * Callers must finish and close returned entry streams first; this method
     * propagates cleanup failures and does not remove the source archive.
     */
    @Override
    public void close() throws IOException {
        this.zipFileSystem.close();
    }

    /**
     * {@code ZipFactory} selects archive pack paths and creates their ZIP-backed loaders.
     */
    private static class ZipFactory implements Factory {
        /**
         * Returns whether the candidate matches the supported archive suffix pattern.
         * The check delegates to {@code PackUtils} without testing file existence,
         * archive readability, or manifest validity before loader creation.
         */
        @Override
        public boolean canLoad(Path path) {
            return PackUtils.isZipPack(path);
        }

        /**
         * Opens a ZIP pack loader for the selected archive path.
         * The caller owns the returned file system through that loader and must
         * close it after consuming all required pack entry streams.
         */
        @Override
        public PackLoader create(Path path) {
            return new ZipPackLoader(path);
        }
    }
}
