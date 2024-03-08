package org.allaymc.server.world.storage;

import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.api.world.storage.WorldStorageFactory;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Allay Project 2024/2/3
 *
 * @author daoge_cmd
 */
public class AllayWorldStorageFactory extends SimpleMappedRegistry<String, Function<Path, WorldStorage>, Map<String, Function<Path, WorldStorage>>> implements WorldStorageFactory {
    public AllayWorldStorageFactory() {
        super(null, unused -> new ConcurrentHashMap<>());
        init();
    }

    protected void init() {
        register("NON_PERSISTENT", unused -> new AllayNonPersistentWorldStorage());
        register("LEVELDB", AllayLevelDBWorldStorage::new);
    }
}
