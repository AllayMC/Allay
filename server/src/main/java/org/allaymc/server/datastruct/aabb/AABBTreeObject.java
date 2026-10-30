package org.allaymc.server.datastruct.aabb;

import lombok.AllArgsConstructor;
import org.allaymc.api.world.physics.HasLongId;

import java.util.Objects;

/**
 * {@code AABBTreeObject} represents a tree map key whose identity is the wrapped object's
 * long identifier. Different object instances with the same identifier address the same
 * tree entry. The identifier must remain stable while the key is stored.
 * @author daoge_cmd
 */
@AllArgsConstructor
final class AABBTreeObject<E extends HasLongId> {
    /**
     * {@code data} retains the original object used by this key. Equality and hashing read
     * its current long identifier, so identifier mutation can invalidate map lookup.
     */
    private final E data;

    /**
     * Returns a new key retaining {@code data} and using its long identifier. The object
     * must be non-null when equality or hashing accesses it, and its identifier must
     * remain stable while stored.
     */
    public static <E extends HasLongId> AABBTreeObject<E> create(E data) {
        return new AABBTreeObject<>(data);
    }

    /**
     * Returns whether another wrapper has the same current long identifier. The wrapped
     * objects need not be identical or equal; only their identifier values participate in
     * this key comparison.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AABBTreeObject<?> that = (AABBTreeObject<?>) o;
        return Objects.equals(data.getLongId(), that.data.getLongId());
    }

    /**
     * Returns a hash derived from the wrapped object's current long identifier.
     */
    @Override
    public int hashCode() {
        return Objects.hash(data.getLongId());
    }

    /**
     * Returns the retained object represented by this identifier-based tree key.
     */
    public E getData() {
        return data;
    }
}
