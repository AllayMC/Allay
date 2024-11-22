package org.allaymc.server.world.service;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.floats.FloatBooleanImmutablePair;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityMoveEvent;
import org.allaymc.api.eventbus.event.player.PlayerMoveEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.service.AABBOverlapFilter;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.server.datastruct.aabb.AABBTree;
import org.allaymc.server.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.joml.Vector3f;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Math.*;
import static org.allaymc.api.block.component.data.BlockStateData.DEFAULT_FRICTION;
import static org.allaymc.api.block.type.BlockTypes.AIR;
import static org.allaymc.api.math.MathUtils.isInRange;

/**
 * Special thanks to <a href="https://www.mcpk.wiki">MCPK Wiki</a>
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEntityPhysicsService implements EntityPhysicsService {

    public static final FloatBooleanImmutablePair EMPTY_FLOAT_BOOLEAN_PAIR = new FloatBooleanImmutablePair(0, false);

    public static final float MOTION_THRESHOLD;
    public static final float STEPPING_OFFSET;
    public static final float FAT_AABB_MARGIN;
    public static final float BLOCK_COLLISION_MOTION;

    private static final float MOMENTUM_FACTOR = 0.91f;
    private static final float GROUND_VELOCITY_FACTOR = 0.1f;
    private static final float AIR_VELOCITY_FACTOR = 0.02f;
    private static final float DRAG_FACTOR = 0.98f;

    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;

    static {
        var settings = Server.SETTINGS.entitySettings().physicsEngineSettings();
        MOTION_THRESHOLD = settings.motionThreshold();
        STEPPING_OFFSET = settings.steppingOffset();
        FAT_AABB_MARGIN = settings.fatAABBMargin();
        BLOCK_COLLISION_MOTION = settings.blockCollisionMotion();
    }

    protected Map<Long, Entity> entities = new Long2ObjectOpenHashMap<>();
    protected Map<Long, Queue<ClientMove>> clientMoveQueue = new Long2ObjectOpenHashMap<>();
    protected Map<Long, List<Entity>> entityCollisionCache = new Long2ObjectOpenHashMap<>();
    /**
     * Regardless of the value of the entity's hasEntityCollision(), this aabb tree saves its collision result
     */
    protected AABBTree<Entity> entityAABBTree = new AABBTree<>();
    protected Dimension dimension;

    public AllayEntityPhysicsService(Dimension dimension) {
        this.dimension = dimension;
    }

    public void tick() {
        handleClientMoveQueue();
        cacheEntityCollisionResult();
        var updatedEntities = new Long2ObjectNonBlockingMap<Entity>();
        entities.values().parallelStream().forEach(entity -> {
            if (!entity.computeMovementServerSide()) return;
            if (!entity.isCurrentChunkLoaded()) return;
            if (entity.getLocation().y() < dimension.getDimensionInfo().minHeight()) return;
            // TODO: liquid motion etc...
            var collidedBlocks = dimension.getCollidingBlockStates(entity.getOffsetAABB());
            if (collidedBlocks == null) {
                // 1. The entity is not stuck in the block
                if (entity.computeEntityCollisionMotion()) computeEntityCollisionMotion(entity);
                entity.setMotion(checkMotionThreshold(new Vector3f(entity.getMotion())));
                if (applyMotion(entity)) updatedEntities.put(entity.getRuntimeId(), entity);
                // Apply friction, gravity etc...
                updateMotion(entity);
            } else if (entity.computeBlockCollisionMotion()) {
                // 2. The entity is stuck in the block
                // Do not calculate other motion exclude block collision motion
                computeBlockCollisionMotion(entity, collidedBlocks);
                entity.setMotion(checkMotionThreshold(new Vector3f(entity.getMotion())));
                if (forceApplyMotion(entity)) {
                    updatedEntities.put(entity.getRuntimeId(), entity);
                }
            }
        });
        updatedEntities.values().forEach(entityAABBTree::update);
    }

    protected void cacheEntityCollisionResult() {
        entityCollisionCache.clear();
        entities.values().forEach(entity -> {
            var collidedEntities = computeCollidingEntities(entity, true);
            if (collidedEntities.isEmpty()) return;
            entityCollisionCache.put(entity.getRuntimeId(), collidedEntities);
            collidedEntities.forEach(entity::onCollideWith);
        });
    }

    protected void computeBlockCollisionMotion(Entity entity, BlockState[][][] collidedBlocks) {
        // 1. Find out the block state which entity collided most
        var aabb = entity.getOffsetAABB();
        var minX = (int) Math.floor(aabb.minX());
        var minY = (int) Math.floor(aabb.minY());
        var minZ = (int) Math.floor(aabb.minZ());
        int targetX = 0, targetY = 0, targetZ = 0;
        float V = 0;
        for (int ox = 0, blocksLength = collidedBlocks.length; ox < blocksLength; ox++) {
            BlockState[][] sub1 = collidedBlocks[ox];
            for (int oy = 0, sub1Length = sub1.length; oy < sub1Length; oy++) {
                BlockState[] sub2 = sub1[oy];
                for (int oz = 0, sub2Length = sub2.length; oz < sub2Length; oz++) {
                    BlockState blockState = sub2[oz];
                    if (blockState == null) continue;
                    var currentX = minX + ox;
                    var currentY = minY + oy;
                    var currentZ = minZ + oz;
                    var intersection = blockState
                            .getBlockStateData()
                            .computeOffsetCollisionShape(currentX, currentY, currentZ)
                            .unionAABB()
                            .intersection(aabb);
                    var currentV = intersection.lengthX() * intersection.lengthY() * intersection.lengthZ();
                    if (currentV > V) {
                        V = currentV;
                        targetX = currentX;
                        targetY = currentY;
                        targetZ = currentZ;
                    }
                }
            }
        }

        // 2. Centered on the block pos we found (1), find out the best moving direction
        BlockFace movingDirection = null;
        var values = BlockFace.values();
        float distanceSqrt = Integer.MAX_VALUE;
        for (int i = values.length - 1; i >= 0; i--) {
            var blockFace = values[i];
            var offsetVec = blockFace.offsetPos(targetX, targetY, targetZ);
            var blockState = dimension.getBlockState(offsetVec);
            if (blockState.getBlockType() == AIR) {
                var currentDistanceSqrt = entity.getLocation().distanceSquared(offsetVec.x() + 0.5f, offsetVec.y() + 0.5f, offsetVec.z() + 0.5f);
                if (currentDistanceSqrt < distanceSqrt) {
                    movingDirection = blockFace;
                    distanceSqrt = currentDistanceSqrt;
                }
            }
        }
        if (movingDirection == null) movingDirection = BlockFace.UP;

        // 3. Add motion to the entity
        var directionOffset = movingDirection.getOffset();
        var mx = directionOffset.x();
        var my = directionOffset.y();
        var mz = directionOffset.z();
        entity.setMotion(mx * BLOCK_COLLISION_MOTION, my * BLOCK_COLLISION_MOTION, mz * BLOCK_COLLISION_MOTION);
    }

    protected void computeEntityCollisionMotion(Entity entity) {
        var collidedEntities = getCachedEntityCollidingResult(entity);
        collidedEntities.removeIf(e -> !e.computeEntityCollisionMotion());

        var collisionMotion = new Vector3f(0, 0, 0);

        var location = entity.getLocation();
        var pushSpeedReduction = entity.getPushSpeedReduction();
        for (var other : collidedEntities) {
            // https://github.com/lovexyn0827/Discovering-Minecraft/blob/master/Minecraft%E5%AE%9E%E4%BD%93%E8%BF%90%E5%8A%A8%E7%A0%94%E7%A9%B6%E4%B8%8E%E5%BA%94%E7%94%A8/5-Chapter-5.md
            var ol = other.getLocation();
            var direction = new Vector3f(entity.getLocation()).sub(other.getLocation(), new Vector3f()).normalize();
            var distance = max(abs(ol.x() - location.x()), abs(ol.z() - location.z()));
            if (distance <= 0.01) continue;

            var k = 0.05f * pushSpeedReduction;
            if (distance <= 1) {
                k *= MathUtils.fastFloatInverseSqrt(distance);
            } else {
                k /= distance;
            }

            collisionMotion.add(direction.mul(k));
        }

        collisionMotion.setComponent(1, 0);
        entity.addMotion(collisionMotion);
    }

    /**
     * @see <a href="https://www.mcpk.wiki/wiki/Horizontal_Movement_Formulas">Horizontal Movement Formulas</a>
     */
    protected void updateMotion(Entity entity) {
        var motion = entity.getMotion();
        var blockStateStandingOn = entity.getBlockStateStandingOn();

        // 1. Multiplier factors
        var movementFactor = entity.getMovementFactor();

        var speedLevel = entity.getEffectLevel(EffectTypes.SPEED);
        var slownessLevel = entity.getEffectLevel(EffectTypes.SLOWNESS);

        var effectFactor = (1f + 0.2f * speedLevel) * (1f - 0.15f * slownessLevel);

        var slipperinessMultiplier = blockStateStandingOn != null ?
                blockStateStandingOn.getBlockStateData().friction() :
                DEFAULT_FRICTION;

        var momentumMx = motion.x() * slipperinessMultiplier * MOMENTUM_FACTOR;
        var momentumMz = motion.z() * slipperinessMultiplier * MOMENTUM_FACTOR;

        // 2. Complete Formulas
        var velocityFactor = entity.isOnGround() ? GROUND_VELOCITY_FACTOR : AIR_VELOCITY_FACTOR;
        var acceleration = velocityFactor * movementFactor;
        if (entity.isOnGround()) {
            acceleration *= (float) (effectFactor * Math.pow(DEFAULT_FRICTION / slipperinessMultiplier, 3));
        }

        var yaw = entity.getLocation().yaw();

        var newMx = (float) (momentumMx + acceleration * Math.sin(yaw));
        var newMz = (float) (momentumMz + acceleration * Math.cos(yaw));

        // Skip sprint jump boost because this service does not handle player

        var newMy = (motion.y() - (entity.hasGravity() ? entity.getGravity() : 0f)) * DRAG_FACTOR;
        entity.setMotion(checkMotionThreshold(new Vector3f(newMx, newMy, newMz)));
    }

    protected Vector3f checkMotionThreshold(Vector3f motion) {
        if (abs(motion.x) < MOTION_THRESHOLD) motion.x = 0;
        if (abs(motion.y) < MOTION_THRESHOLD) motion.y = 0;
        if (abs(motion.z) < MOTION_THRESHOLD) motion.z = 0;
        return motion;
    }

    protected boolean forceApplyMotion(Entity entity) {
        var loc = new Location3f(entity.getLocation());
        loc.add(entity.getMotion());
        return updateEntityLocation(entity, loc);
    }

    protected boolean applyMotion(Entity entity) {
        var pos = new Location3f(entity.getLocation());
        var motion = entity.getMotion();
        var mx = motion.x();
        var my = motion.y();
        var mz = motion.z();
        var aabb = entity.getOffsetAABB();

        // First move along the Y axis
        var yResult = moveAlongAxisAndStopWhenCollision(aabb, my, pos, Y);
        my = yResult.left();
        var isOnGround = yResult.right();

        if (abs(mx) >= abs(mz)) {
            // First handle the X axis, then handle the Z axis
            mx = applyMotion0(entity.getStepHeight(), pos, mx, aabb, isOnGround, X);
            mz = applyMotion0(entity.getStepHeight(), pos, mz, aabb, isOnGround, Z);
        } else {
            mz = applyMotion0(entity.getStepHeight(), pos, mz, aabb, isOnGround, Z);
            mx = applyMotion0(entity.getStepHeight(), pos, mx, aabb, isOnGround, X);
        }

        entity.setMotion(new Vector3f(mx, my, mz));
        if (!pos.equals(entity.getLocation()) && updateEntityLocation(entity, pos)) {
            // Update onGround status after updated entity location
            // to make sure that some block (for example: water) can reset
            // entity's fallDistance before onFall() called
            ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).setOnGround(isOnGround);
            return true;
        }
        return false;
    }

    /**
     * Applies motion to the object's position along the specified axis, considering potential collisions and intersections with other objects.
     *
     * @param stepHeight     The step height the object can overcome.
     * @param pos            The current position of the object.
     * @param motion         The component of the object's movement velocity along the specified axis (X or Z).
     * @param aabb           The Axis-Aligned Bounding Box (AABB) of the object.
     * @param enableStepping Flag indicating whether the object can step over obstacles.
     * @param axis           The axis along which the motion is applied (X or Z).
     *
     * @return The remaining component of the object's movement velocity along the specified axis after considering possible collisions and intersections.
     */
    private float applyMotion0(float stepHeight, Location3f pos, float motion, AABBf aabb, boolean enableStepping, int axis) {
        if (motion == 0) return motion;
        if (axis < X || axis > Z) throw new IllegalArgumentException("Invalid axis: " + axis);

        var resultAABB = new AABBf(aabb);
        var resultPos = new Vector3f(pos);

        // The first time directly moves
        var result = moveAlongAxisAndStopWhenCollision(resultAABB, motion, resultPos, axis);
        if (Boolean.TRUE.equals(result.right())) {
            // There is a collision, try to step over
            // Calculate the remaining speed
            motion -= resultPos.get(axis) - pos.get(axis);
            if (enableStepping && tryStepping(resultPos, resultAABB, stepHeight, motion > 0, axis == X)) {
                result = moveAlongAxisAndStopWhenCollision(resultAABB, motion, resultPos, axis);
            }
        }

        motion = result.left();

        aabb.set(resultAABB);
        pos.set(resultPos);
        return motion;
    }

    /**
     * Moves an axis-aligned bounding box (AABB) along a specified axis direction and stops when a collision occurs.
     *
     * @param aabb     The axis-aligned bounding box to move.
     * @param motion   The distance to move along the specified axis.
     * @param recorder The vector to record the movement along the axis.
     * @param axis     The axis along which to move the AABB. Use 0 for the X-axis, 1 for the Y-axis, and 2 for the Z-axis.
     *
     * @return A pair containing the remaining movement distance along the axis after collision detection (Float)
     * and a boolean indicating whether a collision occurred (Boolean).
     * If no movement was specified (motion = 0), an empty pair is returned.
     *
     * @throws IllegalArgumentException if an invalid axis is provided.
     */
    private Pair<Float, Boolean> moveAlongAxisAndStopWhenCollision(AABBf aabb, float motion, Vector3f recorder, int axis) {
        if (motion == 0) return EMPTY_FLOAT_BOOLEAN_PAIR;

        var extendAxis = new AABBf(aabb);

        // Move towards the negative(motion < 0) or positive(motion > 0) axis direction
        var shouldTowardsNegative = motion < 0;
        switch (axis) {
            case X:
                var lengthX = extendAxis.lengthX();
                extendAxis.minX += shouldTowardsNegative ? motion : lengthX;
                extendAxis.maxX += shouldTowardsNegative ? -lengthX : motion;
                break;
            case Y:
                var lengthY = extendAxis.lengthY();
                extendAxis.minY += shouldTowardsNegative ? motion : lengthY;
                extendAxis.maxY += shouldTowardsNegative ? -lengthY : motion;
                break;
            case Z:
                var lengthZ = extendAxis.lengthZ();
                extendAxis.minZ += shouldTowardsNegative ? motion : lengthZ;
                extendAxis.maxZ += shouldTowardsNegative ? -lengthZ : motion;
                break;
            default:
                throw new IllegalArgumentException("Invalid axis provided");
        }

        if (notValidEntityArea(extendAxis)) return EMPTY_FLOAT_BOOLEAN_PAIR;

        var deltaAxis = motion;
        var collision = false;

        var blocks = dimension.getCollidingBlockStates(extendAxis);
        if (blocks != null) {
            collision = axis != Y || shouldTowardsNegative;

            // There is a collision
            var minAxis = (float) floor(extendAxis.getMin(axis));
            var maxAxis = computeMax(minAxis, axis, blocks);
            // Calculate the ray axis starting coordinate
            var coordinate = shouldTowardsNegative ? aabb.getMin(axis) : aabb.getMax(axis);
            if (isInRange(minAxis, coordinate, maxAxis)) {
                // Stuck into the block
                deltaAxis = 0;
            } else {
                deltaAxis = min(abs(coordinate - minAxis), abs(coordinate - maxAxis));
                if (shouldTowardsNegative) deltaAxis = -deltaAxis;
                if (abs(deltaAxis) <= FAT_AABB_MARGIN) deltaAxis = 0;
            }

            motion = 0;
        }

        // Move the collision box
        if (axis == X) aabb.translate(deltaAxis, 0, 0);
        else if (axis == Y) aabb.translate(0, deltaAxis, 0);
        else aabb.translate(0, 0, deltaAxis);

        // Update the coordinates
        recorder.setComponent(axis, recorder.get(axis) + deltaAxis);
        return new FloatBooleanImmutablePair(motion, collision);
    }

    // Do not use dimension.isAABBInDimension(extendX|Y|Z) because entity should be able to move even if y > maxHeight
    protected boolean notValidEntityArea(AABBf extendAABB) {
        return (extendAABB.minY < dimension.getDimensionInfo().minHeight()) &&
               !dimension.getChunkService().isChunkLoaded((int) extendAABB.minX >> 4, (int) extendAABB.minZ >> 4) &&
               !dimension.getChunkService().isChunkLoaded((int) extendAABB.maxX >> 4, (int) extendAABB.maxZ >> 4);
    }

    protected boolean tryStepping(Vector3f pos, AABBf aabb, float stepHeight, boolean positive, boolean xAxis) {
        var offset = positive ? STEPPING_OFFSET : -STEPPING_OFFSET;
        var offsetAABB = aabb.translate(xAxis ? offset : 0, 0, xAxis ? 0 : offset, new AABBf());
        var recorder = new Vector3f();
        moveAlongAxisAndStopWhenCollision(offsetAABB, stepHeight, recorder, Y);
        moveAlongAxisAndStopWhenCollision(offsetAABB, -stepHeight, recorder, Y);
        if (recorder.y == 0 || dimension.getCollidingBlockStates(offsetAABB) != null) {
            return false;
        } else {
            aabb.set(offsetAABB.translate(xAxis ? -offset : 0, 0, xAxis ? 0 : -offset));
            pos.add(recorder);
            return true;
        }
    }

    protected float computeMax(float start, int axis, BlockState[][][] blocks) {
        float max = start;
        for (int ox = 0, blocksLength = blocks.length; ox < blocksLength; ox++) {
            BlockState[][] sub1 = blocks[ox];
            for (int oy = 0, sub1Length = sub1.length; oy < sub1Length; oy++) {
                BlockState[] sub2 = sub1[oy];
                for (int oz = 0, sub2Length = sub2.length; oz < sub2Length; oz++) {
                    BlockState blockState = sub2[oz];
                    if (blockState == null) continue;
                    float current;
                    var unionAABB = blockState.getBlockStateData().collisionShape().unionAABB();
                    switch (axis) {
                        case X -> current = unionAABB.lengthX() + start + ox;
                        case Y -> current = unionAABB.lengthY() + start + oy;
                        case Z -> current = unionAABB.lengthZ() + start + oz;
                        default -> throw new IllegalArgumentException("Invalid axis provided");
                    }
                    if (current > max) max = current;
                }
            }
        }
        return max;
    }

    protected void handleClientMoveQueue() {
        for (var entry : clientMoveQueue.entrySet()) {
            var queue = entry.getValue();
            while (!queue.isEmpty()) {
                var clientMove = queue.poll();
                var player = clientMove.player();
                // The player may have been removed
                if (!entities.containsKey(player.getRuntimeId())) continue;

                var event = new PlayerMoveEvent(player, player.getLocation(), clientMove.newLoc());
                Server.getInstance().getEventBus().callEvent(event);
                if (event.isCancelled()) {
                    // Let client back to the previous pos
                    player.teleport(event.getFrom());
                    continue;
                }

                // Calculate delta pos (motion)
                var motion = event.getTo().sub(player.getLocation(), new Vector3f());
                ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent()).setMotionValueOnly(motion);
                if (updateEntityLocation(player, clientMove.newLoc())) {
                    entityAABBTree.update(player);
                }
                // ClientMove is not calculated by the server, but we need to calculate the onGround status
                // If it's a server-calculated move, the onGround status will be calculated in applyMotion()
                var aabb = clientMove.player.getOffsetAABB();
                aabb.minY -= FAT_AABB_MARGIN;
                ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent()).setOnGround(dimension.getCollidingBlockStates(aabb) != null);
            }
        }
    }

    protected boolean updateEntityLocation(Entity entity, Location3fc newLoc) {
        var event = new EntityMoveEvent(entity, entity.getLocation(), newLoc);
        event.call();
        if (event.isCancelled()) {
            return false;
        }
        newLoc = event.getTo();

        var baseComponent = (EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent();
        if (baseComponent.setLocationAndCheckChunk(newLoc)) {
            baseComponent.broadcastMoveToViewers(newLoc, false);
            return true;
        } else {
            // Entity is moving into unloaded chunk, and we need to reset the motion
            // to prevent the entity from moving into unloaded chunk continuously
            entity.setMotion(0, 0, 0);
            return false;
        }
    }

    /**
     * Please call it before run tick()!
     */
    public void addEntity(Entity entity) {
        if (entities.containsKey(entity.getRuntimeId()))
            throw new IllegalArgumentException("Entity " + entity.getRuntimeId() + " is already added!");
        entities.put(entity.getRuntimeId(), entity);
        entityAABBTree.add(entity);
    }

    /**
     * Please call it before run tick()!
     */
    public void removeEntity(Entity entity) {
        if (!entities.containsKey(entity.getRuntimeId())) return;
        entities.remove(entity.getRuntimeId());
        entityAABBTree.remove(entity);
        entityCollisionCache.remove(entity.getRuntimeId());
    }

    /**
     * Please note that this method usually been called asynchronously <p/>
     * See {@link org.allaymc.server.network.processor.PlayerAuthInputPacketProcessor#handleAsync(EntityPlayer, PlayerAuthInputPacket, long)}
     */
    public void offerClientMove(EntityPlayer player, Location3fc newLoc) {
        if (!entities.containsKey(player.getRuntimeId())) return;
        if (player.getLocation().equals(newLoc)) return;
        clientMoveQueue.computeIfAbsent(player.getRuntimeId(), $ -> new ConcurrentLinkedQueue<>()).offer(new ClientMove(player, newLoc));
    }

    @Override
    public List<Entity> computeCollidingEntities(AABBfc aabb, AABBOverlapFilter<Entity> predicate) {
        var result = new LinkedList<Entity>();
        entityAABBTree.detectOverlaps(aabb, predicate, result);
        return result;
    }

    @Override
    public List<Entity> computeCollidingEntities(VoxelShape voxelShape, boolean ignoreEntityHasCollision) {
        var result = new LinkedList<Entity>();
        entityAABBTree.detectOverlaps(voxelShape.unionAABB(), entity -> {
            if (!ignoreEntityHasCollision && !entity.hasEntityCollision()) return false;
            return voxelShape.intersectsAABB(entity.getOffsetAABB());
        }, result);
        return result;
    }

    @Override
    public List<Entity> getCachedEntityCollidingResult(Entity entity, boolean ignoreEntityHasCollision) {
        if (!entity.hasEntityCollision()) return Collections.emptyList();
        var result = entityCollisionCache.getOrDefault(entity.getRuntimeId(), Collections.emptyList());
        if (!ignoreEntityHasCollision) result.removeIf(e -> !e.hasEntityCollision());
        return result;
    }

    protected record ClientMove(EntityPlayer player, Location3fc newLoc) {}
}
