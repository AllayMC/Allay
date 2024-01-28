package org.allaymc.api.pack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Allay Project 28/01/2024
 *
 * @author IWareQ, Cloudburst Server
 */
public interface PackLoader extends AutoCloseable {

    Path getLocation();

    boolean hasAsset(Path path);

    InputStream getAsset(Path path) throws IOException;

    boolean hasFolder(Path folder);

    void forEachIn(Path path, Consumer<Path> consumer, boolean recurse);

    CompletableFuture<Path> getNetworkPreparedFile();

    interface Factory {

        boolean canLoad(Path path);

        PackLoader create(Path path) throws IOException;
    }
}
