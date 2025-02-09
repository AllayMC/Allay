package org.allaymc.server.pdc;

import lombok.Getter;
import org.allaymc.api.pdc.PersistentDataAdapterContext;
import org.allaymc.api.pdc.PersistentDataContainerView;
import org.allaymc.api.pdc.PersistentDataType;
import org.allaymc.api.pdc.PersistentDataTypeRegistry;
import org.allaymc.api.utils.Identifier;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author IWareQ
 */
@Getter
public abstract class AllayPersistentDataContainerView implements PersistentDataContainerView {
    protected final PersistentDataTypeRegistry registry;
    protected final PersistentDataAdapterContext adapterContext;

    public AllayPersistentDataContainerView(PersistentDataTypeRegistry registry) {
        this.registry = registry;
        this.adapterContext = new AllayPersistentDataAdapterContext(registry);
    }

    public abstract Object getRawValue(String key);

    @Override
    public <P, C> boolean has(Identifier key, PersistentDataType<P, C> type) {
        var value = this.getRawValue(key.toString());
        if (value == null) {
            return false;
        }

        return this.registry.isInstanceOf(type, value);
    }

    @Override
    public boolean has(Identifier key) {
        return this.getRawValue(key.toString()) != null;
    }

    @Override
    public <P, C> C get(Identifier key, PersistentDataType<P, C> type) {
        var value = this.getRawValue(key.toString());
        if (value == null) {
            return null;
        }

        return type.fromPrimitive(this.registry.extract(type, value), this.adapterContext);
    }

    @Override
    public <P, C> C getOrDefault(Identifier key, PersistentDataType<P, C> type, C defaultValue) {
        var value = this.get(key, type);
        return value != null ? value : defaultValue;
    }

    @Override
    public Set<Identifier> getKeys() {
        var names = this.toNbt().keySet();
        return names.stream().map(Identifier::new).collect(Collectors.toUnmodifiableSet());
    }
}
