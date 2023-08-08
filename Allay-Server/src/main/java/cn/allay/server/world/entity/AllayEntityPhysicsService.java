package cn.allay.server.world.entity;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.datastruct.aabbtree.AABBTree;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.entity.Entity;
import cn.allay.api.math.Location3d;
import cn.allay.api.math.Location3dc;
import cn.allay.api.world.World;
import cn.allay.api.world.entity.EntityPhysicsService;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.doubles.DoubleBooleanImmutablePair;
import org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static cn.allay.api.block.component.impl.attribute.BlockAttributes.DEFAULT_FRICTION;

/**
 * Allay Project 2023/8/5 <br>
 * Special thanks to <a href="https://www.mcpk.wiki">MCPK Wiki</a>
 *
 * @author daoge_cmd
 */
public class AllayEntityPhysicsService implements EntityPhysicsService {

    public static final double DIFF_POSITION_THRESHOLD = 0.001;
    public static final double DIFF_ROTATION_THRESHOLD = 0.1;

    public static final double MOTION_THRESHOLD = 0.003;
    public static final double STEPPING_OFFSET = 0.00001;

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
        var updatedEntities = new Long2ObjectNonBlockingMap<Entity>();
        entities.values().parallelStream().forEach(entity -> {
            if (!entity.computeMovementServerSide()) return;
            //TODO: 碰撞箱挤压 水流作用
            updateMotion(entity);
            if (applyMotion(entity)) {
                updatedEntities.put(entity.getUniqueId(), entity);
            }
        });
        updatedEntities.values().forEach(entity -> entityAABBTree.update(entity));
    }

    protected void updateMotion(Entity entity) {
        //TODO: 效果乘数
        var effectFactor = 1;
        var blockUnder = world.getBlockState((int) entity.getLocation().x(), (int) (entity.getLocation().y() - 0.5), (int) entity.getLocation().z());
        double slipperyFactor = blockUnder != null ?
                blockUnder.blockType().getBlockBehavior().getBlockAttributes(blockUnder).friction() :
                DEFAULT_FRICTION;
        var mx = entity.getMotion().x();
        var my = entity.getMotion().y();
        var mz = entity.getMotion().z();
        //https://www.mcpk.wiki/wiki/Horizontal_Movement_Formulas
        double newMx;
        double newMz;
        var approachMx = mx * slipperyFactor * 0.91;
        var approachMz = mz * slipperyFactor * 0.91;
        double yaw = entity.getLocation().yaw();
        if (entity.isOnGround()) {
            newMx = approachMx + 0.1 * effectFactor * Math.pow(0.6 / slipperyFactor, 3) * Math.sin(yaw);
            newMz = approachMz + 0.1 * effectFactor * Math.pow(0.6 / slipperyFactor, 3) * Math.cos(yaw);
        } else {
            newMx = approachMx + 0.02 * Math.sin(yaw);
            newMz = approachMz + 0.02 * Math.cos(yaw);
        }
        double newMy = (my - entity.getGravity()) * 0.98;
        if (Math.abs(newMx) < MOTION_THRESHOLD) newMx = 0;
        if (Math.abs(newMy) < MOTION_THRESHOLD) newMy = 0;
        if (Math.abs(newMz) < MOTION_THRESHOLD) newMz = 0;
        entity.setMotion(new Vector3d(newMx, newMy, newMz));
    }

    protected boolean applyMotion(Entity entity) {
        var pos = new Location3d(entity.getLocation());
        var motion = entity.getMotion();
        var mx = motion.x();
        var my = motion.y();
        var mz = motion.z();
        var aabb = new AABBd(entity.getOffsetAABB());

        //先沿着Y轴移动
        var yResult = moveAlongYAxisAndStopWhenCollision(aabb, my, pos);
        my = yResult.left();
        entity.setOnGround(yResult.right());

        if (Math.abs(mx) >= Math.abs(mz)) {
            //先处理X轴, 然后处理Z轴
            mx = applyMotionX(entity.getStepHeight(), pos, mx, aabb);
            mz = applyMotionZ(entity.getStepHeight(), pos, mz, aabb);
        } else {
            mz = applyMotionZ(entity.getStepHeight(), pos, mz, aabb);
            mx = applyMotionX(entity.getStepHeight(), pos, mx, aabb);
        }

        entity.setMotion(new Vector3d(mx, my, mz));
        var updated = !pos.equals(entity.getLocation());
        updateEntityLocation(entity, pos);
        return updated;
    }

    protected double applyMotionZ(double stepHeight, Location3d pos, double mz, AABBd aabb) {
        if (mz != 0) {
            var resultAABB = new AABBd(aabb);
            var resultPos = new Vector3d(pos);
            var zResult = moveAlongZAxisAndStopWhenCollision(resultAABB, mz, resultPos);
            if (zResult.right()) {
                //有碰撞，尝试跨步
                if (tryStepping(aabb, stepHeight, mz > 0, false)) {
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

    protected double applyMotionX(double stepHeight, Location3d pos, double mx, AABBd aabb) {
        if (mx != 0) {
            var resultAABB = new AABBd(aabb);
            var resultPos = new Vector3d(pos);
            var xResult = moveAlongXAxisAndStopWhenCollision(resultAABB, mx, resultPos);
            if (xResult.right()) {
                //有碰撞，尝试跨步
                if (tryStepping(aabb, stepHeight, mx > 0, true)) {
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

    protected Pair<Double, Boolean> moveAlongXAxisAndStopWhenCollision(AABBd aabb, double mx, Vector3d recorder) {
        if (mx == 0) return new DoubleBooleanImmutablePair(0, false);
        var extendX = new AABBd(aabb);
        //计算射线X轴起点坐标
        double x;
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
        var blocks = world.getCollidingBlocks(extendX);
        var deltaX = mx;
        var collision = false;
        if (blocks.length != 0) {
            collision = true;
            //存在碰撞
            //union为一个能将所有方块aabb包含的最小aabb
            var union = unionBlockAABBs(blocks);
            //result包含射线与union求交的两个交点的参数
            var result = new Vector2d(0, 0);
            //计算射线YZ轴起点坐标
            var y = (aabb.maxY + aabb.minY) / 2d;
            var z = (aabb.maxZ + aabb.minZ) / 2d;
            union.intersectsRay(
                    x, y, z,
                    mx, 0, 0,
                    result
            );
            //计算X轴坐标变化量
            deltaX = mx * result.x;
            //x轴方向速度归零
            mx = 0;
        }
        //移动碰撞箱
        aabb.translate(deltaX, 0, 0);
        //更新坐标
        recorder.x += deltaX;
        return new DoubleBooleanImmutablePair(mx, collision);
    }

    protected boolean tryStepping(AABBd aabb, double stepHeight, boolean positive, boolean xAxis) {
        var offset = positive ? STEPPING_OFFSET : -STEPPING_OFFSET;
        var offsetAABB = aabb.translate(xAxis ? offset : 0, 0, xAxis ? 0 : offset, new AABBd());
        var recorder = new Vector3d();
        moveAlongYAxisAndStopWhenCollision(offsetAABB, stepHeight, recorder);
        moveAlongYAxisAndStopWhenCollision(offsetAABB, -stepHeight, recorder);
        if (world.getCollidingBlocks(offsetAABB).length != 0) {
            return false;
        } else {
            aabb.set(offsetAABB.translate(xAxis ? -offset : 0, 0, xAxis ? 0 : -offset));
            return true;
        }
    }

    protected Pair<Double, Boolean> moveAlongZAxisAndStopWhenCollision(AABBd aabb, double mz, Vector3d recorder) {
        if (mz == 0) return new DoubleBooleanImmutablePair(0, false);
        var extendZ = new AABBd(aabb);
        //计算射线Z轴起点坐标
        double z;
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
        var blocks = world.getCollidingBlocks(extendZ);
        var deltaZ = mz;
        var collision = false;
        if (blocks.length != 0) {
            collision = true;
            //存在碰撞
            //union为一个能将所有方块aabb包含的最小aabb
            var union = unionBlockAABBs(blocks);
            //result包含射线与union求交的两个交点的参数
            var result = new Vector2d(0, 0);
            //计算射线XY轴起点坐标
            var x = (aabb.maxX + aabb.minX) / 2d;
            var y = (aabb.maxY + aabb.minY) / 2d;
            union.intersectsRay(
                    x, y, z,
                    0, 0, mz,
                    result
            );
            //计算Z轴坐标变化量
            deltaZ = mz * result.x;
            //z轴方向速度归零
            mz = 0;
        }
        //移动碰撞箱
        aabb.translate(0, 0, deltaZ);
        //更新坐标
        recorder.z += deltaZ;
        return new DoubleBooleanImmutablePair(mz, collision);
    }

    protected Pair<Double, Boolean> moveAlongYAxisAndStopWhenCollision(AABBd aabb, double my, Vector3d recorder) {
        if (my == 0) return new DoubleBooleanImmutablePair(0, false);
        AABBd extendY = new AABBd(aabb);
        //计算射线Y轴起点坐标
        double y;
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
        var blocks = world.getCollidingBlocks(extendY);
        var deltaY = my;
        var onGround = false;
        if (blocks.length != 0) {
            //存在碰撞
            if (down) onGround = true;
            //union为一个能将所有方块aabb包含的最小aabb
            var union = unionBlockAABBs(blocks);
            //result包含射线与union求交的两个交点的参数
            var result = new Vector2d(0, 0);
            //计算射线XZ轴起点坐标
            var x = (aabb.maxX + aabb.minX) / 2d;
            var z = (aabb.maxZ + aabb.minZ) / 2d;
            union.intersectsRay(
                    x, y, z,
                    0, my, 0,
                    result
            );
            //计算Y轴坐标变化量
            deltaY = my * result.x;
            //y轴方向速度归零
            my = 0;
        }
        //移动碰撞箱
        aabb.translate(0, deltaY, 0);
        //更新坐标
        recorder.y += deltaY;
        return new DoubleBooleanImmutablePair(my, onGround);
    }

    protected AABBd unionBlockAABBs(BlockState[][][] blocks) {
        AABBd unionAABB = null;
        for (var sub1 : blocks) {
            for (var sub2 : sub1) {
                for (var block : sub2) {
                    if (block == null) continue;
                    var blockAABB = block.blockType().getBlockBehavior().getBlockAttributes(block).aabbCollision();
                    if (unionAABB == null) {
                        unionAABB = new AABBd();
                    } else {
                        unionAABB.union(blockAABB);
                    }
                }
            }
        }
        return unionAABB;
    }

    protected void handleScheduledMoveQueue() {
        for (var entry : scheduledMoveQueue.entrySet()) {
            var queue = entry.getValue();
            while (!queue.isEmpty()) {
                var scheduledMove = queue.poll();
                updateEntityLocation(scheduledMove.entity, scheduledMove.newLoc);
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

    protected void updateEntityLocation(Entity entity, Location3dc newLoc) {
        entity.broadcastMoveToViewers(computeMoveFlags(entity, entity.getLocation(), newLoc), newLoc);
        entity.setLocation(newLoc);
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
