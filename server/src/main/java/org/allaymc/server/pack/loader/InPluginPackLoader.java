package org.allaymc.server.pack.loader;

import lombok.SneakyThrows;
import org.allaymc.api.pack.PackLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.*;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * The loader that loads packs that are inside the jar plugins.
 *
 * @author daoge_cmd
 */
public class InPluginPackLoader implements PackLoader {

    public static final Factory FACTORY = new InPluginFactory();

    private static final String IN_PLUGIN_RESOURCE_PACK_PATH = "assets/resource_pack";
    private static final String ENCRYPTION_KEY_FILE_NAME = "encryption.key";

    private final FileSystem zipFileSystem;
    private final Path root;

    @SneakyThrows
    private InPluginPackLoader(Path path) {
        this.zipFileSystem = FileSystems.newFileSystem(URI.create("jar:" + path.toUri()), Collections.singletonMap("create", "false"));
        this.root = zipFileSystem.getPath("/").resolve(IN_PLUGIN_RESOURCE_PACK_PATH);
    }

    @Override
    public Path getLocation() {
        return this.root;
    }

    @Override
    public boolean hasFile(String name) {
        Path zipPath = root.resolve(name);
        return Files.exists(zipPath) && Files.isRegularFile(zipPath);
    }

    @Override
    public InputStream getFile(String name) throws IOException {
        Path zipPath = root.resolve(name);
        return Files.exists(zipPath) ? Files.newInputStream(zipPath) : null;
    }

    @SneakyThrows
    @Override
    public byte[] readAllBytes() {
        try (var byteArrayOutputStream = new ByteArrayOutputStream();
             var zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
             var stream = Files.walk(root)) {
            stream.forEach(path -> {
                if (Files.isDirectory(path)) {
                    return;
                }

                if (path.getFileName().toString().equals(ENCRYPTION_KEY_FILE_NAME) && path.getParent().equals(root)) {
                    return;
                }

                try (var in = Files.newInputStream(path)) {
                    String relative = root.relativize(path).toString().replace("\\", "/");
                    zipOutputStream.putNextEntry(new ZipEntry(relative));
                    in.transferTo(zipOutputStream);
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            zipOutputStream.finish();
            return byteArrayOutputStream.toByteArray();
        }
    }

    @SneakyThrows
    @Override
    public String getContentKey() {
        // Pack encrypting is not supported in in-plugin packs
        var keyFilePath = root.resolve(ENCRYPTION_KEY_FILE_NAME);
        return Files.exists(keyFilePath) ? Files.readString(keyFilePath) : "";
    }

    @Override
    public void close() throws Exception {
        this.zipFileSystem.close();
    }

    private static class InPluginFactory implements Factory {

        protected static final PathMatcher PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.jar");

        @Override
        public boolean canLoad(Path path) {
            if (!PATH_MATCHER.matches(path) || !Files.isRegularFile(path)) {
                return false;
            }

            // Use FileSystem instead of ZipFile to check internal entry
            URI uri = URI.create("jar:" + path.toUri());

            try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.singletonMap("create", "false"))) {
                Path internalPath = fs.getPath("/").resolve(IN_PLUGIN_RESOURCE_PACK_PATH);
                return Files.exists(internalPath) && Files.isDirectory(internalPath);
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        public PackLoader create(Path path) {
            return new InPluginPackLoader(path);
        }
    }
}
