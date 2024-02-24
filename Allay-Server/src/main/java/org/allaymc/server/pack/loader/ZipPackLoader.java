package org.allaymc.server.pack.loader;

import lombok.SneakyThrows;
import org.allaymc.api.pack.PackLoader;
import org.allaymc.server.pack.PackUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Allay Project 28/01/2024
 *
 * @author IWareQ, Cloudburst Server
 */
public class ZipPackLoader implements PackLoader {

    public static final PackLoader.Factory FACTORY = new ZipFactory();

    private final Path path;
    private final ZipFile zipFile;

    private transient CompletableFuture<Path> networkPreparedFuture;

    private ZipPackLoader(Path path) throws IOException {
        this.path = path;
        try {
            this.zipFile = new ZipFile(this.path.toFile());
        } catch (IOException exception) {
            throw new IOException("Path is not a zip file", exception);
        }
    }

    private ZipEntry getEntry(Path path) {
        return this.zipFile.getEntry(path.toString());
    }

    @Override
    public Path getLocation() {
        return this.path;
    }

    @Override
    public boolean hasFile(Path path) {
        var entry = this.getEntry(path);
        return entry != null && !entry.isDirectory();
    }

    @Override
    public InputStream getFile(Path path) throws IOException {
        var entry = this.getEntry(path);
        if (entry == null) return null;
        return this.zipFile.getInputStream(entry);
    }

    @Override
    public boolean hasFolder(Path folder) {
        var entry = this.getEntry(path);
        return entry != null && entry.isDirectory();
    }

    @Override
    public void forEachIn(Path path, Consumer<Path> consumer, boolean recurse) {
        var entries = this.zipFile.entries();
        while (entries.hasMoreElements()) {
            var entry = entries.nextElement();

            var entryPath = Path.of(entry.getName());
            if (entryPath.getParent().equals(path)) consumer.accept(entryPath);
            if (entry.isDirectory()) this.forEachIn(entryPath, consumer, true);
        }
    }

    @Override
    public CompletableFuture<Path> getNetworkPreparedFile() {
        if (this.networkPreparedFuture == null)
            this.networkPreparedFuture = CompletableFuture.completedFuture(this.path);
        return this.networkPreparedFuture;
    }

    @SneakyThrows
    @Override
    public String findContentKey() {
        var keyFilePath = path.getParent().resolve(path.getFileName() + ".key");
        return Files.exists(keyFilePath) ? Files.readString(keyFilePath) : "";
    }

    @Override
    public void close() throws IOException {
        this.zipFile.close();
    }

    private static class ZipFactory implements Factory {

        @Override
        public boolean canLoad(Path path) {
            return PackUtils.isZipPack(path);
        }

        @Override
        public PackLoader create(Path path) throws IOException {
            return new ZipPackLoader(path);
        }
    }
}
