package cn.allay.server.world.entity;

import cn.allay.api.datastruct.aabbtree.AABBTree;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.entity.Entity;
import cn.allay.api.math.Location3dc;
import cn.allay.api.world.entity.EntityPhysicsService;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Allay Project 2023/8/5
 *
 * @author daoge_cmd
 */
public class AllayEntityPhysicsService implements EntityPhysicsService {
    protected Map<Long, Entity> entities = new Long2ObjectNonBlockingMap<>();
    protected Map<Long, Queue<ScheduledMove>> scheduledMoveQueue = new Long2ObjectNonBlockingMap<>();
    protected Map<Long, Set<Long>> entityCollisionMap = new Long2ObjectNonBlockingMap<>();
    protected AABBTree<Entity> entityAABBTree = new AABBTree<>();

    @Override
    public void tick() {

    }

    @Override
    public void updateEntity(Entity entity) {
        if (!entities.containsKey(entity.getUniqueId()))
            throw new IllegalArgumentException("Entity " + entity.getUniqueId() + " is not registered in this service");
        entityAABBTree.update(entity);
    }

    @Override
    public void addEntity(Entity entity) {
        if (entities.containsKey(entity.getUniqueId()))
            throw new IllegalArgumentException("Entity " + entity.getUniqueId() + " is already registered in this service");
        entities.put(entity.getUniqueId(), entity);
        entityAABBTree.add(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        if (!entities.containsKey(entity.getUniqueId()))
            throw new IllegalArgumentException("Entity " + entity.getUniqueId() + " is not registered in this service");
        entities.remove(entity.getUniqueId());
        entityAABBTree.remove(entity);
    }

    @Override
    public boolean containEntity(Entity entity) {
        return entities.containsKey(entity.getUniqueId());
    }

    @Override
    public void offerScheduledMove(Entity entity, Location3dc newLoc) {
        if (!entities.containsKey(entity.getUniqueId()))
            throw new IllegalArgumentException("Entity " + entity.getUniqueId() + " is not registered in this service");
        scheduledMoveQueue.computeIfAbsent(entity.getUniqueId(), k -> new ConcurrentLinkedQueue<>()).offer(new ScheduledMove(entity, newLoc));
    }

    @Override
    public List<Entity> getCollidingEntities(AABBdc aabb) {
        var result = new ArrayList<Entity>();
        return entityAABBTree.detectOverlaps(aabb, result);
    }

    protected record ScheduledMove(Entity entity, Location3dc newLoc) {};
}
