package org.allaymc.server.datastruct.aabb;

import org.allaymc.api.world.service.HasAABB;
import org.allaymc.api.world.service.HasLongId;

import java.util.function.BiPredicate;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface CollisionFilter<T extends HasAABB & HasLongId> extends BiPredicate<T, T> {
}
