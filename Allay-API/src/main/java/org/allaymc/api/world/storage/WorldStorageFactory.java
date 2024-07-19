package org.allaymc.api.world.storage;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.registry.AbstractMappedRegistry;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;

/**
 * Allay Project 2024/2/3
 *
 * @author daoge_cmd
 */
public interface WorldStorageFactory extends AbstractMappedRegistry<String, Function<Path, WorldStorage>, Map<String, Function<Path, WorldStorage>>> {
    ApiInstanceHolder<WorldStorageFactory> FACTORY = ApiInstanceHolder.create();

    static WorldStorageFactory getFactory() {
        return FACTORY.get();
    }

    default WorldStorage createWorldStorage(String type, Path path) {
        return this.get(type).apply(path);
    }
}
