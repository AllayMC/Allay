package org.allaymc.server.entity.ai.memory;

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
public class MemoryStorageImpl implements MemoryStorage {

    private static final Object EMPTY_VALUE = new Object();

    private final ConcurrentHashMap<MemoryType<?>, Object> storage = new ConcurrentHashMap<>();

    @Override
    public <T> void put(MemoryType<T> type, T value) {
        if (value == null) {
            storage.put(type, EMPTY_VALUE);
        } else {
            storage.put(type, value);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(MemoryType<T> type) {
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
        return (T) value;
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
    public <T> void clear(MemoryType<T> type) {
        storage.remove(type);
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public <T> boolean isEmpty(MemoryType<T> type) {
        var v = storage.get(type);
        return v == null || v == EMPTY_VALUE;
    }

    @Override
    public <T> boolean putIfAbsent(MemoryType<T> type, T value) {
        boolean[] inserted = {false};
        storage.compute(type, (k, existing) -> {
            if (existing == null || existing == EMPTY_VALUE) {
                inserted[0] = true;
                return value != null ? value : EMPTY_VALUE;
            }
            return existing;
        });
        return inserted[0];
    }

    @Override
    public <T> boolean compareDataTo(MemoryType<T> type, T value) {
        return Objects.equals(get(type), value);
    }
}
