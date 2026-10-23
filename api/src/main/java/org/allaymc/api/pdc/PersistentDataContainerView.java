package org.allaymc.api.pdc;

import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Set;

/**
 * {@code PersistentDataContainerView} exposes typed reads of custom persistent metadata stored
 * under namespaced identifiers. The interface offers no mutation operation, but its implementation
 * may share storage with a mutable container; a view alone does not imply a snapshot or deeply
 * immutable returned values.
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataContainerView {
    /**
     * Returns whether a present tag matches the supplied primitive adapter. The check does not
     * decode the complex value, and list adapters can accept the list container without checking
     * its element representation.
     *
     * @throws IllegalArgumentException if a present value requires an unregistered primitive adapter
     */
    <P, C> boolean has(Identifier key, PersistentDataType<P, C> type);

    /**
     * Returns whether a non-{@code null} raw value exists under the supplied identifier.
     */
    boolean has(Identifier key);

    /**
     * Decodes the value under {@code key}, returning {@code null} when the entry is absent. A
     * present incompatible tag raises a conversion error rather than being treated as absent, and
     * returned arrays or other mutable values can share storage.
     *
     * @throws IllegalArgumentException if the primitive adapter is missing or the stored representation is incompatible
     */
    <P, C> C get(Identifier key, PersistentDataType<P, C> type);

    /**
     * Returns the decoded value or {@code defaultValue} when decoding returns {@code null}. The
     * fallback does not suppress an incompatible present tag or another conversion failure, and
     * the
     * supplied default is returned without copying.
     */
    <P, C> C getOrDefault(Identifier key, PersistentDataType<P, C> type, C defaultValue);

    /**
     * Returns an unmodifiable snapshot of the stored identifiers. Changes to the backing container
     * after this call do not update this set; malformed raw key strings can fail while identifiers
     * are constructed.
     */
    Set<Identifier> getKeys();

    /**
     * Returns whether this view currently contains no stored metadata entries.
     */
    boolean isEmpty();

    /**
     * Returns the associated context for constructing compatible nested data containers.
     */
    PersistentDataAdapterContext getAdapterContext();

    /**
     * Returns the raw NBT representation of the custom entries. Implementations may return a map
     * backed by mutable container storage, so callers must not assume that retaining this result
     * captures a stable snapshot.
     */
    NbtMap toNbt();
}
