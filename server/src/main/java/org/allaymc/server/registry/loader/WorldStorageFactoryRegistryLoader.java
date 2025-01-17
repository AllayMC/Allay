package org.allaymc.server.registry.loader;

import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.world.storage.AllayLevelDBWorldStorage;
import org.allaymc.server.world.storage.AllayMemoryWorldStorage;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author daoge_cmd
 */
public class WorldStorageFactoryRegistryLoader implements RegistryLoader<Void, Map<String, Function<Path, WorldStorage>>> {
    @Override
    public Map<String, Function<Path, WorldStorage>> load(Void $) {
        var map = new HashMap<String, Function<Path, WorldStorage>>();
        map.put("LEVELDB", AllayLevelDBWorldStorage::new);
        map.put("MEMORY", AllayMemoryWorldStorage::new);
        return map;
    }
}
