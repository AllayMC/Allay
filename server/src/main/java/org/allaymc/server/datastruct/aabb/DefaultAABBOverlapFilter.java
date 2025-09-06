package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.AABBOverlapFilter;
import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;

/**
 * @author daoge_cmd
 */
public class DefaultAABBOverlapFilter<T extends HasAABB & HasLongId> implements AABBOverlapFilter<T> {
    @Override
    public boolean test(T t) {
        return true;
    }
}
