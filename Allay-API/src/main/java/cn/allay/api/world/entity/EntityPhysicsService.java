package cn.allay.api.world.entity;

import cn.allay.api.entity.Entity;
import cn.allay.api.math.location.Location3dc;
import org.joml.primitives.AABBdc;

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

    void offerScheduledMove(Entity entity, Location3dc newLoc);

    default List<Entity> computeCollidingEntities(Entity entity) {
        if (entity.hasCollision()) {
            var entities = computeCollidingEntities(entity.getOffsetAABB());
            entities.removeIf(e -> e.getUniqueId() == entity.getUniqueId());
            return entities;
        } else return Collections.emptyList();
    }

    List<Entity> computeCollidingEntities(AABBdc aabb);

    List<Entity> getCachedEntityCollidingResult(Entity entity);
}
