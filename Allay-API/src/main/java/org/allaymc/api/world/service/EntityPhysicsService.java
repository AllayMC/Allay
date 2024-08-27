package org.allaymc.api.world.service;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.joml.primitives.AABBfc;

import java.util.List;

/**
 * Allay Project 2023/8/3
 *
 * @author daoge_cmd
 */
public interface EntityPhysicsService {
    boolean containEntity(Entity entity);

    default List<Entity> computeCollidingEntities(Entity entity) {
        return computeCollidingEntities(entity.getOffsetAABB(), other -> other.getRuntimeId() != entity.getRuntimeId());
    }

    default List<Entity> computeCollidingEntities(AABBfc aabb) {
        return computeCollidingEntities(aabb, entity -> true);
    }

    default List<Entity> computeCollidingEntities(Entity entity, boolean ignoreEntityHasCollision) {
        return computeCollidingEntities(entity.getOffsetAABB(), other -> other.getRuntimeId() != entity.getRuntimeId() && (ignoreEntityHasCollision || entity.hasEntityCollision()));
    }

    default List<Entity> computeCollidingEntities(AABBfc aabb, boolean ignoreEntityHasCollision) {
        return computeCollidingEntities(aabb, entity -> ignoreEntityHasCollision || entity.hasEntityCollision());
    }

    List<Entity> computeCollidingEntities(AABBfc aabb, AABBOverlapFilter<Entity> predicate);

    default List<Entity> computeCollidingEntities(VoxelShape voxelShape) {
        return computeCollidingEntities(voxelShape, false);
    }

    List<Entity> computeCollidingEntities(VoxelShape voxelShape, boolean ignoreEntityHasCollision);

    default List<Entity> getCachedEntityCollidingResult(Entity entity) {
        return getCachedEntityCollidingResult(entity, false);
    }

    List<Entity> getCachedEntityCollidingResult(Entity entity, boolean ignoreEntityHasCollision);
}
