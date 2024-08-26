package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.service.HasAABB;
import org.allaymc.api.world.service.HasLongId;

/**
 * Allay Project 2023/7/30
 *
 * @author daoge_cmd
 */
public class DefaultCollisionFilter<T extends HasAABB & HasLongId> implements CollisionFilter<T> {
    @Override
    public boolean test(T t, T t2) {
        return true;
    }
}
