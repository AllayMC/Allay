package cn.allay.server.world.entity;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.datastruct.aabbtree.AABBTree;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.entity.Entity;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.math.location.Location3fc;
import cn.allay.api.math.voxelshape.VoxelShape;
import cn.allay.api.utils.MathUtils;
import cn.allay.api.world.World;
import cn.allay.api.world.entity.EntityPhysicsService;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.floats.FloatBooleanImmutablePair;
import org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket;
import org.joml.Vector3f;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static cn.allay.api.block.component.attribute.BlockAttributes.DEFAULT_FRICTION;
import static java.lang.Math.*;

/**
 * Allay Project 2023/8/5 <br>
 * Special thanks to <a href="https://www.mcpk.wiki">MCPK Wiki</a>
 *
 * @author daoge_cmd
 */
public class AllayEntityPhysicsService implements EntityPhysicsService {

    public static final float DIFF_POSITION_THRESHOLD = 0.0001f;
    public static final float DIFF_ROTATION_THRESHOLD = 0.1f;

    public static final float MOTION_THRESHOLD = 0.003f;
    public static final float STEPPING_OFFSET = 0.05f;
    public static final float FAT_AABB_MARGIN = 0.0005f;
    public static final FloatBooleanImmutablePair EMPTY_FLOAT_BOOLEAN_PAIR = new FloatBooleanImmutablePair(0, false);

    protected World world;
    protected Map<Long, Entity> entities = new Long2ObjectNonBlockingMap<>();
    protected Map<Long, Queue<ScheduledMove>> scheduledMoveQueue = new Long2ObjectNonBlockingMap<>();
    protected Map<Long, List<Entity>> entityCollisionCache = new Long2ObjectNonBlockingMap<>();
    protected AABBTree<Entity> entityAABBTree = new AABBTree<>();
    protected Queue<EntityUpdateOperation> entityUpdateOperationQueue = new ConcurrentLinkedQueue<>();

    public AllayEntityPhysicsService(World world) {
        this.world = world;
    }

    @Override
    public void tick() {
        handleEntityUpdateQueue();
        handleScheduledMoveQueue();
        cacheEntityCollisionResult();
        var updatedEntities = new Long2ObjectNonBlockingMap<Entity>();
        entities.values().parallelStream().forEach(entity -> {
            if (!entity.computeMovementServerSide()) return;
            if (!entity.isInWorld()) return;
            //TODO: 水流作用 方块推出作用 etc...
            if (entity.hasEntityCollision()) computeCollisionMotion(entity);
            entity.setMotion(checkMotionThreshold(new Vector3f(entity.getMotion())));
            if (applyMotion(entity)) {
                updatedEntities.put(entity.getUniqueId(), entity);
            }
            updateMotion(entity);
        });
        updatedEntities.values().forEach(entity -> entityAABBTree.update(entity));
    }

    protected void cacheEntityCollisionResult() {
        entityCollisionCache.clear();
        entities.values().stream().filter(Entity::hasEntityCollision).forEach(entity -> {
            var collidedEntities = computeCollidingEntities(entity);
            if (collidedEntities.isEmpty()) return;
            entityCollisionCache.put(entity.getUniqueId(), collidedEntities);
        });
    }

    protected void computeCollisionMotion(Entity entity) {
        var collidedEntities = getCachedEntityCollidingResult(entity);
        var collisionMotion = new Vector3f(0, 0, 0);
        var loc = entity.getLocation();
        float r = entity.getPushSpeedReduction();
        for (var other : collidedEntities) {
            //https://github.com/lovexyn0827/Discovering-Minecraft/blob/master/Minecraft%E5%AE%9E%E4%BD%93%E8%BF%90%E5%8A%A8%E7%A0%94%E7%A9%B6%E4%B8%8E%E5%BA%94%E7%94%A8/5-Chapter-5.md
            var ol = other.getLocation();
            var direction = new Vector3f(entity.getLocation()).sub(other.getLocation(), new Vector3f()).normalize();
            float distance = max(abs(ol.x() - loc.x()), abs(ol.z() - loc.z()));
            float k;
            if (distance <= 0.01) continue;
            if (distance <= 1) {
                k = (0.05f * r) * MathUtils.fastFloatInverseSqrt(distance);
            } else {
                k = (0.05f * r) / distance;
            }
            collisionMotion.add(direction.mul(k));
        }
        collisionMotion.setComponent(1, 0);
        entity.addMotion(collisionMotion);
    }

    protected void updateMotion(Entity entity) {
        //https://www.mcpk.wiki/wiki/Horizontal_Movement_Formulas
        //TODO: 效果乘数
        float effectFactor = 1;
        float movementFactor = entity.getMovementFactor();
        var blockUnder = world.getBlockState((int) entity.getLocation().x(), (int) (entity.getLocation().y() - 0.5), (int) entity.getLocation().z());
        float slipperyFactor = blockUnder != null ?
                blockUnder.blockType().getBlockBehavior().getBlockAttributes(blockUnder).friction() :
                DEFAULT_FRICTION;
        float mx = entity.getMotion().x();
        float my = entity.getMotion().y();
        float mz = entity.getMotion().z();
        float newMx;
        float newMz;
        float approachMx = mx * slipperyFactor * 0.91f;
        float approachMz = mz * slipperyFactor * 0.91f;
        double yaw = entity.getLocation().yaw();
        if (entity.isOnGround()) {
            newMx = (float) (approachMx + 0.1f * movementFactor * effectFactor * Math.pow(0.6 / slipperyFactor, 3) * Math.sin(yaw));
            newMz = (float) (approachMz + 0.1f * movementFactor * effectFactor * Math.pow(0.6 / slipperyFactor, 3) * Math.cos(yaw));
        } else {
            newMx = (float) (approachMx + 0.02f * movementFactor * Math.sin(yaw));
            newMz = (float) (approachMz + 0.02f * movementFactor * Math.cos(yaw));
        }
        float newMy = (my - (entity.hasGravity() ? entity.getGravity() : 0f)) * 0.98f;
        entity.setMotion(checkMotionThreshold(new Vector3f(newMx, newMy, newMz)));
    }

    protected Vector3f checkMotionThreshold(Vector3f motion) {
        if (abs(motion.x) < MOTION_THRESHOLD) motion.x = 0;
        if (abs(motion.y) < MOTION_THRESHOLD) motion.y = 0;
        if (abs(motion.z) < MOTION_THRESHOLD) motion.z = 0;
        return motion;
    }

    protected boolean applyMotion(Entity entity) {
        var pos = new Location3f(entity.getLocation());
        var motion = entity.getMotion();
        var mx = motion.x();
        var my = motion.y();
        var mz = motion.z();
        var aabb = entity.getOffsetAABB();

        //先沿着Y轴移动
        var yResult = moveAlongYAxisAndStopWhenCollision(aabb, my, pos);
        my = yResult.left();
        entity.setOnGround(yResult.right());

        if (abs(mx) >= abs(mz)) {
            //先处理X轴, 然后处理Z轴
            mx = applyMotionX(entity.getStepHeight(), pos, mx, aabb, entity.isOnGround());
            mz = applyMotionZ(entity.getStepHeight(), pos, mz, aabb, entity.isOnGround());
        } else {
            mz = applyMotionZ(entity.getStepHeight(), pos, mz, aabb, entity.isOnGround());
            mx = applyMotionX(entity.getStepHeight(), pos, mx, aabb, entity.isOnGround());
        }

        entity.setMotion(new Vector3f(mx, my, mz));
        var updated = !pos.equals(entity.getLocation());
        return updated && updateEntityLocation(entity, pos);
    }

    protected float applyMotionZ(float stepHeight, Location3f pos, float mz, AABBf aabb, boolean enableStepping) {
        if (mz != 0) {
            var resultAABB = new AABBf(aabb);
            var resultPos = new Vector3f(pos);
            //第一次直接移动
            var zResult = moveAlongZAxisAndStopWhenCollision(resultAABB, mz, resultPos);
            if (zResult.right()) {
                //有碰撞，尝试跨步
                //计算剩余速度
                mz = mz - (resultPos.z - pos.z);
                if (enableStepping && tryStepping(resultPos, resultAABB, stepHeight, mz > 0, false)) {
                    //跨步成功
                    zResult = moveAlongZAxisAndStopWhenCollision(resultAABB, mz, resultPos);
                }
            }
            mz = zResult.left();
            aabb.set(resultAABB);
            pos.set(resultPos);
        }
        return mz;
    }

    protected float applyMotionX(float stepHeight, Location3f pos, float mx, AABBf aabb, boolean enableStepping) {
        if (mx != 0) {
            var resultAABB = new AABBf(aabb);
            var resultPos = new Vector3f(pos);
            //第一次直接移动
            var xResult = moveAlongXAxisAndStopWhenCollision(resultAABB, mx, resultPos);
            if (xResult.right()) {
                //有碰撞，尝试跨步
                //计算剩余速度
                mx = mx - (resultPos.x - pos.x);
                if (enableStepping && tryStepping(resultPos, resultAABB, stepHeight, mx > 0, true)) {
                    //跨步成功
                    xResult = moveAlongXAxisAndStopWhenCollision(resultAABB, mx, resultPos);
                }
            }
            mx = xResult.left();
            aabb.set(resultAABB);
            pos.set(resultPos);
        }
        return mx;
    }

    protected Pair<Float, Boolean> moveAlongXAxisAndStopWhenCollision(AABBf aabb, float mx, Vector3f recorder) {
        if (mx == 0) return EMPTY_FLOAT_BOOLEAN_PAIR;
        var extendX = new AABBf(aabb);
        //计算射线X轴起点坐标
        float x;
        if (mx < 0) {
            //向X轴负方向移动
            x = aabb.minX;
            extendX.maxX -= extendX.lengthX();
            extendX.minX += mx;
        } else {
            //向X轴正方向移动
            x = aabb.maxX;
            extendX.minX += extendX.lengthX();
            extendX.maxX += mx;
        }
        var deltaX = mx;
        var collision = false;
        if (!world.isAABBInWorld(extendX)) return EMPTY_FLOAT_BOOLEAN_PAIR;
        var blocks = world.getCollidingBlocks(extendX);
        if (blocks != null) {
            collision = true;
            //存在碰撞
            var minX = (float) floor(extendX.minX);
            var maxX = computeMax(minX, 0, blocks);
            if (minX <= x && x <= maxX) {
                //卡方块里面了
                deltaX = 0;
            } else {
                deltaX = min(abs(x - minX), abs(x - maxX));
                if (mx < 0) deltaX = -deltaX;
                if (abs(deltaX) <= FAT_AABB_MARGIN) deltaX = 0;
            }
            //x轴方向速度归零
            mx = 0;
        }
        //移动碰撞箱
        aabb.translate(deltaX, 0, 0);
        //更新坐标
        recorder.x += deltaX;
        return new FloatBooleanImmutablePair(mx, collision);
    }

    protected Pair<Float, Boolean> moveAlongZAxisAndStopWhenCollision(AABBf aabb, float mz, Vector3f recorder) {
        if (mz == 0) return EMPTY_FLOAT_BOOLEAN_PAIR;
        var extendZ = new AABBf(aabb);
        //计算射线Z轴起点坐标
        float z;
        if (mz < 0) {
            //向Z轴负方向移动
            z = aabb.minZ;
            extendZ.maxZ -= extendZ.lengthZ();
            extendZ.minZ += mz;
        } else {
            //向Z轴正方向移动
            z = aabb.maxZ;
            extendZ.minZ += extendZ.lengthZ();
            extendZ.maxZ += mz;
        }
        var deltaZ = mz;
        var collision = false;
        if (!world.isAABBInWorld(extendZ)) return EMPTY_FLOAT_BOOLEAN_PAIR;
        var blocks = world.getCollidingBlocks(extendZ);
        if (blocks != null) {
            collision = true;
            //存在碰撞
            var minZ = (float) floor(extendZ.minZ);
            var maxZ = computeMax(minZ, 2, blocks);
            if (minZ <= z && z <= maxZ) {
                //卡方块里面了
                deltaZ = 0;
            } else {
                deltaZ = min(abs(z - minZ), abs(z - maxZ));
                if (mz < 0) deltaZ = -deltaZ;
                if (abs(deltaZ) <= FAT_AABB_MARGIN) deltaZ = 0;
            }
            //z轴方向速度归零
            mz = 0;
        }
        //移动碰撞箱
        aabb.translate(0, 0, deltaZ);
        //更新坐标
        recorder.z += deltaZ;
        return new FloatBooleanImmutablePair(mz, collision);
    }

    protected Pair<Float, Boolean> moveAlongYAxisAndStopWhenCollision(AABBf aabb, float my, Vector3f recorder) {
        if (my == 0) return EMPTY_FLOAT_BOOLEAN_PAIR;
        AABBf extendY = new AABBf(aabb);
        //计算射线Y轴起点坐标
        float y;
        boolean down = false;
        //检查范围不包括实体aabb
        if (my < 0) {
            //向下移动
            down = true;
            y = aabb.minY;
            extendY.maxY -= extendY.lengthY();
            extendY.minY += my;
        } else {
            //向上移动
            y = aabb.maxY;
            extendY.minY += extendY.lengthY();
            extendY.maxY += my;
        }
        var deltaY = my;
        var onGround = false;
        if (!world.isAABBInWorld(extendY)) return EMPTY_FLOAT_BOOLEAN_PAIR;
        var blocks = world.getCollidingBlocks(extendY);
        if (blocks != null) {
            //存在碰撞
            if (down) onGround = true;
            var minY = (float) floor(extendY.minY);
            var maxY = computeMax(minY, 1, blocks);
            if (minY <= y && y <= maxY) {
                //卡方块里面了
                deltaY = 0;
            } else {
                deltaY = min(abs(y - minY), abs(y - maxY));
                if (my < 0) deltaY = -deltaY;
                if (abs(deltaY) <= FAT_AABB_MARGIN) deltaY = 0;
            }
            //y轴方向速度归零
            my = 0;
        }
        //移动碰撞箱
        aabb.translate(0, deltaY, 0);
        //更新坐标
        recorder.y += deltaY;
        return new FloatBooleanImmutablePair(my, onGround);
    }

    protected boolean tryStepping(Vector3f pos, AABBf aabb, float stepHeight, boolean positive, boolean xAxis) {
        var offset = positive ? STEPPING_OFFSET : -STEPPING_OFFSET;
        var offsetAABB = aabb.translate(xAxis ? offset : 0, 0, xAxis ? 0 : offset, new AABBf());
        var recorder = new Vector3f();
        moveAlongYAxisAndStopWhenCollision(offsetAABB, stepHeight, recorder);
        moveAlongYAxisAndStopWhenCollision(offsetAABB, -stepHeight, recorder);
        if (recorder.y == 0 || world.getCollidingBlocks(offsetAABB) != null) {
            return false;
        } else {
            aabb.set(offsetAABB.translate(xAxis ? -offset : 0, 0, xAxis ? 0 : -offset));
            pos.add(recorder);
            return true;
        }
    }

    protected float computeMax(float start, int xyzMode, BlockState[][][] blocks) {
        float max = start;
        for (int ox = 0, blocksLength = blocks.length; ox < blocksLength; ox++) {
            BlockState[][] sub1 = blocks[ox];
            for (int oy = 0, sub1Length = sub1.length; oy < sub1Length; oy++) {
                BlockState[] sub2 = sub1[oy];
                for (int oz = 0, sub2Length = sub2.length; oz < sub2Length; oz++) {
                    BlockState blockState = sub2[oz];
                    if (blockState == null) continue;
                    var current = 0f;
                    switch (xyzMode) {
                        case 0 -> {
                            //x
                            current = blockState.getBlockAttributes().voxelShape().unionAABB().lengthX() + start + ox;
                        }
                        case 1 -> {
                            //y
                            current = blockState.getBlockAttributes().voxelShape().unionAABB().lengthY() + start + oy;
                        }
                        case 2 -> {
                            //y
                            current = blockState.getBlockAttributes().voxelShape().unionAABB().lengthZ() + start + oz;
                        }
                    }
                    if (current > max) max = current;
                }
            }
        }
        return max;
    }

    protected void handleScheduledMoveQueue() {
        for (var entry : scheduledMoveQueue.entrySet()) {
            var queue = entry.getValue();
            while (!queue.isEmpty()) {
                var scheduledMove = queue.poll();
                if (updateEntityLocation(scheduledMove.entity, scheduledMove.newLoc))
                    entityAABBTree.update(scheduledMove.entity);
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

    protected boolean updateEntityLocation(Entity entity, Location3fc newLoc) {
        if (!world.isInWorld(newLoc.x(), newLoc.y(), newLoc.z())) return false;
        entity.broadcastMoveToViewers(computeMoveFlags(entity, entity.getLocation(), newLoc), newLoc);
        entity.setLocation(newLoc);
        return true;
    }

    protected Set<MoveEntityDeltaPacket.Flag> computeMoveFlags(Entity entity, Location3fc oldLoc, Location3fc newLoc) {
        var flags = EnumSet.noneOf(MoveEntityDeltaPacket.Flag.class);
        if (abs(oldLoc.x() - newLoc.x()) > DIFF_POSITION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_X);
        if (abs(oldLoc.y() - newLoc.y()) > DIFF_POSITION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_Y);
        if (abs(oldLoc.z() - newLoc.z()) > DIFF_POSITION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_Z);
        if (abs(oldLoc.yaw() - newLoc.yaw()) > DIFF_ROTATION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_YAW);
        if (abs(oldLoc.pitch() - newLoc.pitch()) > DIFF_ROTATION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_PITCH);
        if (entity.enableHeadYaw() && abs(oldLoc.headYaw() - newLoc.headYaw()) > DIFF_ROTATION_THRESHOLD) flags.add(MoveEntityDeltaPacket.Flag.HAS_HEAD_YAW);
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
            return;
        entityUpdateOperationQueue.offer(new EntityUpdateOperation(entity, EntityUpdateType.REMOVE));
    }

    @Override
    public boolean containEntity(Entity entity) {
        return entities.containsKey(entity.getUniqueId());
    }

    @Override
    public void offerScheduledMove(Entity entity, Location3fc newLoc) {
        if (!entities.containsKey(entity.getUniqueId()))
            throw new IllegalArgumentException("Entity " + entity.getUniqueId() + " is not registered in this service");
        if (entity.getLocation().equals(newLoc))
            return;
        scheduledMoveQueue.computeIfAbsent(entity.getUniqueId(), k -> new ConcurrentLinkedQueue<>()).offer(new ScheduledMove(entity, newLoc));
    }

    @Override
    public List<Entity> computeCollidingEntities(AABBfc aabb) {
        var result = new ArrayList<Entity>();
        entityAABBTree.detectOverlaps(aabb, result);
        return result;
    }

    @Override
    public List<Entity> computeCollidingEntities(VoxelShape voxelShape) {
        //用一个set暂存entity避免重复
        var list = new ArrayList<Entity>();
        entityAABBTree.detectOverlaps(voxelShape.unionAABB(), list);
        list.removeIf(entity -> !voxelShape.intersectsAABB(entity.getOffsetAABB()));
        return list;
    }

    @Override
    public List<Entity> getCachedEntityCollidingResult(Entity entity) {
        return entityCollisionCache.getOrDefault(entity.getUniqueId(), Collections.emptyList());
    }

    protected record ScheduledMove(Entity entity, Location3fc newLoc) {};

    protected record EntityUpdateOperation(Entity entity, EntityUpdateType type) {}

    protected enum EntityUpdateType {
        ADD,
        REMOVE,
        UPDATE
    }
}
