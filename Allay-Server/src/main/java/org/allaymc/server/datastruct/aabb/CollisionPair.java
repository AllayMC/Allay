package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.service.HasAABB;
import org.allaymc.api.world.service.HasLongId;

import java.util.Objects;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
public record CollisionPair<T extends HasAABB & HasLongId>(T objectA, T objectB) {

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

    @Override
    public int hashCode() {
        return Objects.hash(objectA.getLongId(), objectB.getLongId()) ^ Objects.hash(objectB.getLongId(), objectA.getLongId());
    }
}
