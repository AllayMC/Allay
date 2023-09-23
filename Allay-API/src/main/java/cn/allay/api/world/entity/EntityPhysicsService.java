package cn.allay.api.world.entity;

import cn.allay.api.entity.Entity;
import cn.allay.api.math.location.Location3fc;
import cn.allay.api.math.voxelshape.VoxelShape;
import org.joml.primitives.AABBfc;

import java.util.Collections;
import java.util.List;

/**
 * Allay Project 2023/8/3
 *
 * @author daoge_cmd
 */
public interface EntityPhysicsService {

    void tick();

    void updateEntity(Entity entity);

    void addEntity(Entity entity);

    void removeEntity(Entity entity);

    boolean containEntity(Entity entity);

    void offerScheduledMove(Entity entity, Location3fc newLoc);

    default List<Entity> computeCollidingEntities(Entity entity) {
        if (entity.hasEntityCollision()) {
            var entities = computeCollidingEntities(entity.getOffsetAABB());
            entities.removeIf(e -> e.getUniqueId() == entity.getUniqueId());
            return entities;
        } else return Collections.emptyList();
    }

    List<Entity> computeCollidingEntities(AABBfc aabb);

    List<Entity> computeCollidingEntities(VoxelShape voxelShape);

    List<Entity> getCachedEntityCollidingResult(Entity entity);
}
