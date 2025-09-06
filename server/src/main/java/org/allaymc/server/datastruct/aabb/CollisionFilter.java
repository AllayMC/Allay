package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;

import java.util.function.BiPredicate;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface CollisionFilter<T extends HasAABB & HasLongId> extends BiPredicate<T, T> {
}
