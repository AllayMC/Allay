package org.allaymc.server.world.dimension;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.config.Config;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages custom dimension id allocation and persistence.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class CustomDimensionIdAllocator {

    public static final int CUSTOM_DIMENSION_ID_START = 1000;

    private static final String FILE_NAME = "dimension-ids.yml";

    private static CustomDimensionIdAllocator instance;

    private final Config config;
    private final Map<String, Integer> identifierToId;
    private final AtomicInteger nextId;

    private CustomDimensionIdAllocator() {
        this.config = new Config(FILE_NAME, Config.YAML);
        this.identifierToId = new HashMap<>();

        int maxId = CUSTOM_DIMENSION_ID_START - 1;
        for (var entry : config.getAll().entrySet()) {
            var id = ((Number) entry.getValue()).intValue();
            identifierToId.put(entry.getKey(), id);
            if (id > maxId) {
                maxId = id;
            }
        }

        this.nextId = new AtomicInteger(maxId + 1);
    }

    public static void init() {
        instance = new CustomDimensionIdAllocator();
    }

    public static CustomDimensionIdAllocator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CustomDimensionIdAllocator has not been initialized");
        }
        return instance;
    }

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
