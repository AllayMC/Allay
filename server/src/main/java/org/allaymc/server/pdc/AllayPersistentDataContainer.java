package org.allaymc.server.pdc;

import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.pdc.PersistentDataType;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    public void readFromBytes(byte[] bytes, boolean clear) throws IOException {
        if (clear) {
            this.clear();
        }

        try (var reader = NbtUtils.createReader(new ByteArrayInputStream(bytes))) {
            this.customDataTags.putAll((NbtMap) reader.readTag());
        }
    }

    @Override
    public Object getRawValue(String key) {
        return this.customDataTags.get(key);
    }

    @Override
    public NbtMap toCompoundTag() {
        return NbtMap.fromMap(this.customDataTags);
    }

    public void clear() {
        this.customDataTags.clear();
    }

    public void put(String key, Object value) {
        this.customDataTags.put(key, value);
    }

    public void putAll(Map<String, Object> map) {
        this.customDataTags.putAll(map);
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
