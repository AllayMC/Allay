package org.allaymc.server.registry.loader;

import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.world.storage.leveldb.AllayLevelDBWorldStorage;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * {@code WorldStorageFactoryRegistryLoader} associates world-storage configuration names with
 * their construction functions. The built-in {@code "LEVELDB"} entry constructs
 * {@code AllayLevelDBWorldStorage} for the path provided when the function is invoked, so loading
 * the factory table does not open world storage itself.
 *
 * @author daoge_cmd
 */
public class WorldStorageFactoryRegistryLoader implements RegistryLoader<Void, Map<String, Function<Path, WorldStorage>>> {
    /**
     * Creates the mutable storage-factory table containing the built-in LevelDB implementation.
     */
    @Override
    public Map<String, Function<Path, WorldStorage>> load(Void $) {
        var map = new HashMap<String, Function<Path, WorldStorage>>();
        map.put("LEVELDB", AllayLevelDBWorldStorage::new);
        return map;
    }
}
