package org.allaymc.server.datastruct.aabb;

import lombok.AllArgsConstructor;
import org.allaymc.api.world.service.HasLongId;

import java.util.Objects;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
final class AABBTreeObject<E extends HasLongId> {
    private final E data;

    public static <E extends HasLongId> AABBTreeObject<E> create(E data) {
        return new AABBTreeObject<>(data);
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(data.getLongId());
    }

    public E getData() {
        return data;
    }
}
