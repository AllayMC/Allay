package org.allaymc.api.world.service;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.jetbrains.annotations.ApiStatus;
import org.joml.primitives.AABBfc;

import java.util.List;

/**
 * Allay Project 2023/8/3
 *
 * @author daoge_cmd
 */
public interface EntityPhysicsService {

    void tick();

    /**
     * Please call it before run tick()!
     */
    @ApiStatus.Internal
    void addEntity(Entity entity);

    /**
     * Please call it before run tick()!
     */
    @ApiStatus.Internal
    void removeEntity(Entity entity);

    boolean containEntity(Entity entity);

    void offerScheduledMove(Entity entity, Location3fc newLoc);

    default List<Entity> computeCollidingEntities(Entity entity, boolean ignoreEntityHasCollision) {
        var entities = computeCollidingEntities(entity.getOffsetAABB(), ignoreEntityHasCollision);
        entities.removeIf(e -> e.getUniqueId() == entity.getUniqueId());
        return entities;
    }

    default List<Entity> computeCollidingEntities(Entity entity) {
        return computeCollidingEntities(entity, false);
    }

    default List<Entity> computeCollidingEntities(AABBfc aabb) {
        return computeCollidingEntities(aabb, false);
    }

    List<Entity> computeCollidingEntities(AABBfc aabb, boolean ignoreEntityHasCollision);

    default List<Entity> computeCollidingEntities(VoxelShape voxelShape) {
        return computeCollidingEntities(voxelShape, false);
    }

    List<Entity> computeCollidingEntities(VoxelShape voxelShape, boolean ignoreEntityHasCollision);

    default List<Entity> getCachedEntityCollidingResult(Entity entity) {
        return getCachedEntityCollidingResult(entity, false);
    }

    List<Entity> getCachedEntityCollidingResult(Entity entity, boolean ignoreEntityHasCollision);
}
