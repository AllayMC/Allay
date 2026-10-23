package org.allaymc.server.pack.loader;

import lombok.SneakyThrows;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.server.pack.ResourcePackException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.*;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * {@code InPluginPackLoader} reads a pack embedded beneath a plugin JAR's
 * {@code assets/resource_pack} directory. It owns a ZIP file system for manifest
 * and file access, while {@code readAllBytes} builds a separate archive whose
 * root is the embedded pack directory. Closing the loader closes that file
 * system and ends access through its archive paths.
 *
 * <p>
 * Client archive paths must be relative to the embedded pack rather than the
 * plugin JAR, so rebuilding removes the directory prefix and normalizes path
 * separators to slashes. The root {@code encryption.key} supplies the content
 * key but is omitted from transferred bytes. Files with that name in nested
 * directories remain part of the rebuilt archive.
 *
 * @author daoge_cmd
 */
public class InPluginPackLoader implements PackLoader {

    /**
     * {@code FACTORY} discovers plugin JARs with an embedded pack directory and
     * opens loaders rooted at that directory for registry loading.
     */
    public static final Factory FACTORY = new InPluginFactory();

    // IN_PLUGIN_RESOURCE_PACK_PATH selects the embedded pack root, while
    // ENCRYPTION_KEY_FILE_NAME identifies its local key metadata. Rebuilt client
    // archives omit only that key file at the selected root.
    /** Embedded pack directory path. */
    private static final String IN_PLUGIN_RESOURCE_PACK_PATH = "assets/resource_pack";
    /** Root encryption-key file name. */
    private static final String ENCRYPTION_KEY_FILE_NAME = "encryption.key";

    /**
     * {@code zipFileSystem} owns the opened plugin archive until {@code close}
     * runs; paths and entry streams depend on that file system remaining open.
     */
    private final FileSystem zipFileSystem;
    /** Embedded pack root path. */
    private final Path root;

    /**
     * Opens a plugin archive and resolves its embedded pack root.
     * The caller owns the new file system through this loader and must close
     * it after all returned entry streams have been consumed and closed.
     */
    @SneakyThrows
    private InPluginPackLoader(Path path) {
        this.zipFileSystem = FileSystems.newFileSystem(URI.create("jar:" + path.toUri()), Collections.singletonMap("create", "false"));
        this.root = zipFileSystem.getPath("/").resolve(IN_PLUGIN_RESOURCE_PACK_PATH);
    }

    /**
     * Returns the embedded pack root within the opened plugin archive.
     */
    @Override
    public Path getLocation() {
        return this.root;
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
     * Opens the resolved pack entry, or returns {@code null} when absent.
     * Callers close returned streams before closing this loader; entry-opening
     * I/O failures are wrapped in {@code ResourcePackException}, including paths
     * that exist but cannot be opened as files.
     */
    @Override
    public InputStream getFile(String name) {
        Path zipPath = root.resolve(name);
        try {
            return Files.exists(zipPath) ? Files.newInputStream(zipPath) : null;
        } catch (IOException e) {
            throw new ResourcePackException(e);
        }
    }

    /**
     * Builds a standalone ZIP containing files below the embedded pack root.
     * The returned bytes exclude the root {@code encryption.key}, and the
     * temporary walk, entry streams, and output archive close within this call.
     *
     * <p>
     * Each entry removes the plugin-specific directory prefix and normalizes
     * separators to {@code /}, preserving the relative names used by the pack's
     * manifest and assets. Nested files named {@code encryption.key} remain
     * ordinary payload files. Entry and walk-opening I/O failures throw
     * {@code ResourcePackException}; lazy traversal can instead propagate
     * {@code UncheckedIOException}. Neither failure path returns a partially
     * generated client archive.
     */
    @Override
    public byte[] readAllBytes() {
        try (var byteArrayOutputStream = new ByteArrayOutputStream();
             var zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
             var stream = Files.walk(root)) {
            stream.forEach(path -> {
                if (Files.isDirectory(path)) {
                    return;
                }

                // The root key is separate decryption metadata, so omit it
                // from transferred bytes while preserving identically named nested assets.
                if (path.getFileName().toString().equals(ENCRYPTION_KEY_FILE_NAME) && path.getParent().equals(root)) {
                    return;
                }

                try (var in = Files.newInputStream(path)) {
                    // Rebase entries onto the pack root and normalize separators
                    // so clients resolve assets independently of the plugin container.
                    String relative = root.relativize(path).toString().replace("\\", "/");
                    zipOutputStream.putNextEntry(new ZipEntry(relative));
                    in.transferTo(zipOutputStream);
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    throw new ResourcePackException(e);
                }
            });
            zipOutputStream.finish();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new ResourcePackException(e);
        }
    }

    /**
     * Returns the root encryption-key file's UTF-8 text, or an empty string.
     * The text is returned without trimming; an absent file denotes an
     * unencrypted pack, and read failures propagate from the file system.
     */
    @SneakyThrows
    @Override
    public String getContentKey() {
        var keyFilePath = root.resolve(ENCRYPTION_KEY_FILE_NAME);
        return Files.exists(keyFilePath) ? Files.readString(keyFilePath) : "";
    }

    /**
     * Closes the ZIP file system owned by this embedded-pack loader.
     * Callers must first close entry streams and finish using archive paths;
     * cleanup failures propagate rather than being logged and suppressed here.
     */
    @Override
    public void close() throws Exception {
        this.zipFileSystem.close();
    }

    /**
     * {@code InPluginFactory} recognizes plugin archives containing the embedded pack directory.
     */
    private static class InPluginFactory implements Factory {

        /**
         * {@code PATH_MATCHER} restricts discovery probes to JAR-shaped paths
         * before opening an archive to inspect the embedded pack directory.
         */
        protected static final PathMatcher PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.jar");

        /**
         * Returns whether a regular JAR candidate contains the embedded pack directory.
         * A temporary ZIP file system closes after inspection, and caught I/O
         * failures return {@code false}; unchecked file-system failures still
         * propagate to the caller.
         */
        @Override
        public boolean canLoad(Path path) {
            if (!PATH_MATCHER.matches(path) || !Files.isRegularFile(path)) {
                return false;
            }

            URI uri = URI.create("jar:" + path.toUri());

            try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.singletonMap("create", "false"))) {
                Path internalPath = fs.getPath("/").resolve(IN_PLUGIN_RESOURCE_PACK_PATH);
                return Files.exists(internalPath) && Files.isDirectory(internalPath);
            } catch (IOException e) {
                // An unreadable archive is rejected as a loader candidate so
                // discovery can continue considering other files without opening this pack.
                return false;
            }
        }

        /**
         * Opens an embedded-pack loader for the supplied plugin archive.
         * The caller owns the returned loader and must close its file system;
         * this operation does not repeat the discovery probe or validate a manifest.
         */
        @Override
        public PackLoader create(Path path) {
            return new InPluginPackLoader(path);
        }
    }
}
