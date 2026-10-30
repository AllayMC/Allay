package org.allaymc.server.pdc;

import lombok.Getter;
import org.allaymc.api.pdc.PersistentDataAdapterContext;
import org.allaymc.api.pdc.PersistentDataContainerView;
import org.allaymc.api.pdc.PersistentDataType;
import org.allaymc.api.pdc.PersistentDataTypeRegistry;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@code AllayPersistentDataContainerView} decodes custom metadata supplied by a subclass's raw
 * value lookup and NBT representation. A retained registry selects primitive adapters, and the
 * associated context creates compatible nested containers; typed reads distinguish missing
 * entries from present representations that fail conversion.
 *
 * @author IWareQ
 */
@Getter
public abstract class AllayPersistentDataContainerView implements PersistentDataContainerView {
    /**
     * Registry used for primitive conversions.
     */
    protected final PersistentDataTypeRegistry registry;
    /**
     * Context for compatible nested containers.
     */
    protected final PersistentDataAdapterContext adapterContext;

    /**
     * Retains the adapter registry and creates its associated nested-container context.
     */
    public AllayPersistentDataContainerView(PersistentDataTypeRegistry registry) {
        this.registry = registry;
        this.adapterContext = new AllayPersistentDataAdapterContext(registry);
    }

    /**
     * Returns the stored representation for a raw string key. Subclasses return {@code null} for
     * an absent value, and mutable returned values may remain shared with the backing storage
     * used by this view.
     */
    public abstract Object getRawValue(String key);

    /**
     * Returns whether a present raw value matches {@code type}'s registry adapter. Missing entries
     * return {@code false} before adapter lookup; a {@code null} type therefore fails only when
     * a non-{@code null} raw value is found.
     */
    @Override
    public <P, C> boolean has(Identifier key, PersistentDataType<P, C> type) {
        var value = this.getRawValue(key.toString());
        if (value == null) {
            return false;
        }

        return this.registry.isInstanceOf(type, value);
    }

    /**
     * Returns whether the identifier selects a non-{@code null} raw stored value.
     */
    @Override
    public boolean has(Identifier key) {
        return this.getRawValue(key.toString()) != null;
    }

    /**
     * Decodes a present raw value with the selected adapter and complex converter. Missing entries
     * return {@code null} before type lookup, while incompatible present values propagate
     * conversion
     * failures rather than being treated as missing.
     */
    @Override
    public <P, C> C get(Identifier key, PersistentDataType<P, C> type) {
        var value = this.getRawValue(key.toString());
        if (value == null) {
            return null;
        }

        return type.fromPrimitive(this.registry.extract(type, value), this.adapterContext);
    }

    /**
     * Returns the decoded value, substituting {@code defaultValue} only for {@code null}. A
     * present
     * value whose conversion fails still raises that failure, and the fallback reference is
     * returned
     * without being copied.
     */
    @Override
    public <P, C> C getOrDefault(Identifier key, PersistentDataType<P, C> type, C defaultValue) {
        var value = this.get(key, type);
        return value != null ? value : defaultValue;
    }

    /**
     * Builds an unmodifiable identifier set from the current NBT keys. The set is independent of
     * later key changes, but invalid raw names cause identifier construction to fail during the
     * collection operation.
     */
    @Override
    public Set<Identifier> getKeys() {
        var names = this.toNbt().keySet();
        return names.stream().map(Identifier::new).collect(Collectors.toUnmodifiableSet());
    }
}
