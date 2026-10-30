package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;

import java.util.Objects;

/**
 * {@code CollisionPair} represents two potentially colliding objects using unordered long
 * identifiers for equality and hashing. The tree uses these values to suppress duplicate
 * pair results found from opposite traversal directions. Endpoint identifiers must be
 * stable, and valid tree pairs contain distinct identifiers.
 * @param objectA first retained collision object
 * @param objectB second retained collision object
 * @author daoge_cmd
 */
public record CollisionPair<T extends HasAABB & HasLongId>(T objectA, T objectB) {

    /**
     * Returns whether both endpoint identifiers occur in the other pair. For pairs with
     * distinct identifiers this comparison ignores endpoint order; degenerate
     * duplicate-identifier pairs do not receive additional normalization or validation.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollisionPair<?> that = (CollisionPair<?>) o;
        return (Objects.equals(objectA.getLongId(), that.objectA.getLongId()) || Objects.equals(objectA.getLongId(), that.objectB.getLongId())) &&
               (Objects.equals(objectB.getLongId(), that.objectB.getLongId()) || Objects.equals(objectB.getLongId(), that.objectA.getLongId()));
    }

    /**
     * Returns an order-independent hash of the two current endpoint identifiers.
     */
    @Override
    public int hashCode() {
        return Objects.hash(objectA.getLongId(), objectB.getLongId()) ^ Objects.hash(objectB.getLongId(), objectA.getLongId());
    }
}
