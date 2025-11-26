package org.allaymc.server.world.physics;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerMoveEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.physics.AABBOverlapFilter;
import org.allaymc.api.world.physics.EntityPhysicsEngine;
import org.allaymc.server.AllayServer;
import org.allaymc.server.block.component.BlockLiquidBaseComponentImpl;
import org.allaymc.server.block.impl.BlockLiquidBehaviorImpl;
import org.allaymc.server.datastruct.aabb.AABBTree;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerPhysicsComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jctools.maps.NonBlockingHashMapLong;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Math.*;
import static org.allaymc.api.block.type.BlockTypes.AIR;

/**
 * Special thanks to <a href="https://www.mcpk.wiki">MCPK Wiki</a>
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEntityPhysicsEngine implements EntityPhysicsEngine {

    public static final double MOTION_THRESHOLD;
    public static final double BLOCK_COLLISION_MOTION;
    /**
     * When the min distance of entity to the collision shape of block is smaller than FAT_AABB_MARGIN,
     * the entity will be considered as collided with the block. This is used to prevent floating point
     * number overflow problem and is what the vanilla actually does.
     * <p>
     * This value is actually the value of the client-side y coordinate decimal point when the player
     * stands on the full block (such as the grass block).
     */
    public static final double FAT_AABB_MARGIN = 0.00001;

    private static final double WATER_FLOW_MOTION = 0.014;
    private static final double LAVA_FLOW_MOTION = 0.002333333;
    private static final double LAVA_FLOW_MOTION_IN_NETHER = 0.007;

    static {
        var settings = AllayServer.getSettings().entitySettings().physicsEngineSettings();
        MOTION_THRESHOLD = settings.motionThreshold();
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

    public AllayEntityPhysicsEngine(Dimension dimension) {
        this.dimension = dimension;
    }

    public void tick() {
        handleClientMoveQueue();
        cacheEntityCollisionResult();
        var updatedEntities = new NonBlockingHashMapLong<Entity>();
        entities.values().parallelStream().forEach(entity -> {
            if (!(entity instanceof EntityPhysicsComponent physicsComponent)) {
                return;
            }

            if (!physicsComponent.computeMovementServerSide() ||
                !entity.isCurrentChunkLoaded() ||
                // Invisible bedrock is present at minHeight - 40
                entity.getLocation().y() < dimension.getDimensionInfo().minHeight() - 40) {
                return;
            }

            var collidedBlocks = dimension.getCollidingBlockStates(entity.getOffsetAABB());
            if (collidedBlocks == null || !physicsComponent.computeBlockCollisionMotion()) {
                // The entity is not stuck in the block
                if (physicsComponent.computeEntityCollisionMotion()) {
                    computeEntityCollisionMotion(entity);
                }

                var hasLiquidMotion = false;
                if (physicsComponent.computeLiquidMotion()) {
                    hasLiquidMotion = computeLiquidMotion(entity);
                }

                // We should always check the threshold for motion after we modified it
                physicsComponent.setMotion(checkMotionThreshold(new Vector3d(physicsComponent.getMotion())));
                if (physicsComponent.applyMotion()) {
                    updatedEntities.put(entity.getRuntimeId(), entity);
                }

                // Update and set motion again
                physicsComponent.setMotion(checkMotionThreshold(physicsComponent.updateMotion(hasLiquidMotion)));
            } else if (physicsComponent.computeBlockCollisionMotion()) {
                // The entity is stuck in the block. Do not calculate other motions exclude block collision motion
                computeBlockCollisionMotion(entity, collidedBlocks);
                physicsComponent.setMotion(checkMotionThreshold(new Vector3d(physicsComponent.getMotion())));
                if (forceApplyMotion(entity)) {
                    updatedEntities.put(entity.getRuntimeId(), entity);
                }
            }
        });
        updatedEntities.values().forEach(entityAABBTree::update);
    }

    protected void cacheEntityCollisionResult() {
        entityCollisionCache.clear();
        var map = new ConcurrentHashMap<Entity, List<Entity>>();
        // Compute colliding entities in parallel, because computeCollidingEntities()
        // will be an expensive method if there are a lot of entities. Method
        // computeCollidingEntities() should be safe to call in parallel
        entities.values().parallelStream().forEach(entity -> {
            var collidedEntities = computeCollidingEntities(entity, true);
            if (collidedEntities.isEmpty()) {
                return;
            }
            map.put(entity, collidedEntities);
        });
        map.forEach((entity, collidedEntities) -> {
            // These two operations is not thread-safe, so simply do them synchronously
            // as the two operations shouldn't be slow
            entityCollisionCache.put(entity.getRuntimeId(), collidedEntities);
            collidedEntities.forEach(entity::onCollideWithEntity);
        });
    }

    protected void computeBlockCollisionMotion(Entity entity, BlockState[][][] collidedBlocks) {
        // 1. Find out the block state which entity collided most
        var aabb = entity.getOffsetAABB();
        var minX = (int) floor(aabb.minX());
        var minY = (int) floor(aabb.minY());
        var minZ = (int) floor(aabb.minZ());
        int targetX = 0, targetY = 0, targetZ = 0;
        double volume = 0;
        for (int offsetX = 0, length0 = collidedBlocks.length; offsetX < length0; offsetX++) {
            var sub1 = collidedBlocks[offsetX];
            for (int offsetY = 0, length1 = sub1.length; offsetY < length1; offsetY++) {
                var sub2 = sub1[offsetY];
                for (int offsetZ = 0, length2 = sub2.length; offsetZ < length2; offsetZ++) {
                    var blockState = sub2[offsetZ];
                    if (blockState == null) {
                        continue;
                    }

                    var currentX = minX + offsetX;
                    var currentY = minY + offsetY;
                    var currentZ = minZ + offsetZ;
                    var intersection = blockState
                            .getBlockStateData()
                            .computeOffsetCollisionShape(currentX, currentY, currentZ)
                            .unionAABB()
                            .intersection(aabb);
                    var currentVolume = intersection.lengthX() * intersection.lengthY() * intersection.lengthZ();
                    if (currentVolume > volume) {
                        volume = currentVolume;
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
        double distanceSqrt = Integer.MAX_VALUE;
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
        ((EntityPhysicsComponent) entity).setMotion(mx * BLOCK_COLLISION_MOTION, my * BLOCK_COLLISION_MOTION, mz * BLOCK_COLLISION_MOTION);
    }

    protected void computeEntityCollisionMotion(Entity entity) {
        var collidedEntities = getCachedEntityCollidingResult(entity);
        var collisionMotion = new Vector3d(0, 0, 0);

        var location = entity.getLocation();
        var pushSpeedReduction = ((EntityPhysicsComponent) entity).getPushSpeedReduction();
        for (var other : collidedEntities) {
            // https://github.com/lovexyn0827/Discovering-Minecraft/blob/master/Minecraft%E5%AE%9E%E4%BD%93%E8%BF%90%E5%8A%A8%E7%A0%94%E7%A9%B6%E4%B8%8E%E5%BA%94%E7%94%A8/5-Chapter-5.md
            var ol = other.getLocation();
            var direction = MathUtils.normalizeIfNotZero(new Vector3d(entity.getLocation()).sub(other.getLocation(), new Vector3d()));
            var distance = max(abs(ol.x() - location.x()), abs(ol.z() - location.z()));
            if (distance <= 0.01) continue;

            var k = 0.05f * pushSpeedReduction;
            if (distance <= 1) {
                k *= MathUtils.fastDoubleInverseSqrt(distance);
            } else {
                k /= distance;
            }

            collisionMotion.add(direction.mul(k));
        }

        collisionMotion.setComponent(1, 0);
        ((EntityPhysicsComponent) entity).addMotion(collisionMotion);
    }

    /**
     * Compute the liquid motion for the entity.
     *
     * @param entity the entity to compute liquid motion
     * @return {@code true} if the entity has liquid motion, otherwise {@code false}.
     */
    protected boolean computeLiquidMotion(Entity entity) {
        var hasWaterMotion = new AtomicBoolean(false);
        var hasLavaMotion = new AtomicBoolean(false);
        var waterMotion = new Vector3d();
        var lavaMotion = new Vector3d();
        var entityY = entity.getLocation().y();

        dimension.forEachBlockStates(entity.getOffsetAABB(), 0, (x, y, z, block) -> {
            if (!(block.getBehavior() instanceof BlockLiquidBehaviorImpl liquidBehavior)) {
                return;
            }

            var flowVector = ((BlockLiquidBaseComponentImpl) liquidBehavior.getBaseComponent()).calculateFlowVector(dimension, x, y, z, block);
            if (flowVector.lengthSquared() <= 0) {
                return;
            }

            var d = BlockLiquidBaseComponent.getDepth(block) * 0.125f + y - entityY;
            if (d <= 0) {
                return;
            }
            if (d < 0.4) {
                flowVector.mul(d);
            }

            if (liquidBehavior.getBlockType().hasBlockTag(BlockTags.WATER)) {
                hasWaterMotion.set(true);
                waterMotion.add(flowVector);
            } else if (liquidBehavior.getBlockType().hasBlockTag(BlockTags.LAVA)) {
                hasLavaMotion.set(true);
                lavaMotion.add(flowVector);
            }
        });

        if (!hasWaterMotion.get() && !hasLavaMotion.get()) {
            return false;
        }

        var finalMotion = new Vector3d();
        if (hasWaterMotion.get()) {
            // Multiple water flow vector may cancel each other out and let the final motion result
            // in zero vector, so we still need to use normalizeIfNotZero() here to prevent NaN
            finalMotion.add(MathUtils.normalizeIfNotZero(waterMotion).mul(WATER_FLOW_MOTION));
        }
        if (hasLavaMotion.get()) {
            // Same to above
            finalMotion.add(MathUtils.normalizeIfNotZero(lavaMotion).mul(dimension.getDimensionInfo() == DimensionInfo.NETHER ? LAVA_FLOW_MOTION_IN_NETHER : LAVA_FLOW_MOTION));
        }

        ((EntityPhysicsComponent) entity).addMotion(finalMotion);
        return true;
    }

    protected Vector3d checkMotionThreshold(Vector3d motion) {
        if (abs(motion.x) < MOTION_THRESHOLD) motion.x = 0;
        if (abs(motion.y) < MOTION_THRESHOLD) motion.y = 0;
        if (abs(motion.z) < MOTION_THRESHOLD) motion.z = 0;
        return motion;
    }

    protected boolean forceApplyMotion(Entity entity) {
        var loc = new Location3d(entity.getLocation());
        loc.add(((EntityPhysicsComponent) entity).getMotion());
        return entity.trySetLocation(loc);
    }

    protected void handleClientMoveQueue() {
        for (var entry : clientMoveQueue.entrySet()) {
            var queue = entry.getValue();
            while (!queue.isEmpty()) {
                var clientMove = queue.poll();
                var player = clientMove.player();
                // The player may have been removed
                if (!entities.containsKey(player.getRuntimeId())) {
                    continue;
                }

                var baseComponent = ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent());
                if (baseComponent.getExpectedTeleportPos() != null) {
                    // It is possible that client move already get into the move queue before we set 'awatingTeleportACK' to true,
                    // so here we should ignore all client moves until 'awatingTeleportACK' become false.
                    continue;
                }

                var event = new PlayerMoveEvent(player, player.getLocation(), clientMove.newLoc());
                if (!event.call()) {
                    if (event.getRevertTo() != null) {
                        // Teleport player to the specified revert position.
                        player.teleport(event.getRevertTo());
                    }
                    continue;
                }

                // Calculate delta pos (motion)
                var motion = event.getTo().sub(player.getLocation(), new Vector3d());
                var physicsComponent = ((EntityPlayerPhysicsComponentImpl) ((EntityPlayerImpl) player).getPhysicsComponent());
                physicsComponent.setMotionValueOnly(motion);
                if (player.trySetLocation(clientMove.newLoc())) {
                    entityAABBTree.update(player);
                }
                // ClientMove is not calculated by the server, but we need to calculate the onGround status
                // If it's a server-calculated move, the onGround status will be calculated in applyMotion()
                var aabb = new AABBd(clientMove.player.getOffsetAABB());
                // Here we should subtract twice FAT_AABB_MARGIN, because the client pos has an extra FAT_AABB_MARGIN in y coordinate
                aabb.minY -= 2 * FAT_AABB_MARGIN;
                physicsComponent.setOnGround(dimension.getCollidingBlockStates(aabb) != null);
            }
        }
    }

    /**
     * Please call it before run tick()!
     */
    public void addEntity(Entity entity) {
        if (entities.containsKey(entity.getRuntimeId())) {
            throw new IllegalArgumentException("Entity " + entity.getRuntimeId() + " is already added!");
        }

        entities.put(entity.getRuntimeId(), entity);
        entityAABBTree.add(entity);
    }

    /**
     * Please call it before run tick()!
     */
    public void removeEntity(Entity entity) {
        if (!entities.containsKey(entity.getRuntimeId())) {
            return;
        }

        entities.remove(entity.getRuntimeId());
        entityAABBTree.remove(entity);
        entityCollisionCache.remove(entity.getRuntimeId());
    }

    /**
     * Please note that this method usually been called asynchronously <p/>
     * See {@link PacketProcessor#handleAsync(org.allaymc.api.player.Player, BedrockPacket, long)}
     */
    public void offerClientMove(EntityPlayer player, Location3dc newLoc) {
        if (!entities.containsKey(player.getRuntimeId()) || player.getLocation().equals(newLoc)) {
            return;
        }

        clientMoveQueue.computeIfAbsent(player.getRuntimeId(), $ -> new ConcurrentLinkedQueue<>()).offer(new ClientMove(player, newLoc));
    }

    @Override
    public List<Entity> computeCollidingEntities(AABBdc aabb, AABBOverlapFilter<Entity> predicate) {
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
        if (!entity.hasEntityCollision()) {
            return Collections.emptyList();
        }

        var result = entityCollisionCache.getOrDefault(entity.getRuntimeId(), Collections.emptyList());
        if (!ignoreEntityHasCollision) {
            result.removeIf(e -> !e.hasEntityCollision());
        }
        return result;
    }

    protected record ClientMove(EntityPlayer player, Location3dc newLoc) {
    }
}
