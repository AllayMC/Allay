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
 * The loader that loads packs from zip/mcpack files using java.nio.file.FileSystem.
 */
public class ZipPackLoader implements PackLoader {

    public static final PackLoader.Factory FACTORY = new ZipFactory();

    private final Path path;
    private final FileSystem zipFileSystem;
    private final Path root;

    @SneakyThrows
    private ZipPackLoader(Path path) {
        this.path = path;
        this.zipFileSystem = FileSystems.newFileSystem(URI.create("jar:" + path.toUri()), Collections.singletonMap("create", "false"));
        this.root = zipFileSystem.getPath("/");
    }

    @Override
    public Path getLocation() {
        return this.path;
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

    @Override
    public byte[] readAllBytes() {
        try {
            return Files.readAllBytes(this.root);
        } catch (IOException e) {
            throw new ResourcePackException(e);
        }
    }

    @Override
    public String getContentKey() {
        var keyFilePath = path.getParent().resolve(path.getFileName() + ".key");
        try {
            return Files.exists(keyFilePath) ? Files.readString(keyFilePath) : "";
        } catch (IOException e) {
            throw new ResourcePackException(e);
        }
    }

    @Override
    public void close() throws IOException {
        this.zipFileSystem.close();
    }

    private static class ZipFactory implements Factory {
        @Override
        public boolean canLoad(Path path) {
            return PackUtils.isZipPack(path);
        }

        @Override
        public PackLoader create(Path path) {
            return new ZipPackLoader(path);
        }
    }
}
