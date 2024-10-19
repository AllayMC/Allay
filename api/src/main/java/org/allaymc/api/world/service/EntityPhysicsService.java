package org.allaymc.api.world.service;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.joml.primitives.AABBfc;

import java.util.List;

/**
 * EntityPhysicsService is responsible for handling entity physics.
 *
 * @author daoge_cmd
 */
public interface EntityPhysicsService {

    /**
     * Compute the entities that are colliding with the specified entity.
     *
     * @param entity The entity to compute the colliding entities.
     *
     * @return The entities that are colliding with the specified entity.
     */
    default List<Entity> computeCollidingEntities(Entity entity) {
        return computeCollidingEntities(entity.getOffsetAABB(), other -> other.getRuntimeId() != entity.getRuntimeId());
    }

    /**
     * Compute the entities that are colliding with the specified aabb.
     *
     * @param aabb The aabb to compute the colliding entities.
     *
     * @return The entities that are colliding with the specified aabb.
     */
    default List<Entity> computeCollidingEntities(AABBfc aabb) {
        return computeCollidingEntities(aabb, entity -> true);
    }

    /**
     * Compute the entities that are colliding with the specified entity.
     *
     * @param entity                   The entity to compute the colliding entities.
     * @param ignoreEntityHasCollision Whether to ignore the entity's "hasCollision" property.
     *
     * @return The entities that are colliding with the specified entity.
     */
    default List<Entity> computeCollidingEntities(Entity entity, boolean ignoreEntityHasCollision) {
        return computeCollidingEntities(entity.getOffsetAABB(), other -> other.getRuntimeId() != entity.getRuntimeId() && (ignoreEntityHasCollision || entity.hasEntityCollision()));
    }

    /**
     * Compute the entities that are colliding with the specified aabb.
     *
     * @param aabb                     The entity to compute the colliding entities.
     * @param ignoreEntityHasCollision Whether to ignore the entity's "hasCollision" property.
     *
     * @return The entities that are colliding with the specified aabb.
     */
    default List<Entity> computeCollidingEntities(AABBfc aabb, boolean ignoreEntityHasCollision) {
        return computeCollidingEntities(aabb, entity -> ignoreEntityHasCollision || entity.hasEntityCollision());
    }

    /**
     * Compute the entities that are colliding with the specified aabb.
     *
     * @param aabb      The entity to compute the colliding entities.
     * @param predicate The predicate to filter the entities.
     *
     * @return The entities that are colliding with the specified aabb and pass the predicate.
     */
    List<Entity> computeCollidingEntities(AABBfc aabb, AABBOverlapFilter<Entity> predicate);

    default List<Entity> computeCollidingEntities(VoxelShape voxelShape) {
        return computeCollidingEntities(voxelShape, false);
    }

    /**
     * Compute the entities that are colliding with the specified voxel shape.
     *
     * @param voxelShape               The voxel shape to compute the colliding entities.
     * @param ignoreEntityHasCollision Whether to ignore the entity's has collision.
     *
     * @return The entities that are colliding with the specified voxel shape.
     */
    List<Entity> computeCollidingEntities(VoxelShape voxelShape, boolean ignoreEntityHasCollision);

    /**
     * Get the cached entity colliding result of the specified entity.
     *
     * @param entity The entity to get the cached entity colliding result.
     *
     * @return The cached entity colliding result of the specified entity.
     */
    default List<Entity> getCachedEntityCollidingResult(Entity entity) {
        return getCachedEntityCollidingResult(entity, false);
    }

    /**
     * Get the cached entity colliding result of the specified entity.
     *
     * @param entity                   The entity to get the cached entity colliding result.
     * @param ignoreEntityHasCollision Whether to ignore the entity's "hasCollision" property.
     *
     * @return The cached entity colliding result of the specified entity.
     */
    List<Entity> getCachedEntityCollidingResult(Entity entity, boolean ignoreEntityHasCollision);
}
