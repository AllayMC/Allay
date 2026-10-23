package org.allaymc.server.world.physics;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockLiquidBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent.LiquidState;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerMoveEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.physics.AABBOverlapFilter;
import org.allaymc.api.world.physics.EntityPhysicsEngine;
import org.allaymc.server.AllayServer;
import org.allaymc.server.block.component.liquid.BlockLiquidBaseComponentImpl;
import org.allaymc.server.block.impl.BlockLiquidBehaviorImpl;
import org.allaymc.server.datastruct.aabb.AABBTree;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerPhysicsComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jctools.maps.NonBlockingHashMapLong;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Math.*;
import static org.allaymc.api.block.type.BlockTypes.AIR;

/**
 * {@code AllayEntityPhysicsEngine} coordinates client movement, cached
 * overlaps, server movement, and deferred motion callbacks within one
 * dimension. Each tick drains queued client moves before querying overlaps,
 * then computes eligible server movements in parallel. The spatial tree
 * remains unchanged during those worker passes and is updated after movement
 * workers finish.
 *
 * <p>
 * Deferred callbacks run sequentially after tree updates, allowing projectile
 * impacts and landing consequences to observe the completed movement phase.
 * Motion below the configured threshold stays stored so later acceleration can
 * accumulate. Membership, cache storage, and the outer client-queue map are
 * ordinary mutable maps; concurrent queue contents do not make their
 * surrounding map operations safe. Failed worker joins prevent later tree or
 * callback phases without rolling back changes already made.
 *
 * Special thanks to <a href="https://www.mcpk.wiki">MCPK Wiki</a>.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayEntityPhysicsEngine implements EntityPhysicsEngine {

    /**
     * {@code MOTION_THRESHOLD} stores the configured minimum absolute axis
     * velocity in blocks per tick. The value is captured at class
     * initialization and only gates movement application.
     */
    public static final double MOTION_THRESHOLD;
    /**
     * {@code BLOCK_COLLISION_MOTION} stores configured block-escape velocity
     * in blocks per tick. The class captures it at initialization for
     * cancellable stuck-entity motion proposals.
     */
    public static final double BLOCK_COLLISION_MOTION;
    /**
     * {@code FAT_AABB_MARGIN} supplies the small block-coordinate collision
     * tolerance shared with movement clipping. Client grounding expands
     * downward by twice this margin to detect nearby supporting shapes.
     */
    public static final double FAT_AABB_MARGIN = 0.00001;

    /**
     * {@code WATER_FLOW_MOTION} scales the normalized accumulated water flow
     * into a per-pass velocity addition in blocks per tick.
     */
    private static final double WATER_FLOW_MOTION = 0.014;
    /**
     * {@code LAVA_FLOW_MOTION} scales normalized lava flow outside the Nether
     * into a per-pass velocity addition in blocks per tick.
     */
    private static final double LAVA_FLOW_MOTION = 0.002333333;
    /**
     * {@code LAVA_FLOW_MOTION_IN_NETHER} scales normalized Nether lava flow
     * into its stronger per-pass velocity addition in blocks per tick.
     */
    private static final double LAVA_FLOW_MOTION_IN_NETHER = 0.007;
    /**
     * {@code BUBBLE_COLUMN_UPWARD_ACCELERATION} increases vertical velocity
     * inside upward columns, measured in blocks per tick squared before the
     * speed cap.
     */
    private static final double BUBBLE_COLUMN_UPWARD_ACCELERATION = 0.06;
    /**
     * {@code BUBBLE_COLUMN_UPWARD_EXIT_ACCELERATION} increases vertical
     * velocity at upward column exits, measured in blocks per tick squared
     * before the exit cap.
     */
    private static final double BUBBLE_COLUMN_UPWARD_EXIT_ACCELERATION = 0.10;
    /**
     * {@code BUBBLE_COLUMN_DOWNWARD_ACCELERATION} decreases vertical velocity
     * inside downward columns, measured in blocks per tick squared before the
     * speed floor.
     */
    private static final double BUBBLE_COLUMN_DOWNWARD_ACCELERATION = 0.30;
    /**
     * {@code BUBBLE_COLUMN_DOWNWARD_EXIT_ACCELERATION} decreases vertical
     * velocity at downward column exits, measured in blocks per tick squared
     * before the exit floor.
     */
    private static final double BUBBLE_COLUMN_DOWNWARD_EXIT_ACCELERATION = 0.03;
    /**
     * {@code BUBBLE_COLUMN_UPWARD_MAX_SPEED} caps upward vertical velocity
     * within a column in blocks per tick after acceleration.
     */
    private static final double BUBBLE_COLUMN_UPWARD_MAX_SPEED = 0.70;
    /**
     * {@code BUBBLE_COLUMN_UPWARD_EXIT_MAX_SPEED} caps upward exit velocity in
     * blocks per tick after the exit acceleration is applied.
     */
    private static final double BUBBLE_COLUMN_UPWARD_EXIT_MAX_SPEED = 1.80;
    /**
     * {@code BUBBLE_COLUMN_DOWNWARD_MAX_SPEED} bounds downward column velocity
     * in blocks per tick, using a negative value for downward motion.
     */
    private static final double BUBBLE_COLUMN_DOWNWARD_MAX_SPEED = -0.30;
    /**
     * {@code BUBBLE_COLUMN_DOWNWARD_EXIT_MAX_SPEED} bounds downward exit
     * velocity in blocks per tick, using a negative value for downward motion.
     */
    private static final double BUBBLE_COLUMN_DOWNWARD_EXIT_MAX_SPEED = -0.90;

    // Capture both settings when the class initializes. Later configuration changes
    // do not update these static values, so every engine instance uses the same
    // captured movement rules.
    static {
        var settings = AllayServer.getSettings().entitySettings().physicsEngineSettings();
        MOTION_THRESHOLD = settings.motionThreshold();
        BLOCK_COLLISION_MOTION = settings.blockCollisionMotion();
    }

    /**
     * {@code entities} retains registered entities by runtime identifier. Tick
     * passes read this ordinary map while membership updates belong outside
     * those passes; it does not provide concurrent map operations.
     */
    protected Map<Long, Entity> entities = new Long2ObjectOpenHashMap<>();
    /**
     * {@code clientMoveQueue} retains per-player movement queues whose
     * contents support concurrent offers and polling. The surrounding map is
     * ordinary storage, so concurrent queue creation and map iteration are not
     * protected by those queues.
     */
    protected Map<Long, Queue<ClientMove>> clientMoveQueue = new Long2ObjectOpenHashMap<>();
    /**
     * {@code entityCollisionCache} holds overlap lists from the current
     * collision pass. Default cached access can filter these shared lists in
     * place, affecting later readers until the next pass replaces them.
     */
    protected Map<Long, List<Entity>> entityCollisionCache = new Long2ObjectOpenHashMap<>();
    /**
     * {@code entityAABBTree} indexes registered world-space bounds regardless
     * of entity-collision flags. Worker queries finish before the tick updates
     * tree entries for accepted server movement.
     */
    protected AABBTree<Entity> entityAABBTree = new AABBTree<>();
    /**
     * Dimension whose entities this engine processes.
     */
    protected Dimension dimension;

    /**
     * Creates an empty physics engine retaining the supplied dimension
     * reference. The caller supplies the dimension whose entity membership,
     * block samples, and world context all later processing will use.
     */
    public AllayEntityPhysicsEngine(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Processes one dimension physics cycle with joined parallel movement and
     * sequential deferred effects. Client moves are handled before overlap
     * caching. Registered physics entities are then considered for server
     * movement, with disabled server movement, unavailable chunks, and
     * positions below the lower cutoff skipping normal processing.
     *
     * <p>
     * Normal processing combines enabled entity pushes and liquid flow before
     * applying stored motion above the threshold, then proposes the next
     * motion through the component setter even when no movement occurred.
     * Stuck entities instead propose block-escape motion and attempt forced
     * travel using whichever vector remains stored. After workers complete
     * normally, accepted moves update the spatial tree before post-motion
     * callbacks run. A failed join skips both final phases without rollback.
     */
    public void tick() {
        handleClientMoveQueue();
        cacheEntityCollisionResult();
        var updatedEntities = new NonBlockingHashMapLong<Entity>();
        Utils.forEachInParallel(entities.values(), Server.getInstance().getComputeThreadPool(), entity -> {
            if (!(entity instanceof EntityPhysicsComponent physicsComponent)) {
                return;
            }

            if (!physicsComponent.computeMovementServerSide() ||
                !entity.isCurrentChunkLoaded() ||
                // The lower cutoff skips ordinary movement forty blocks below the
                // dimension minimum; living entities retain their separate
                // void-damage handling.
                entity.getLocation().y() < dimension.getDimensionType().getMinHeight() - 40) {

                // Any failed movement eligibility condition removes a nonliving
                // entity here; living entities are skipped without this direct
                // removal request.
                if (!(entity instanceof EntityLiving)) {
                    entity.remove();
                }

                return;
            }

            var collidedBlocks = dimension.getCollidingBlockStates(entity.getOffsetAABB());
            if (collidedBlocks == null || !physicsComponent.computeBlockCollisionMotion()) {
                if (physicsComponent.computeEntityCollisionMotion()) {
                    computeEntityCollisionMotion(entity);
                }

                var liquidState = LiquidState.NONE;
                if (physicsComponent.computeLiquidMotion()) {
                    liquidState = computeLiquidMotion(entity);
                }

                // Threshold only movement application, retaining small stored
                // forces so later motion updates can accumulate enough velocity to
                // move the entity.
                if (isAboveMotionThreshold(physicsComponent.getMotion()) && physicsComponent.applyMotion()) {
                    updatedEntities.put(entity.getRuntimeId(), entity);
                }

                // Propose the next velocity even after no displacement, allowing
                // gravity or buoyancy to accumulate while movement remains below
                // threshold.
                physicsComponent.setMotion(physicsComponent.updateMotion(liquidState));
            } else if (physicsComponent.computeBlockCollisionMotion()) {
                // Stuck processing omits ordinary pushes and flow before proposing
                // escape velocity; cancellation can leave the prior vector for
                // forced movement.
                computeBlockCollisionMotion(entity, collidedBlocks);
                if (isAboveMotionThreshold(physicsComponent.getMotion()) && forceApplyMotion(entity)) {
                    updatedEntities.put(entity.getRuntimeId(), entity);
                }
            }
        }).join();
        // Join movement workers before mutating spatial bounds, then publish all
        // moved entries before any deferred impact or landing callback observes the
        // tree.
        updatedEntities.values().forEach(entityAABBTree::update);
        updatedEntities.values().forEach(entity -> ((EntityPhysicsComponent) entity).afterApplyMotion());
    }

    /**
     * Rebuilds cached overlaps and invokes overlap callbacks before server
     * movement. Worker tasks query the unchanged spatial tree and collect
     * nonempty source lists independently of entity-collision flags. The
     * method joins those tasks before writing the ordinary cache map.
     *
     * <p>
     * Each collected source entry is published immediately before that
     * source's callbacks run. Callbacks can therefore observe an incompletely
     * repopulated cache for other sources, and their world changes do not
     * recompute the sampled overlaps in this pass. The cached lists remain
     * shared with later motion processing until another pass clears them.
     */
    protected void cacheEntityCollisionResult() {
        entityCollisionCache.clear();
        var map = new ConcurrentHashMap<Entity, List<Entity>>();
        // Query the stable tree in parallel while collecting results separately,
        // then join before publishing ordinary cache-map entries or invoking
        // overlap callbacks.
        Utils.forEachInParallel(entities.values(), Server.getInstance().getComputeThreadPool(), entity -> {
            var collidedEntities = computeCollidingEntities(entity, true);
            if (collidedEntities.isEmpty()) {
                return;
            }
            map.put(entity, collidedEntities);
        }).join();
        map.forEach((entity, collidedEntities) -> {
            // Publish each source list immediately before its callbacks on the
            // ticking thread; callback mutations must not run inside the parallel
            // tree-query pass.
            entityCollisionCache.put(entity.getRuntimeId(), collidedEntities);
            collidedEntities.forEach(entity::onCollideWithEntity);
        });
    }

    /**
     * Proposes configured escape motion for an intersecting entity through its
     * cancellable motion setter. The collision array must use the dimension
     * query layout whose vertical origin is one block below the floored entity
     * minimum. Union-box overlap volumes select one representative block.
     *
     * <p>
     * The smallest positive face penetration with an exactly-air neighbor
     * selects direction, using {@code BlockFace.VALUES} order; no eligible
     * face falls back upward. The method ignores motion-setter acceptance.
     * Cancellation can leave the previous vector stored, which the tick
     * forced-location path can still apply instead of the proposed escape
     * vector.
     */
    protected void computeBlockCollisionMotion(Entity entity, BlockState[][][] collidedBlocks) {
        var aabb = entity.getOffsetAABB();
        var minX = (int) floor(aabb.minX());
        // Match the dimension collision-array origin, which includes the cell below
        // the entity for collision shapes extending above their own block.
        var minY = (int) floor(aabb.minY()) - 1;
        var minZ = (int) floor(aabb.minZ());
        int targetX = 0, targetY = 0, targetZ = 0;
        BlockState targetBlockState = null;
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
                        targetBlockState = blockState;
                    }
                }
            }
        }

        BlockFace movingDirection = null;
        if (targetBlockState != null) {
            var targetAABB = targetBlockState
                    .getBlockStateData()
                    .computeOffsetCollisionShape(targetX, targetY, targetZ)
                    .unionAABB();
            // Keep penetration entries in BlockFace.VALUES order so each distance
            // selects the corresponding neighboring cell rather than another face
            // escape direction.
            double[] penetrations = {
                    aabb.maxY() - targetAABB.minY(), // DOWN
                    targetAABB.maxY() - aabb.minY(), // UP
                    aabb.maxZ() - targetAABB.minZ(), // NORTH
                    targetAABB.maxZ() - aabb.minZ(), // SOUTH
                    aabb.maxX() - targetAABB.minX(), // WEST
                    targetAABB.maxX() - aabb.minX(), // EAST
            };
            double minPenetration = Double.MAX_VALUE;
            for (int i = 0; i < BlockFace.VALUES.length; i++) {
                var penetration = penetrations[i];
                if (penetration > 0 && penetration < minPenetration) {
                    var blockFace = BlockFace.VALUES[i];
                    var offsetVec = blockFace.offsetPos(targetX, targetY, targetZ);
                    if (dimension.getBlockState(offsetVec).getBlockType() == AIR) {
                        minPenetration = penetration;
                        movingDirection = blockFace;
                    }
                }
            }
        }
        // No neighboring air face can leave the entity without an escape choice;
        // use upward motion even when the inspected neighbors cannot provide one.
        if (movingDirection == null) movingDirection = BlockFace.UP;

        var directionOffset = movingDirection.getOffset();
        var mx = directionOffset.x();
        var my = directionOffset.y();
        var mz = directionOffset.z();
        ((EntityPhysicsComponent) entity).setMotion(mx * BLOCK_COLLISION_MOTION, my * BLOCK_COLLISION_MOTION, mz * BLOCK_COLLISION_MOTION);
    }

    /**
     * Adds horizontal separation motion from the cached collision candidates.
     * The source collision flag and cached filtering decide which candidates
     * remain. Direction uses normalized three-dimensional displacement, while
     * strength uses the maximum horizontal axis separation rather than
     * Euclidean distance.
     *
     * <p>
     * Very small horizontal separation contributes nothing. Inside one block,
     * an inverse-square-root approximation scales the push; farther away,
     * inverse separation scales it instead. The source push-speed reduction
     * affects every contribution, and the accumulated vertical component is
     * discarded before the result is added through the motion component.
     */
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
            // Ignore almost coincident horizontal positions so the distance-scaled
            // push does not amplify an unstable separation direction.
            if (distance <= 0.01) continue;

            // Scale the base push by the source push-speed multiplier before the
            // near or far separation rule changes its strength.
            var k = 0.05f * pushSpeedReduction;
            if (distance <= 1) {
                // The near branch uses the inverse-square-root approximation while
                // the far branch weakens push by inverse horizontal separation.
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
     * Samples overlapped liquid blocks, adds accumulated flow, and applies any
     * unambiguous bubble-column response. Water and lava presence are recorded
     * separately from nonzero flow, so a stationary liquid still selects later
     * liquid physics. Effective liquid depth is converted from eighth-block
     * units before shallow flow weighting.
     *
     * <p>
     * Each liquid type accumulates its own direction before normalization and
     * fixed-strength addition, allowing opposing samples to cancel. Nether
     * lava uses a separate strength. Bubble columns reset fall distance and
     * record direction and exit observations; mixed upward and downward
     * observations suppress bubble acceleration, while an air block above any
     * sampled column selects exit constants. The returned state reports both
     * observed liquid kinds.
     */
    protected LiquidState computeLiquidMotion(Entity entity) {
        var inWater = new AtomicBoolean(false);
        var inLava = new AtomicBoolean(false);
        var waterMotion = new Vector3d();
        var lavaMotion = new Vector3d();
        var upwardBubbleColumn = new AtomicBoolean(false);
        var downwardBubbleColumn = new AtomicBoolean(false);
        var bubbleColumnExit = new AtomicBoolean(false);
        var entityY = entity.getLocation().y();

        dimension.forEachBlockStates(entity.getOffsetAABB(), 0, (x, y, z, block) -> {
            if (block.getBlockType() == BlockTypes.BUBBLE_COLUMN) {
                inWater.set(true);
                ((EntityPhysicsComponent) entity).resetFallDistance();
                if (block.getPropertyValue(BlockPropertyTypes.DRAG_DOWN)) {
                    downwardBubbleColumn.set(true);
                } else {
                    upwardBubbleColumn.set(true);
                }
                if (dimension.getBlockState(x, y + 1, z).getBlockType() == AIR) {
                    bubbleColumnExit.set(true);
                }
                return;
            }

            if (!(block.getBehavior() instanceof BlockLiquidBehaviorImpl liquidBehavior)) {
                return;
            }

            if (liquidBehavior.getBlockType().hasBlockTag(BlockTags.WATER)) {
                inWater.set(true);
            } else if (liquidBehavior.getBlockType().hasBlockTag(BlockTags.LAVA)) {
                inLava.set(true);
            }

            var flowVector = ((BlockLiquidBaseComponentImpl) liquidBehavior.getBaseComponent()).calculateFlowVector(dimension, x, y, z, block);
            if (flowVector.lengthSquared() <= 0) {
                return;
            }

            // Convert effective eighth-block depth into a surface height relative
            // to the entity feet before weighting shallow flow samples.
            var d = BlockLiquidBaseComponent.getDepth(block) * 0.125f + y - entityY;
            if (d <= 0) {
                return;
            }
            // Shallow contact scales flow contribution before directions are
            // combined across the entity overlap volume.
            if (d < 0.4) {
                flowVector.mul(d);
            }

            if (liquidBehavior.getBlockType().hasBlockTag(BlockTags.WATER)) {
                waterMotion.add(flowVector);
            } else if (liquidBehavior.getBlockType().hasBlockTag(BlockTags.LAVA)) {
                lavaMotion.add(flowVector);
            }
        });

        var finalMotion = new Vector3d();
        if (waterMotion.lengthSquared() > 0) {
            // Normalize the summed water direction only after accumulation so
            // opposing samples can cancel rather than each contributing a
            // full-strength push.
            finalMotion.add(MathUtils.normalizeIfNotZero(waterMotion).mul(WATER_FLOW_MOTION));
        }
        if (lavaMotion.lengthSquared() > 0) {
            // Lava uses its own summed direction and dimension strength, preserving
            // its contribution independently from any accumulated water flow.
            finalMotion.add(MathUtils.normalizeIfNotZero(lavaMotion).mul(dimension.getDimensionType() == DimensionTypes.NETHER ? LAVA_FLOW_MOTION_IN_NETHER : LAVA_FLOW_MOTION));
        }

        if (finalMotion.lengthSquared() > 0) {
            ((EntityPhysicsComponent) entity).addMotion(finalMotion);
        }

        applyBubbleColumnMotion(entity, upwardBubbleColumn.get(), downwardBubbleColumn.get(), bubbleColumnExit.get());

        return new LiquidState(inWater.get(), inLava.get());
    }

    /**
     * Adds the vertical change selected by bubble direction and exit state.
     * Equal upward and downward observations produce no change, covering both
     * no column and conflicting column directions. Otherwise exit state
     * selects a separate acceleration and speed bound.
     *
     * <p>
     * Upward columns cap the increased vertical velocity, while downward
     * columns floor the decreased value using signed negative limits. The
     * helper adds the difference from current velocity rather than replacing
     * horizontal components. Motion-event handling can still reject or modify
     * that addition through the component API.
     */
    private void applyBubbleColumnMotion(Entity entity, boolean upward, boolean downward, boolean exitsColumn) {
        if (upward == downward) {
            return;
        }

        var physics = (EntityPhysicsComponent) entity;
        var currentMotion = physics.getMotion();
        var currentY = currentMotion.y();
        var targetY = upward
                ? Math.min(exitsColumn ? BUBBLE_COLUMN_UPWARD_EXIT_MAX_SPEED : BUBBLE_COLUMN_UPWARD_MAX_SPEED,
                currentY + (exitsColumn ? BUBBLE_COLUMN_UPWARD_EXIT_ACCELERATION : BUBBLE_COLUMN_UPWARD_ACCELERATION))
                : Math.max(exitsColumn ? BUBBLE_COLUMN_DOWNWARD_EXIT_MAX_SPEED : BUBBLE_COLUMN_DOWNWARD_MAX_SPEED,
                currentY - (exitsColumn ? BUBBLE_COLUMN_DOWNWARD_EXIT_ACCELERATION : BUBBLE_COLUMN_DOWNWARD_ACCELERATION));
        physics.addMotion(0, targetY - currentY, 0);
    }

    /**
     * Returns whether any absolute velocity component reaches the configured
     * movement threshold. Equality permits movement, while a below-threshold
     * result leaves the stored vector available for subsequent acceleration
     * instead of zeroing it.
     */
    protected boolean isAboveMotionThreshold(Vector3dc motion) {
        return abs(motion.x()) >= MOTION_THRESHOLD ||
               abs(motion.y()) >= MOTION_THRESHOLD ||
               abs(motion.z()) >= MOTION_THRESHOLD;
    }

    /**
     * Attempts a location change by adding the stored motion without ordinary
     * collision clipping. The entity location setter decides acceptance, and
     * the caller updates the spatial tree only after a successful result.
     */
    protected boolean forceApplyMotion(Entity entity) {
        var loc = new Location3d(entity.getLocation());
        loc.add(((EntityPhysicsComponent) entity).getMotion());
        return entity.trySetLocation(loc);
    }

    /**
     * Drains offered player moves before collision caching. A removed player
     * or one still awaiting teleport acknowledgement discards a polled move.
     * Cancellation can request a corrective teleport through the event revert
     * location.
     *
     * <p>
     * Accepted movement computes observed displacement from {@code
     * event.getTo()}, but placement uses the retained {@code
     * clientMove.newLoc()} reference. Those values initially refer to the same
     * location; this path does not substitute a different event reference for
     * placement. The tree updates on accepted placement. Grounding then
     * samples current player bounds expanded downward by twice the collision
     * margin, even after rejected placement. Queue map entries remain after
     * draining, and a thrown handler consumes the polled move before stopping
     * this pass.
     */
    protected void handleClientMoveQueue() {
        for (var entry : clientMoveQueue.entrySet()) {
            var queue = entry.getValue();
            ClientMove clientMove;
            while ((clientMove = queue.poll()) != null) {
                var player = clientMove.player();
                if (!entities.containsKey(player.getRuntimeId())) {
                    continue;
                }

                var baseComponent = ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent());
                if (baseComponent.getExpectedTeleportPos() != null) {
                    // A queued move may predate the teleport expectation; discard
                    // it until acknowledgement clears that expectation so stale
                    // input cannot undo teleport positioning.
                    continue;
                }

                var event = new PlayerMoveEvent(player, player.getLocation(), clientMove.newLoc());
                if (!event.call()) {
                    if (event.getRevertTo() != null) {
                        // Cancellation can supply a corrective teleport
                        // destination, reconciling the client position before
                        // another queued move is considered.
                        player.teleport(event.getRevertTo());
                    }
                    continue;
                }

                var motion = event.getTo().sub(player.getLocation(), new Vector3d());
                var physicsComponent = ((EntityPlayerPhysicsComponentImpl) ((EntityPlayerImpl) player).getPhysicsComponent());
                physicsComponent.setMotionValueOnly(motion);
                if (player.trySetLocation(clientMove.newLoc())) {
                    entityAABBTree.update(player);
                }
                // Client travel bypasses server applyMotion, so sample supporting
                // collision here before publishing grounded state and its possible
                // landing consequences.
                var aabb = new AABBd(clientMove.player.getOffsetAABB());
                // Expand downward by twice the shared clipping tolerance so a
                // client standing just above a surface still finds its supporting
                // collision shape.
                aabb.minY -= 2 * FAT_AABB_MARGIN;
                physicsComponent.setOnGround(dimension.getCollidingBlockStates(aabb) != null);
            }
        }
    }

    /**
     * Registers an entity in the runtime map and spatial tree before ticking
     * begins. A duplicate runtime identifier throws {@code
     * IllegalArgumentException}, and a later tree failure does not undo the
     * map insertion.
     */
    public void addEntity(Entity entity) {
        if (entities.containsKey(entity.getRuntimeId())) {
            throw new IllegalArgumentException("Entity " + entity.getRuntimeId() + " is already added!");
        }

        entities.put(entity.getRuntimeId(), entity);
        entityAABBTree.add(entity);
    }

    /**
     * Removes an existing runtime entry, spatial bounds, and its own cached
     * overlap list outside ticking. An absent entry is ignored; queued client
     * moves and references inside other cached lists remain until later
     * processing.
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
     * Offers a movement record for a currently registered player whose
     * location differs from {@code newLoc}. The record retains both references
     * without copying, so callers must keep the supplied location stable until
     * polling finishes. The next client-move pass decides whether removal or
     * teleport acknowledgement makes it obsolete.
     *
     * <p>
     * Network processing can invoke this method asynchronously, but only the
     * queue contents use concurrent storage. Membership lookup and per-player
     * queue creation use ordinary maps without an internal lock, so this
     * method does not establish thread safety for those surrounding
     * operations. It neither applies movement nor updates the spatial tree.
     */
    public void offerClientMove(EntityPlayer player, Location3dc newLoc) {
        if (!entities.containsKey(player.getRuntimeId()) || player.getLocation().equals(newLoc)) {
            return;
        }

        clientMoveQueue.computeIfAbsent(player.getRuntimeId(), $ -> new ConcurrentLinkedQueue<>()).offer(new ClientMove(player, newLoc));
    }

    /**
     * Returns a new mutable list of indexed overlaps accepted by {@code
     * predicate}. The query does not apply extra collision-flag filtering and
     * requires tree membership and bounds to remain stable during traversal.
     */
    @Override
    public List<Entity> computeCollidingEntities(AABBdc aabb, AABBOverlapFilter<Entity> predicate) {
        var result = new LinkedList<Entity>();
        entityAABBTree.detectOverlaps(aabb, predicate, result);
        return result;
    }

    /**
     * Returns a new mutable list of entities intersecting the actual voxel
     * shape. The shape union box first narrows the spatial-tree candidates,
     * and an optional entity-collision flag check precedes the detailed shape
     * intersection. Source exclusion is not implicit in this overload.
     *
     * <p>
     * Passing {@code ignoreEntityHasCollision} as {@code true} retains
     * collision-disabled candidates for the same geometric test. The method
     * reads current entity bounds during filtering, so callers must keep the
     * tree and participating bounds stable for a coherent query. The returned
     * list owns its container but retains the original entity references.
     */
    @Override
    public List<Entity> computeCollidingEntities(VoxelShape voxelShape, boolean ignoreEntityHasCollision) {
        var result = new LinkedList<Entity>();
        entityAABBTree.detectOverlaps(voxelShape.unionAABB(), entity -> {
            if (!ignoreEntityHasCollision && !entity.hasEntityCollision()) return false;
            return voxelShape.intersectsAABB(entity.getOffsetAABB());
        }, result);
        return result;
    }

    /**
     * Returns the shared cached candidate list or an empty list for the source
     * entity. A source with collision disabled returns empty regardless of
     * {@code ignoreEntityHasCollision}, and an uncached source also has no
     * candidates.
     *
     * <p>
     * When candidate collision flags are respected, this method removes
     * disabled candidates from the cached list itself. Later calls that ignore
     * flags cannot recover those removed entries until the cache is rebuilt.
     * Callers must leave the returned storage unchanged and coordinate access
     * with cache rebuilding rather than treating this read-like method as an
     * immutable snapshot.
     */
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

    /**
     * {@code ClientMove} retains one player and proposed location until the
     * movement queue polls it.
     *
     * @param player retained player reference whose current registration and
     *     teleport state are checked when the move is polled
     * @param newLoc retained proposed location; callers must keep its
     *     coordinates and dimension stable until the queued record is consumed
     */
    protected record ClientMove(EntityPlayer player, Location3dc newLoc) {
    }
}
