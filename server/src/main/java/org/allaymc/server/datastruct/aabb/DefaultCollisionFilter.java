package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;

/**
 * @author daoge_cmd
 */
public class DefaultCollisionFilter<T extends HasAABB & HasLongId> implements CollisionFilter<T> {
    @Override
    public boolean test(T t, T t2) {
        return true;
    }
}
