package cn.allay.server.world.entity;

import cn.allay.api.datastruct.aabbtree.AABBTree;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.entity.Entity;
import cn.allay.api.math.Location3dc;
import cn.allay.api.world.entity.EntityPhysicsService;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Allay Project 2023/8/5
 *
 * @author daoge_cmd
 */
public class AllayEntityPhysicsService implements EntityPhysicsService {

    public static final double DIFF_POSITION_THRESHOLD = 0.0001;
    public static final double DIFF_ROTATION_THRESHOLD = 0.1;

    protected Map<Long, Entity> entities = new Long2ObjectNonBlockingMap<>();
    protected Map<Long, Queue<ScheduledMove>> scheduledMoveQueue = new Long2ObjectNonBlockingMap<>();
    protected Map<Long, List<Entity>> entityCollisionCache = new Long2ObjectNonBlockingMap<>();
    protected AABBTree<Entity> entityAABBTree = new AABBTree<>();
    protected Queue<EntityUpdateOperation> entityUpdateOperationQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void tick() {
        handleEntityUpdateQueue();
        handleScheduledMoveQueue();
        computeEntityCollision();
        updateEntityMotion();
    }

    protected void updateEntityMotion() {

    }

    protected void computeEntityCollision() {
        entities.values().parallelStream().forEach(entity -> {
            var result = new ObjectArrayList<Entity>();
            entityAABBTree.detectOverlaps(entity.getOffsetAABB(), result);
            entityCollisionCache.put(entity.getUniqueId(), result);
        });
    }

    protected void handleScheduledMoveQueue() {
        for (var entry : scheduledMoveQueue.entrySet()) {
            var queue = entry.getValue();
            while (!queue.isEmpty()) {
                var scheduledMove = queue.poll();
                var entity = scheduledMove.entity;
                var newLoc = scheduledMove.newLoc;
                entity.broadcastMoveToViewers(computeMoveFlags(entity, entity.getLocation(), newLoc), newLoc);
                entity.setLocation(newLoc);
            }
        }
    }

    protected void handleEntityUpdateQueue() {
        while (!entityUpdateOperationQueue.isEmpty()) {
            var operation = entityUpdateOperationQueue.poll();
            var entity = operation.entity;
            switch (operation.type) {
                case ADD -> {
                    entities.put(entity.getUniqueId(), entity);
                    entityAABBTree.add(entity);
                }
                case REMOVE -> {
                    entities.remove(entity.getUniqueId());
                    entityAABBTree.remove(entity);
                    entityCollisionCache.remove(entity.getUniqueId());
                }
                case UPDATE -> entityAABBTree.update(entity);
            }
        }
    }

    protected Set<MoveEntityDeltaPacket.Flag> computeMoveFlags(Entity entity, Location3dc oldLoc, Location3dc newLoc) {
        var flags = EnumSet.noneOf(MoveEntityDeltaPacket.Flag.class);
        if (Math.abs(oldLoc.x() - newLoc.x()) > DIFF_POSITION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_X);
        if (Math.abs(oldLoc.y() - newLoc.y()) > DIFF_POSITION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_Y);
        if (Math.abs(oldLoc.z() - newLoc.z()) > DIFF_POSITION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_Z);
        if (Math.abs(oldLoc.yaw() - newLoc.yaw()) > DIFF_ROTATION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_YAW);
        if (Math.abs(oldLoc.pitch() - newLoc.pitch()) > DIFF_ROTATION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_PITCH);
        if (entity.enableHeadYaw() && Math.abs(oldLoc.headYaw() - newLoc.headYaw()) > DIFF_ROTATION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_HEAD_YAW);
        return flags;
    }

    @Override
    public void updateEntity(Entity entity) {
        if (!entities.containsKey(entity.getUniqueId()))
            throw new IllegalArgumentException("Entity " + entity.getUniqueId() + " is not added!");
        entityUpdateOperationQueue.offer(new EntityUpdateOperation(entity, EntityUpdateType.UPDATE));
    }

    @Override
    public void addEntity(Entity entity) {
        if (entities.containsKey(entity.getUniqueId()))
            throw new IllegalArgumentException("Entity " + entity.getUniqueId() + " is already added!");
        entityUpdateOperationQueue.offer(new EntityUpdateOperation(entity, EntityUpdateType.ADD));
    }

    @Override
    public void removeEntity(Entity entity) {
        if (!entities.containsKey(entity.getUniqueId()))
            throw new IllegalArgumentException("Entity " + entity.getUniqueId() + " is not added!");
        entityUpdateOperationQueue.offer(new EntityUpdateOperation(entity, EntityUpdateType.REMOVE));
    }

    @Override
    public boolean containEntity(Entity entity) {
        return entities.containsKey(entity.getUniqueId());
    }

    @Override
    public void offerScheduledMove(Entity entity, Location3dc newLoc) {
        if (!entities.containsKey(entity.getUniqueId()))
            throw new IllegalArgumentException("Entity " + entity.getUniqueId() + " is not registered in this service");
        if (entity.getLocation().equals(newLoc))
            return;
        scheduledMoveQueue.computeIfAbsent(entity.getUniqueId(), k -> new ConcurrentLinkedQueue<>()).offer(new ScheduledMove(entity, newLoc));
    }

    @Override
    public List<Entity> computeCollidingEntities(AABBdc aabb) {
        var result = new ArrayList<Entity>();
        entityAABBTree.detectOverlaps(aabb, result);
        return result;
    }

    @Override
    public List<Entity> getCachedEntityCollidingResult(Entity entity) {
        return entityCollisionCache.getOrDefault(entity.getUniqueId(), Collections.emptyList());
    }

    protected record ScheduledMove(Entity entity, Location3dc newLoc) {};

    protected record EntityUpdateOperation(Entity entity, EntityUpdateType type) {}

    protected enum EntityUpdateType {
        ADD,
        REMOVE,
        UPDATE
    }
}
