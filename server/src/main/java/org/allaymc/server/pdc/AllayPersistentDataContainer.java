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
 * @author IWareQ
 */
public class AllayPersistentDataContainer extends AllayPersistentDataContainerView implements PersistentDataContainer {
    private final Map<String, Object> customDataTags = new HashMap<>();

    public AllayPersistentDataContainer(PersistentDataTypeRegistry registry) {
        super(registry);
    }

    @Override
    public <P, C> void set(Identifier key, PersistentDataType<P, C> type, C value) {
        this.customDataTags.put(key.toString(), this.registry.wrap(type, type.toPrimitive(value, this.adapterContext)));
    }

    @Override
    public void remove(Identifier key) {
        this.customDataTags.remove(key.toString());
    }

    @Override
    public void putAll(Map<String, Object> map) {
        this.customDataTags.putAll(map);
    }

    @Override
    public void clear() {
        this.customDataTags.clear();
    }

    @Override
    public Object getRawValue(String key) {
        return this.customDataTags.get(key);
    }

    @Override
    public boolean isEmpty() {
        return customDataTags.isEmpty();
    }

    @Override
    public NbtMap toNbt() {
        return NbtMap.fromMap(this.customDataTags);
    }

    public Map<String, Object> getRaw() {
        return this.customDataTags;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AllayPersistentDataContainer that)) return false;
        return Objects.equals(customDataTags, that.customDataTags);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(customDataTags);
    }
}
