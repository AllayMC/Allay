package org.allaymc.server.entity.ai.memory;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.ai.memory.MemoryStorage;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap-backed implementation of {@link MemoryStorage}.
 *
 * @author daoge_cmd
 */
public class SimpleMemoryStorage implements MemoryStorage {

    private static final Object EMPTY_VALUE = new Object();

    private final ConcurrentHashMap<MemoryType<?>, Object> storage = new ConcurrentHashMap<>();

    @Getter
    @Setter
    private Entity entity;

    public SimpleMemoryStorage() {
    }

    public SimpleMemoryStorage(Entity entity) {
        this.entity = entity;
    }

    @Override
    public <Data> void put(MemoryType<Data> type, Data value) {
        if (value == null) {
            storage.put(type, EMPTY_VALUE);
        } else {
            storage.put(type, value);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Data> Data get(MemoryType<Data> type) {
        var value = storage.get(type);
        if (value == null) {
            // Not present in map, return default
            var defaultData = type.defaultData().get();
            if (defaultData != null) {
                storage.put(type, defaultData);
            }
            return defaultData;
        }
        if (value == EMPTY_VALUE) {
            return null;
        }
        return (Data) value;
    }

    @Override
    @UnmodifiableView
    public Map<MemoryType<?>, Object> getAll() {
        return Collections.unmodifiableMap(storage);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public <Data> boolean compareDataTo(MemoryType<Data> type, Data value) {
        return Objects.equals(get(type), value);
    }
}
