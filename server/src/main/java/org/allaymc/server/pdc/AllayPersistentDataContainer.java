package org.allaymc.server.pdc;

import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.pdc.PersistentDataType;
import org.allaymc.api.pdc.PersistentDataTypeRegistry;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.nbt.NbtMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * {@code AllayPersistentDataContainer} stores encoded custom metadata in a mutable string-keyed
 * map. Typed writes use the shared registry, while raw insertion bypasses conversion; arrays,
 * raw values, and NBT map views can retain shared storage, so callers coordinate access and
 * copy data when they need independent values.
 *
 * @author IWareQ
 */
public class AllayPersistentDataContainer extends AllayPersistentDataContainerView implements PersistentDataContainer {
    /**
     * {@code customDataTags} owns the entry map. {@code getRaw} exposes it directly, while
     * {@code toNbt} wraps the same storage in an unmodifiable NBT view.
     */
    private final Map<String, Object> customDataTags = new HashMap<>();

    /**
     * Creates an empty custom metadata map using the supplied adapter registry.
     */
    public AllayPersistentDataContainer(PersistentDataTypeRegistry registry) {
        super(registry);
    }

    /**
     * Converts {@code value} and replaces the encoded entry under {@code key}. Conversion finishes
     * before the map changes, but primitive adapters can retain mutable references rather than
     * making independent stored copies.
     */
    @Override
    public <P, C> void set(Identifier key, PersistentDataType<P, C> type, C value) {
        this.customDataTags.put(key.toString(), this.registry.wrap(type, type.toPrimitive(value, this.adapterContext)));
    }

    /**
     * Removes the string-keyed metadata entry selected by the supplied identifier.
     */
    @Override
    public void remove(Identifier key) {
        this.customDataTags.remove(key.toString());
    }

    /**
     * Copies raw entries into {@code customDataTags}, overwriting matching keys. The method
     * bypasses
     * identifier and NBT-type validation, and it retains mutable entry values rather than copying
     * their contents.
     */
    @Override
    public void putAll(Map<String, Object> map) {
        this.customDataTags.putAll(map);
    }

    /**
     * Removes every custom metadata entry from the retained backing map.
     */
    @Override
    public void clear() {
        this.customDataTags.clear();
    }

    /**
     * Returns the raw stored reference for {@code key}, or {@code null} when no non-{@code null} value
     * exists. The result bypasses converter checks, and callers can mutate shared arrays through
     * that reference.
     */
    @Override
    public Object getRawValue(String key) {
        return this.customDataTags.get(key);
    }

    /**
     * Returns whether the mutable backing map currently contains no entries.
     */
    @Override
    public boolean isEmpty() {
        return customDataTags.isEmpty();
    }

    /**
     * Returns an unmodifiable NBT map backed by {@code customDataTags}. Later entry changes remain
     * visible through the returned map, so retaining it does not capture an independent snapshot
     * of this container.
     */
    @Override
    public NbtMap toNbt() {
        return NbtMap.fromMap(this.customDataTags);
    }

    /**
     * Returns the mutable backing map used by this container. Changes through the result
     * immediately
     * affect container reads and bypass conversion validation, so callers must coordinate access
     * and preserve valid raw representations themselves.
     */
    public Map<String, Object> getRaw() {
        return this.customDataTags;
    }

    /**
     * Returns whether another Allay container has an equal raw entry map.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AllayPersistentDataContainer that)) return false;
        return Objects.equals(customDataTags, that.customDataTags);
    }

    /**
     * Returns the hash of the current raw metadata entry map.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(customDataTags);
    }
}
