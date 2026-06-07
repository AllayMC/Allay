package org.allaymc.server.world.biome;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.config.Config;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages custom biome ID allocation and persistence.
 * <p>
 * Custom biome IDs start from {@value #CUSTOM_BIOME_ID_START} and are persisted
 * to {@code biome_ids.yml} in the server's running directory. Once an ID is
 * allocated to a biome identifier, it remains fixed across server restarts.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class CustomBiomeIdAllocator {

    public static final int CUSTOM_BIOME_ID_START = 30000;

    private static final String FILE_NAME = "biome-ids.yml";

    private static CustomBiomeIdAllocator instance;

    private final Config config;
    private final Map<String, Integer> identifierToId;
    private final AtomicInteger nextId;

    private CustomBiomeIdAllocator() {
        this.config = new Config(FILE_NAME, Config.YAML);
        this.identifierToId = new HashMap<>();

        int maxId = CUSTOM_BIOME_ID_START - 1;
        for (var entry : config.getAll().entrySet()) {
            var id = ((Number) entry.getValue()).intValue();
            identifierToId.put(entry.getKey(), id);
            if (id > maxId) {
                maxId = id;
            }
        }

        this.nextId = new AtomicInteger(maxId + 1);
    }

    /**
     * Initialize the singleton instance. Must be called during server startup
     * after the biome registry is created.
     */
    public static void init() {
        instance = new CustomBiomeIdAllocator();
    }

    /**
     * Get the singleton instance.
     *
     * @return the allocator instance
     *
     * @throws IllegalStateException if not initialized
     */
    public static CustomBiomeIdAllocator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CustomBiomeIdAllocator has not been initialized");
        }
        return instance;
    }

    /**
     * Allocate an ID for the given biome identifier.
     * <p>
     * If the identifier was previously allocated an ID (persisted in {@code biome_ids.yml}),
     * the same ID is returned. Otherwise, a new ID is allocated and persisted.
     *
     * @param identifier the biome identifier
     *
     * @return the allocated ID
     */
    public synchronized int allocateId(Identifier identifier) {
        var key = identifier.toString();
        var existing = identifierToId.get(key);
        if (existing != null) {
            return existing;
        }

        var id = nextId.getAndIncrement();
        identifierToId.put(key, id);
        config.set(key, id);
        config.save();
        return id;
    }
}
