package org.allaymc.api.world.physics;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.joml.primitives.AABBdc;

import java.util.List;

/**
 * {@code EntityPhysicsEngine} exposes entity overlap queries and collision
 * results cached by physics processing. Query overloads select different
 * self-exclusion and collision-flag rules, while cached results describe the
 * most recent collision pass rather than recomputing current overlaps.
 *
 * @author daoge_cmd
 */
public interface EntityPhysicsEngine {

    /**
     * Finds overlapping entities excluding the queried entity by runtime
     * identifier. This overload applies no candidate collision-flag filter, so
     * entities with collision disabled can still appear in the returned list.
     */
    default List<Entity> computeCollidingEntities(Entity entity) {
        return computeCollidingEntities(entity.getOffsetAABB(), other -> other.getRuntimeId() != entity.getRuntimeId());
    }

    /**
     * Finds indexed entities whose world-space bounds overlap {@code aabb}.
     * This overload accepts every candidate regardless of its entity-collision
     * flag and performs no self-exclusion for a particular source entity.
     */
    default List<Entity> computeCollidingEntities(AABBdc aabb) {
        return computeCollidingEntities(aabb, entity -> true);
    }

    /**
     * Finds overlaps excluding the source runtime identifier and optionally
     * checking source collision eligibility. When {@code
     * ignoreEntityHasCollision} is {@code false}, this overload checks {@code
     * entity.hasEntityCollision()} rather than each candidate collision flag.
     */
    default List<Entity> computeCollidingEntities(Entity entity, boolean ignoreEntityHasCollision) {
        return computeCollidingEntities(entity.getOffsetAABB(), other -> other.getRuntimeId() != entity.getRuntimeId() && (ignoreEntityHasCollision || entity.hasEntityCollision()));
    }

    /**
     * Finds entities overlapping {@code aabb} and optionally filters each
     * candidate collision flag. Passing {@code true} includes
     * collision-disabled candidates, while {@code false} retains only
     * candidates whose {@code hasEntityCollision()} returns {@code true}.
     */
    default List<Entity> computeCollidingEntities(AABBdc aabb, boolean ignoreEntityHasCollision) {
        return computeCollidingEntities(aabb, entity -> ignoreEntityHasCollision || entity.hasEntityCollision());
    }

    /**
     * Finds indexed entities overlapping {@code aabb} that also pass {@code
     * predicate}. The predicate supplies all entity-specific filtering for
     * this query, including any desired source exclusion or entity-collision
     * flag checks.
     */
    List<Entity> computeCollidingEntities(AABBdc aabb, AABBOverlapFilter<Entity> predicate);

    /**
     * Finds entities whose world-space bounds intersect the supplied voxel
     * shape. This overload applies candidate entity-collision eligibility by
     * delegating with {@code ignoreEntityHasCollision} set to {@code false}.
     */
    default List<Entity> computeCollidingEntities(VoxelShape voxelShape) {
        return computeCollidingEntities(voxelShape, false);
    }

    /**
     * Finds entities whose world-space bounds intersect the supplied voxel
     * shape. Passing {@code ignoreEntityHasCollision} as {@code true} includes
     * candidates whose entity-collision flag would otherwise exclude them from
     * the result.
     */
    List<Entity> computeCollidingEntities(VoxelShape voxelShape, boolean ignoreEntityHasCollision);

    /**
     * Returns collision-enabled candidates from the most recent cached
     * collision pass. This overload uses {@code ignoreEntityHasCollision} set
     * to {@code false}; callers must treat the returned collection as
     * engine-owned storage.
     */
    default List<Entity> getCachedEntityCollidingResult(Entity entity) {
        return getCachedEntityCollidingResult(entity, false);
    }

    /**
     * Returns the most recently cached overlaps for an entity with collision
     * enabled. Collision-disabled source entities return an empty list even
     * when {@code ignoreEntityHasCollision} is {@code true}; the server may
     * filter its shared cached list in place.
     */
    List<Entity> getCachedEntityCollidingResult(Entity entity, boolean ignoreEntityHasCollision);
}
