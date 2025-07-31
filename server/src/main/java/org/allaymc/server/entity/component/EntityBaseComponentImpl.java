package org.allaymc.server.entity.component;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.doubles.DoubleBooleanImmutablePair;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityStatus;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.metadata.Metadata;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.event.entity.*;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.permission.Permissible;
import org.allaymc.api.permission.PermissionGroup;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNbtUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.entity.component.event.*;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static org.allaymc.api.block.component.data.BlockStateData.DEFAULT_FRICTION;
import static org.allaymc.api.utils.AllayNbtUtils.readVector2f;
import static org.allaymc.api.utils.AllayNbtUtils.readVector3f;
import static org.allaymc.server.world.service.AllayEntityPhysicsService.FAT_AABB_MARGIN;
import static org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket.Flag.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityBaseComponentImpl implements EntityBaseComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    // This tag is also used in EntityPlayerNetworkComponentImpl, so make it public for reuse
    public static final String TAG_POS = "Pos";
    protected static final String TAG_IDENTIFIER = "identifier";
    protected static final String TAG_ON_GROUND = "OnGround";
    protected static final String TAG_MOTION = "Motion";
    protected static final String TAG_ROTATION = "Rotation";
    protected static final String TAG_TAGS = "Tags";
    protected static final String TAG_ACTIVE_EFFECTS = "ActiveEffects";
    protected static final String TAG_UNIQUE_ID = "UniqueID";
    protected static final String TAG_PDC = "PDC";

    // Constants used in physics calculation
    protected static final DoubleBooleanImmutablePair EMPTY_FLOAT_BOOLEAN_PAIR = new DoubleBooleanImmutablePair(0, false);
    protected static final double STEPPING_OFFSET = 0.05;
    protected static final int X = 0;
    protected static final int Y = 1;
    protected static final int Z = 2;

    protected static final int DEFAULT_DEAD_TIMER = 20;
    // NOTICE: the runtime id is counted from 1 not 0
    protected static final AtomicLong RUNTIME_ID_COUNTER = new AtomicLong(1);
    protected static final CommandOriginData ENTITY_COMMAND_ORIGIN_DATA = new CommandOriginData(CommandOriginType.ENTITY, UUID.randomUUID(), "", 0);

    @Getter
    protected final Location3d location;
    protected final Location3d locationLastSent;
    @Getter
    protected final long runtimeId;
    @Getter
    protected final Metadata metadata;
    @Getter
    protected PermissionGroup permissionGroup;
    // Will be reset in method loadUniqueId()
    @Getter
    protected long uniqueId = Long.MAX_VALUE;
    @Manager
    protected ComponentManager manager;
    @ComponentObject
    protected Entity thisEntity;
    @Dependency(optional = true)
    protected EntityAttributeComponent attributeComponent;
    @Getter
    protected EntityType<? extends Entity> entityType;
    protected Map<Long, EntityPlayer> viewers;
    protected Map<EffectType, EffectInstance> effects;
    @Getter
    protected Vector3d motion = new Vector3d();
    @Getter
    protected Vector3d lastMotion = new Vector3d();
    @Getter
    protected boolean onGround = true;
    @Getter
    protected EntityStatus status;
    protected int deadTimer;
    @Getter
    protected double fallDistance;
    @Getter
    @Setter
    protected String displayName;
    protected Set<String> tags;
    @Getter
    @Setter
    protected PersistentDataContainer persistentDataContainer;

    public EntityBaseComponentImpl(EntityInitInfo info) {
        this.location = new Location3d(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, info.dimension());
        this.locationLastSent = new Location3d(0, 0, 0, null);
        this.runtimeId = RUNTIME_ID_COUNTER.getAndIncrement();
        this.metadata = new Metadata();
        this.entityType = info.getEntityType();
        this.viewers = new Long2ObjectOpenHashMap<>();
        this.effects = new HashMap<>();
        this.status = EntityStatus.DESPAWNED;
        this.tags = new HashSet<>();
        this.persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);
        setDisplayName(entityType.getIdentifier().toString());
    }

    @OnInitFinish
    public void onInitFinish(EntityInitInfo initInfo) {
        loadNBT(initInfo.nbt());
        initMetadata();
        initPermissionGroup();
    }

    protected void initMetadata() {
        metadata.set(EntityDataTypes.PLAYER_INDEX, 0);
        metadata.set(EntityDataTypes.AIR_SUPPLY, (short) 300);
        metadata.set(EntityDataTypes.AIR_SUPPLY_MAX, (short) 300);
        metadata.set(EntityFlag.HAS_GRAVITY, true);
        metadata.set(EntityFlag.HAS_COLLISION, true);
        metadata.set(EntityFlag.CAN_CLIMB, true);
        metadata.set(EntityFlag.BREATHING, true);
        updateHitBoxAndCollisionBoxMetadata();
    }

    protected void initPermissionGroup() {
        this.permissionGroup = PermissionGroup.create("Permission group for entity " + uniqueId, Set.of(), PermissionGroups.OPERATOR, false);
    }

    @Override
    public Permissible getPermissible() {
        return thisEntity;
    }

    protected NbtMap buildAABBTag() {
        var aabb = getAABB();
        return NbtMap.builder()
                .putFloat("MinX", 0)
                .putFloat("MinY", 0)
                .putFloat("MinZ", 0)
                .putFloat("MaxX", (float) (aabb.maxX() - aabb.minX()))
                .putFloat("MaxY", (float) (aabb.maxY() - aabb.minY()))
                .putFloat("MaxZ", (float) (aabb.maxZ() - aabb.minZ()))
                .putFloat("PivotX", 0)
                .putFloat("PivotY", 0)
                .putFloat("PivotZ", 0)
                .build();
    }

    protected void updateHitBoxAndCollisionBoxMetadata() {
        metadata.set(EntityDataTypes.HITBOX, buildAABBTag());
        var aabb = getAABB();
        metadata.set(EntityDataTypes.COLLISION_BOX,
                Vector3f.from(
                        aabb.maxX() - aabb.minX(),
                        aabb.maxY() - aabb.minY(),
                        aabb.maxZ() - aabb.minZ())
        );
    }

    public void tick(long currentTick) {
        checkDead();
        tickEffects();
        tickBreathe();
        computeAndNotifyCollidedBlocks();

        manager.callEvent(new CEntityTickEvent(currentTick));
    }

    protected void computeAndNotifyCollidedBlocks() {
        var aabb = getOffsetAABB().expand(2 * FAT_AABB_MARGIN);
        var dimension = getDimension();
        dimension.forEachBlockStates(aabb, 0, (x, y, z, blockState) -> {
            var block = new BlockStateWithPos(blockState, new Position3i(x, y, z, dimension), 0);

            if (blockState.getBlockStateData().collisionShape().translate(x, y, z).intersectsAABB(aabb)) {
                this.onCollideWithBlock(block);
                blockState.getBehavior().onCollideWithEntity(block, thisEntity);
            }

            // Check if the entity is inside block
            if (blockState.getBlockStateData().shape().translate(x, y, z).intersectsAABB(aabb)) {
                blockState.getBehavior().onEntityInside(block, thisEntity);
                this.onInsideBlock(block);
            }
        });
    }

    @Override
    public Vector3d updateMotion(boolean hasLiquidMotion) {
        var blockStateStandingOn = this.getBlockStateStandingOn();
        var slipperinessMultiplier = 1.0;
        if (!hasLiquidMotion) {
            // Entity that has liquid motion won't be affected by the friction of the block it stands on
            slipperinessMultiplier = blockStateStandingOn != null ? blockStateStandingOn.getBlockStateData().friction() : DEFAULT_FRICTION;
        }
        return new Vector3d(
                motion.x() * slipperinessMultiplier * (1 - this.getDragFactorOnGround()),
                (motion.y() - (this.hasGravity() ? this.getGravity() : 0f)) * (1 - this.getDragFactorInAir()),
                motion.z() * slipperinessMultiplier * (1 - this.getDragFactorOnGround())
        );
    }

    @Override
    public boolean applyMotion() {
        if (motion.lengthSquared() == 0) {
            return false;
        }

        var pos = new Location3d(location);
        var mx = motion.x();
        var my = motion.y();
        var mz = motion.z();
        var aabb = this.getOffsetAABB();

        // First move along the Y axis
        var yResult = moveAlongAxisAndStopWhenCollision(aabb, my, pos, Y);
        my = yResult.left();
        var isOnGround = yResult.right();

        if (abs(mx) >= abs(mz)) {
            // First handle the X axis, then handle the Z axis
            mx = applyMotion0(this.getStepHeight(), pos, mx, aabb, isOnGround, X);
            mz = applyMotion0(this.getStepHeight(), pos, mz, aabb, isOnGround, Z);
        } else {
            mz = applyMotion0(this.getStepHeight(), pos, mz, aabb, isOnGround, Z);
            mx = applyMotion0(this.getStepHeight(), pos, mx, aabb, isOnGround, X);
        }

        this.setMotion(new Vector3d(mx, my, mz));
        if (!pos.equals(location) && trySetLocation(pos)) {
            // Update onGround status after updated entity location
            // to make sure that some block (for example: water) can reset
            // entity's fallDistance before onFall() called
            this.setOnGround(isOnGround);
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
     * @return The remaining component of the object's movement velocity along the specified axis after considering possible collisions and intersections.
     */
    private double applyMotion0(double stepHeight, Location3d pos, double motion, AABBd aabb, boolean enableStepping, int axis) {
        if (motion == 0) return motion;
        checkAxis(axis);

        var resultAABB = new AABBd(aabb);
        var resultPos = new Vector3d(pos);

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
     * @return A pair containing the remaining movement distance along the axis after collision detection (Double)
     * and a boolean indicating whether a collision occurred (Boolean) or whether the entity will be on ground (if axis == Y).
     * If no movement was specified (motion = 0), an empty pair is returned.
     * @throws IllegalArgumentException if an invalid axis is provided.
     */
    private Pair<Double, Boolean> moveAlongAxisAndStopWhenCollision(AABBd aabb, double motion, Vector3d recorder, @Range(from = X, to = Z) int axis) {
        if (motion == 0) {
            return EMPTY_FLOAT_BOOLEAN_PAIR;
        }
        checkAxis(axis);

        // `extAABBInAxis` is the extended aabb in the axis which is used to check for block collision,
        // and the direction of the axis is determined by the sign of `motion`
        var extAABBInAxis = new AABBd(aabb);

        // Move towards the negative(motion < 0) or positive(motion > 0) axis direction
        var shouldTowardsNegative = motion < 0;
        switch (axis) {
            case X -> {
                var lengthX = extAABBInAxis.lengthX();
                extAABBInAxis.minX += shouldTowardsNegative ? motion : lengthX;
                extAABBInAxis.maxX += shouldTowardsNegative ? -lengthX : motion;
            }
            case Y -> {
                var lengthY = extAABBInAxis.lengthY();
                extAABBInAxis.minY += shouldTowardsNegative ? motion : lengthY;
                extAABBInAxis.maxY += shouldTowardsNegative ? -lengthY : motion;
            }
            case Z -> {
                var lengthZ = extAABBInAxis.lengthZ();
                extAABBInAxis.minZ += shouldTowardsNegative ? motion : lengthZ;
                extAABBInAxis.maxZ += shouldTowardsNegative ? -lengthZ : motion;
            }
            default -> throw new IllegalArgumentException("Invalid axis provided");
        }

        // Do not use dimension.isAABBInDimension(extendX|Y|Z) because entity should be able to move even if y > maxHeight
        if (notValidEntityArea(extAABBInAxis)) {
            return EMPTY_FLOAT_BOOLEAN_PAIR;
        }

        // `deltaInAxis` is the actual movement distance along the axis direction, and if there is no collision, this distance is equal to `motion`
        var deltaInAxis = motion;
        var collision = false;

        var blocks = getDimension().getCollidingBlockStates(extAABBInAxis);
        if (blocks != null) {
            // There is a collision if `blocks` is not null
            if (axis == Y) {
                // When the axis is Y, `collision` indicates whether the entity will be on the ground
                collision = shouldTowardsNegative;
            } else {
                collision = true;
            }

            var extAABBStartCoordinate = shouldTowardsNegative ? extAABBInAxis.getMax(axis) : extAABBInAxis.getMin(axis);
            var collisionCoordinate = computeCollisionCoordinate(aabb, extAABBInAxis, blocks, axis, shouldTowardsNegative);
            // abs(collisionCoordinate) != Double.MAX_VALUE means that the entity is stuck into the blocks. Collision
            // coordinate cannot being calculated because blocks that are intersected with the entity will be ignored
            if (abs(collisionCoordinate) != Double.MAX_VALUE) {
                deltaInAxis = abs(extAABBStartCoordinate - collisionCoordinate);
            }

            // Make a certain distance (FAT_AABB_MARGIN) between the entity and the blocks
            if (deltaInAxis < FAT_AABB_MARGIN) {
                deltaInAxis = 0;
            } else {
                deltaInAxis -= FAT_AABB_MARGIN;
            }

            // The actual movement distance should be negative if the entity moves towards the negative axis direction
            if (shouldTowardsNegative) {
                deltaInAxis = -deltaInAxis;
            }

            motion = 0;
        }

        if (deltaInAxis != 0) {
            // Move the collision box
            switch (axis) {
                case X -> aabb.translate(deltaInAxis, 0, 0);
                case Y -> aabb.translate(0, deltaInAxis, 0);
                default -> aabb.translate(0, 0, deltaInAxis);
            }
            // Update the coordinates
            recorder.setComponent(axis, recorder.get(axis) + deltaInAxis);
        }

        return new DoubleBooleanImmutablePair(motion, collision);
    }

    private void checkAxis(int axis) {
        if (axis < X || axis > Z) {
            throw new IllegalArgumentException("Invalid axis: " + axis);
        }
    }

    private boolean notValidEntityArea(AABBd extendAABB) {
        return !(extendAABB.minY >= getDimension().getDimensionInfo().minHeight()) &&
               !getDimension().getChunkService().isChunkLoaded((int) extendAABB.minX >> 4, (int) extendAABB.minZ >> 4) &&
               !getDimension().getChunkService().isChunkLoaded((int) extendAABB.maxX >> 4, (int) extendAABB.maxZ >> 4);
    }

    private boolean tryStepping(Vector3d pos, AABBd aabb, double stepHeight, boolean positive, boolean xAxis) {
        var offset = positive ? STEPPING_OFFSET : -STEPPING_OFFSET;
        var offsetAABB = aabb.translate(xAxis ? offset : 0, 0, xAxis ? 0 : offset, new AABBd());
        var recorder = new Vector3d();
        moveAlongAxisAndStopWhenCollision(offsetAABB, stepHeight, recorder, Y);
        moveAlongAxisAndStopWhenCollision(offsetAABB, -stepHeight, recorder, Y);
        if (recorder.y == 0 || getDimension().getCollidingBlockStates(offsetAABB) != null) {
            return false;
        } else {
            aabb.set(offsetAABB.translate(xAxis ? -offset : 0, 0, xAxis ? 0 : -offset));
            pos.add(recorder);
            return true;
        }
    }

    private double computeCollisionCoordinate(AABBdc entityAABB, AABBdc extAABBInAxis, BlockState[][][] blocks, @Range(from = X, to = Z) int axis, boolean shouldTowardNegative) {
        checkAxis(axis);
        double coordinate = shouldTowardNegative ? -Double.MAX_VALUE : Double.MAX_VALUE;

        for (int ox = 0; ox < blocks.length; ox++) {
            var sub1 = blocks[ox];
            for (int oy = 0; oy < sub1.length; oy++) {
                var sub2 = sub1[oy];
                for (int oz = 0; oz < sub2.length; oz++) {
                    var blockState = sub2[oz];
                    if (blockState == null) {
                        continue;
                    }

                    var shape = blockState.getBlockStateData().computeOffsetCollisionShape(floor(extAABBInAxis.minX()) + ox, floor(extAABBInAxis.minY()) + oy, floor(extAABBInAxis.minZ()) + oz);
                    if (shape.intersectsAABB(entityAABB)) {
                        // Ignore the blocks that collided with the entity
                        continue;
                    }

                    var solids = shape.getSolids();
                    for (var solid : solids) {
                        if (!solid.intersectsAABB(extAABBInAxis)) {
                            // This solid part is not intersected with `extAABBInAxis`, ignore it
                            continue;
                        }

                        double current;
                        if (shouldTowardNegative) {
                            current = solid.getMax(axis);
                            if (current > coordinate) {
                                coordinate = current;
                            }
                        } else {
                            current = solid.getMin(axis);
                            if (current < coordinate) {
                                coordinate = current;
                            }
                        }
                    }
                }
            }
        }

        return coordinate;
    }

    @Override
    public boolean trySetLocation(Location3dc newLocation) {
        var event = new EntityMoveEvent(thisEntity, location, newLocation);
        if (!event.call()) {
            return false;
        }

        newLocation = event.getTo();
        this.setLocation(newLocation);
        this.broadcastMoveToViewers(newLocation, false);
        return true;
    }

    protected void tickBreathe() {
        if (!getMetadata().has(EntityDataTypes.AIR_SUPPLY)) {
            return;
        }

        short airSupply = getMetadata().get(EntityDataTypes.AIR_SUPPLY);
        short airSupplyMax = getMetadata().get(EntityDataTypes.AIR_SUPPLY_MAX);
        short newAirSupply = airSupply;
        if (!thisEntity.canBreathe()) {
            newAirSupply = (short) (airSupply - 1);
            if (newAirSupply <= -20) {
                manager.callEvent(CEntityDrownEvent.INSTANCE);
                newAirSupply = 0;
            }
        } else if (airSupply < airSupplyMax) {
            newAirSupply = (short) (airSupply + 4);
        }
        if (airSupply != newAirSupply) {
            setAndSendEntityData(EntityDataTypes.AIR_SUPPLY, newAirSupply);
        }
    }

    protected void tickEffects() {
        if (effects.isEmpty()) {
            return;
        }

        for (var effect : effects.values().toArray(EffectInstance[]::new)) {
            effect.setDuration(effect.getDuration() - 1);
            effect.getType().onTick(thisEntity, effect);
            if (effect.getDuration() <= 0) {
                removeEffect(effect.getType());
            }
        }
    }

    protected void checkDead() {
        // TODO: move these code to EntityAttributeComponentImpl
        if (attributeComponent == null || !attributeComponent.supportAttribute(AttributeType.HEALTH)) {
            return;
        }

        if (attributeComponent.getHealth() == 0 && !isDead()) {
            onDie();
        }
        if (isDead()) {
            if (hasDeadTimer()) {
                if (deadTimer > 0) deadTimer--;
                if (deadTimer == 0) {
                    // Spawn dead particle
                    spawnDeadParticle();
                    getDimension().getEntityService().removeEntity(thisEntity);
                }
            } else {
                getDimension().getEntityService().removeEntity(thisEntity);
            }
        }
    }

    protected boolean hasDeadTimer() {
        return true;
    }

    protected void onDie() {
        new EntityDieEvent(thisEntity).call();

        manager.callEvent(CEntityDieEvent.INSTANCE);
        setStatus(EntityStatus.DEAD);
        if (hasDeadTimer()) {
            deadTimer = DEFAULT_DEAD_TIMER;
        }

        applyEntityEvent(EntityEventType.DEATH, 0);
        effects.values().forEach(effect -> effect.getType().onEntityDies(thisEntity, effect));
        removeAllEffects();
    }

    protected void spawnDeadParticle() {
        var offsetAABB = getOffsetAABB();
        for (double x = offsetAABB.minX(); x <= offsetAABB.maxX(); x += 0.5) {
            for (double z = offsetAABB.minZ(); z <= offsetAABB.maxZ(); z += 0.5) {
                for (double y = offsetAABB.minY(); y <= offsetAABB.maxY(); y += 0.5) {
                    this.getDimension().addParticle(x, y, z, ParticleType.EXPLODE);
                }
            }
        }
    }

    @Override
    public void setLocationBeforeSpawn(Location3dc location) {
        if (!canBeSpawnedIgnoreLocation()) {
            throw new IllegalStateException("Trying to set location of an entity which cannot being spawned!");
        }

        setLocationWithoutChunkCheck(location, false);
    }

    protected void setLocationWithoutChunkCheck(Location3dc location, boolean calculateFallDistance) {
        if (MathUtils.hasNaN(location)) {
            throw new IllegalArgumentException("Trying to set the location of entity " + runtimeId + " to a new location which contains NaN: " + location);
        }

        if (calculateFallDistance && !this.onGround) {
            if (this.fallDistance < 0) {
                // Entity start falling
                this.fallDistance = 0;
            }
            // fall distance < 0 -> move up
            // fall distance > 0 -> move down
            this.fallDistance -= location.y() - this.location.y();
            tryResetFallDistance(location);
        }

        this.location.set(location);
        this.location.setYaw(location.yaw());
        this.location.setHeadYaw(location.headYaw());
        this.location.setPitch(location.pitch());
        this.location.setDimension(location.dimension());
    }

    protected void tryResetFallDistance(Location3dc location) {
        var blockState0 = location.dimension().getBlockState(location);
        var blockState1 = location.dimension().getBlockState(location, 1);
        var newEntityAABB = getAABB().translate(location, new AABBd());

        if (!blockState0.getBlockStateData().hasCollision() &&
            blockState1.getBehavior().canResetFallDamage() &&
            blockState1.getBlockStateData().computeOffsetShape(MathUtils.floor(location)).intersectsAABB(newEntityAABB)) {
            this.fallDistance = 0;
            return;
        }

        if (blockState0.getBehavior().canResetFallDamage() &&
            blockState0.getBlockStateData().computeOffsetShape(MathUtils.floor(location)).intersectsAABB(newEntityAABB)) {
            this.fallDistance = 0;
        }
    }

    @Override
    public Dimension getDimension() {
        return location.dimension();
    }

    @Override
    public void despawn() {
        getDimension().getEntityService().removeEntity(thisEntity);
    }

    public synchronized boolean setStatus(EntityStatus status) {
        if (!status.getPreviousStatuses().isEmpty() && !status.getPreviousStatuses().contains(this.status)) {
            log.warn("Trying to set status of entity {} to {} but the current status is {}", thisEntity, status, this.status);
            return false;
        }

        this.status = status;
        return true;
    }

    @Override
    public boolean canBeSpawned() {
        return location.x != Integer.MAX_VALUE &&
               location.y != Integer.MAX_VALUE &&
               location.z != Integer.MAX_VALUE &&
               location.dimension() != null &&
               canBeSpawnedIgnoreLocation();
    }

    protected boolean canBeSpawnedIgnoreLocation() {
        return status == EntityStatus.DESPAWNED;
    }

    protected void setLocation(Location3dc newLoc) {
        setLocation(newLoc, true);
    }

    protected void setLocation(Location3dc newLoc, boolean calculateFallDistance) {
        var oldChunkX = (int) this.location.x() >> 4;
        var oldChunkZ = (int) this.location.z() >> 4;
        var newChunkX = (int) newLoc.x() >> 4;
        var newChunkZ = (int) newLoc.z() >> 4;
        var oldDimension = this.location.dimension();
        var newDimension = newLoc.dimension();
        setLocationWithoutChunkCheck(newLoc, calculateFallDistance);

        if (oldChunkX == newChunkX && oldChunkZ == newChunkZ) {
            return;
        }

        Set<EntityPlayer> oldChunkPlayers = oldDimension != null ? oldDimension.getChunkService().getChunk(oldChunkX, oldChunkZ).getPlayerChunkLoaders() : Collections.emptySet();
        Set<EntityPlayer> newChunkPlayers = newDimension != null ? newDimension.getChunkService().getChunk(newChunkX, newChunkZ).getPlayerChunkLoaders() : Collections.emptySet();
        Set<EntityPlayer> oldChunkOnlyPlayers = new HashSet<>(oldChunkPlayers);
        oldChunkOnlyPlayers.removeAll(newChunkPlayers);
        Set<EntityPlayer> newChunkOnlyPlayers = new HashSet<>(newChunkPlayers);
        newChunkOnlyPlayers.removeAll(oldChunkPlayers);

        oldChunkOnlyPlayers.stream().filter(player -> player != thisEntity).forEach(this::despawnFrom);
        newChunkOnlyPlayers.stream().filter(player -> player != thisEntity).forEach(this::spawnTo);
    }

    @Override
    public boolean teleport(Location3dc target, EntityTeleportEvent.Reason reason) {
        Objects.requireNonNull(target.dimension());
        if (this.location.dimension() == null) {
            log.warn("Trying to teleport an entity whose dimension is null! Entity: {}", thisEntity);
            return false;
        }

        if (!this.isSpawned()) {
            log.warn("Trying to teleport an entity which is not spawned! Entity: {}", thisEntity);
            return false;
        }

        var event = new EntityTeleportEvent(thisEntity, this.location, new Location3d(target), reason);
        if (!event.call()) {
            return false;
        }

        target = event.getTo();
        beforeTeleport(target);
        if (this.location.dimension() == target.dimension()) {
            // Teleporting in the current same dimension,
            // and we just need to move the entity to the new coordination
            teleportInDimension(target);
        } else {
            teleportOverDimension(target);
        }

        return true;
    }

    protected void beforeTeleport(Location3dc target) {
        this.fallDistance = 0;
        this.setMotion(0, 0, 0);
        // Ensure that the new chunk is loaded
        target.dimension().getChunkService().getOrLoadChunkSync((int) target.x() >> 4, (int) target.z() >> 4);
    }

    protected void teleportInDimension(Location3dc target) {
        // This method should always return true because we've loaded the chunk
        setLocation(target, false);
        broadcastMoveToViewers(target, true);
    }

    protected void teleportOverDimension(Location3dc target) {
        // Teleporting to another dimension, there will be more works to be done
        this.location.dimension().getEntityService().removeEntity(thisEntity, () -> {
            setLocationBeforeSpawn(target);
            target.dimension().getEntityService().addEntity(thisEntity);
        });
    }

    @Override
    public void sendMetadata() {
        if (viewers.isEmpty()) {
            return;
        }

        sendPacketToViewers(createSetEntityDataPacket());
    }

    protected SetEntityDataPacket createSetEntityDataPacket() {
        var packet = new SetEntityDataPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setMetadata(this.metadata.getEntityDataMap());
        packet.setTick(this.getWorld().getTick());
        return packet;
    }

    @Override
    public AABBdc getAABB() {
        // Default aabb is player's aabb
        return new AABBd(-0.3, 0.0, -0.3, 0.3, 1.8, 0.3);
    }

    @Override
    @UnmodifiableView
    public Map<Long, EntityPlayer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    @Override
    public void setMotion(Vector3dc motion) {
        if (MathUtils.hasNaN(motion)) {
            throw new IllegalArgumentException("Trying to set the motion of entity " + runtimeId + " to a new motion which contains NaN: " + motion);
        }
        this.lastMotion = this.motion;
        this.motion = new Vector3d(motion);
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        if (onGround) {
            this.onFall(this.fallDistance);
        }
    }

    @Override
    public void knockback(Vector3dc source, double kb, double kby, Vector3dc additionalMotion, boolean ignoreKnockbackResistance) {
        setMotion(calculateKnockbackMotion(source, kb, kby, additionalMotion, ignoreKnockbackResistance));
    }

    protected Vector3d calculateKnockbackMotion(Vector3dc source, double kb, double kby, Vector3dc additionalMotion, boolean ignoreKnockbackResistance) {
        if (!ignoreKnockbackResistance) {
            var resistance = 0.0;
            if (attributeComponent != null && attributeComponent.supportAttribute(AttributeType.KNOCKBACK_RESISTANCE)) {
                resistance = attributeComponent.getAttributeValue(AttributeType.KNOCKBACK_RESISTANCE);
            }
            if (resistance > 0) {
                var factor = 1 - resistance;
                kb *= factor;
                kby *= factor;
                additionalMotion = additionalMotion.mul(factor, new Vector3d());
            }
        }
        Vector3d vec;
        if (location.distanceSquared(source) <= 0.0001 /* 0.01 * 0.01 */) {
            // Generate a random kb direction if distance <= 0.01m
            var rand = ThreadLocalRandom.current();
            var rx = rand.nextDouble(1) - 0.5;
            var rz = rand.nextDouble(1) - 0.5;
            vec = MathUtils.normalizeIfNotZero(new Vector3d(rx, 0, rz)).mul(kb);
        } else {
            vec = location.sub(source, new Vector3d()).setComponent(1, 0).normalize().mul(kb);
        }
        vec.y = kby;
        return motion.mul(0.5, new Vector3d()).add(vec).add(additionalMotion);
    }

    @Override
    public void spawnTo(EntityPlayer player) {
        player.sendPacket(createSpawnPacket());
        viewers.put(player.getRuntimeId(), player);
    }

    @Override
    public void despawnFrom(EntityPlayer player) {
        var pk = new RemoveEntityPacket();
        pk.setUniqueEntityId(runtimeId);
        player.sendPacket(pk);
        viewers.remove(player.getRuntimeId());
    }

    @Override
    public void despawnFromAll() {
        viewers.values().forEach(this::despawnFrom);
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        var addEntityPacket = new AddEntityPacket();
        addEntityPacket.setRuntimeEntityId(runtimeId);
        addEntityPacket.setUniqueEntityId(runtimeId);
        addEntityPacket.setIdentifier(entityType.getIdentifier().toString());
        addEntityPacket.setPosition(Vector3f.from(location.x(), location.y() + getNetworkOffset(), location.z()));
        addEntityPacket.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        addEntityPacket.setRotation(Vector2f.from(location.pitch(), location.yaw()));
        addEntityPacket.getMetadata().putAll(metadata.getEntityDataMap());
        return addEntityPacket;
    }

    @Override
    public void sendPacketToViewers(BedrockPacket packet) {
        viewers.values().forEach(client -> client.sendPacket(packet));
    }

    @Override
    public void sendPacketToViewersImmediately(BedrockPacket packet) {
        viewers.values().forEach(client -> client.sendPacketImmediately(packet));
    }

    public void broadcastMoveToViewers(Location3dc newLoc, boolean teleporting) {
        var movementPk = createMovePacket(newLoc, teleporting);
        var motionPk = createMotionPacket();
        // Should send packets immediately here, otherwise entity movement will lag
        sendPacketToViewersImmediately(movementPk);
        sendPacketToViewersImmediately(motionPk);
    }

    protected BedrockPacket createMovePacket(Location3dc newLoc, boolean teleporting) {
        return Server.SETTINGS.entitySettings().physicsEngineSettings().useDeltaMovePacket() ?
                createDeltaMovePacket(newLoc, teleporting) :
                createAbsoluteMovePacket(newLoc, teleporting);
    }

    protected BedrockPacket createMotionPacket() {
        var pk = new SetEntityMotionPacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setMotion(Vector3f.from(motion.x, motion.y, motion.z));
        return pk;
    }

    protected BedrockPacket createAbsoluteMovePacket(Location3dc newLoc, boolean teleporting) {
        var pk = new MoveEntityAbsolutePacket();
        pk.setRuntimeEntityId(getRuntimeId());
        pk.setPosition(Vector3f.from(newLoc.x(), newLoc.y() + getNetworkOffset(), newLoc.z()));
        pk.setRotation(Vector3f.from(newLoc.pitch(), newLoc.yaw(), newLoc.headYaw()));
        pk.setOnGround(onGround);
        pk.setTeleported(teleporting);
        return pk;
    }

    protected BedrockPacket createDeltaMovePacket(Location3dc newLoc, boolean teleporting) {
        var pk = new MoveEntityDeltaPacket();
        pk.setRuntimeEntityId(getRuntimeId());
        var moveFlags = computeMoveFlags(newLoc);
        pk.getFlags().addAll(moveFlags);
        if (moveFlags.contains(HAS_X)) {
            pk.setX((float) newLoc.x());
            locationLastSent.x = newLoc.x();
        }
        if (moveFlags.contains(HAS_Y)) {
            pk.setY((float) newLoc.y());
            locationLastSent.y = newLoc.y() + getNetworkOffset();
        }
        if (moveFlags.contains(HAS_Z)) {
            pk.setZ((float) newLoc.z());
            locationLastSent.z = newLoc.z();
        }
        if (moveFlags.contains(HAS_PITCH)) {
            pk.setPitch((float) newLoc.pitch());
            locationLastSent.pitch = newLoc.pitch();
        }
        if (moveFlags.contains(HAS_YAW)) {
            pk.setYaw((float) newLoc.yaw());
            locationLastSent.yaw = newLoc.yaw();
        }
        if (moveFlags.contains(HAS_HEAD_YAW)) {
            pk.setHeadYaw((float) newLoc.headYaw());
            locationLastSent.headYaw = newLoc.headYaw();
        }
        if (onGround) {
            pk.getFlags().add(ON_GROUND);
        }
        if (teleporting) {
            pk.getFlags().add(TELEPORTING);
        }
        return pk;
    }

    protected Set<MoveEntityDeltaPacket.Flag> computeMoveFlags(Location3dc newLoc) {
        var flags = EnumSet.noneOf(MoveEntityDeltaPacket.Flag.class);
        var settings = Server.SETTINGS.entitySettings().physicsEngineSettings();
        var diffPositionThreshold = settings.diffPositionThreshold();
        var diffRotationThreshold = settings.diffRotationThreshold();
        if (abs(locationLastSent.x() - newLoc.x()) > diffPositionThreshold) flags.add(HAS_X);
        if (abs(locationLastSent.y() - newLoc.y()) > diffPositionThreshold) flags.add(HAS_Y);
        if (abs(locationLastSent.z() - newLoc.z()) > diffPositionThreshold) flags.add(HAS_Z);
        if (abs(locationLastSent.yaw() - newLoc.yaw()) > diffRotationThreshold) flags.add(HAS_YAW);
        if (abs(locationLastSent.pitch() - newLoc.pitch()) > diffRotationThreshold) flags.add(HAS_PITCH);
        if (enableHeadYaw() && abs(locationLastSent.headYaw() - newLoc.headYaw()) > diffRotationThreshold)
            flags.add(HAS_HEAD_YAW);
        return flags;
    }

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder();
        builder.putString(TAG_IDENTIFIER, entityType.getIdentifier().toString());
        builder.putBoolean(TAG_ON_GROUND, onGround);
        AllayNbtUtils.writeVector3f(builder, TAG_POS, (float) location.x, (float) location.y, (float) location.z);
        AllayNbtUtils.writeVector3f(builder, TAG_MOTION, (float) motion.x, (float) motion.y, (float) motion.z);
        AllayNbtUtils.writeVector2f(builder, TAG_ROTATION, (float) location.yaw(), (float) location.pitch());
        if (!tags.isEmpty()) {
            builder.putList(TAG_TAGS, NbtType.STRING, new ArrayList<>(tags));
        }
        if (!effects.isEmpty()) {
            builder.putList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, effects.values().stream().map(EffectInstance::saveNBT).toList());
        }
        if (!persistentDataContainer.isEmpty()) {
            builder.put(TAG_PDC, persistentDataContainer.toNbt());
        }
        saveUniqueId(builder);
        var event = new CEntitySaveNBTEvent(builder);
        manager.callEvent(event);
        return builder.build();
    }

    protected void saveUniqueId(NbtMapBuilder builder) {
        builder.putLong(TAG_UNIQUE_ID, uniqueId);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        if (nbt.containsKey(TAG_POS)) {
            var pos = readVector3f(nbt, TAG_POS);
            location.set(pos.x, pos.y, pos.z);
        }

        if (nbt.containsKey(TAG_MOTION)) {
            var motion = readVector3f(nbt, TAG_MOTION);
            this.motion.set(motion);
        }

        if (nbt.containsKey(TAG_ROTATION)) {
            var rot = readVector2f(nbt, TAG_ROTATION);
            location.setYaw(rot.x);
            location.setPitch(rot.y);
        }

        nbt.listenForBoolean(TAG_ON_GROUND, onGround -> this.onGround = onGround);
        nbt.listenForList(TAG_TAGS, NbtType.STRING, tags -> this.tags.addAll(tags));
        nbt.listenForList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, activeEffects -> {
            for (NbtMap activeEffect : activeEffects) {
                var effectInstance = EffectInstance.fromNBT(activeEffect);
                addEffect(effectInstance);
            }
        });
        nbt.listenForCompound(TAG_PDC, customNbt -> {
            this.persistentDataContainer.clear();
            this.persistentDataContainer.putAll(customNbt);
        });

        loadUniqueId(nbt);
        var event = new CEntityLoadNBTEvent(nbt);
        manager.callEvent(event);
    }

    protected void loadUniqueId(NbtMap nbt) {
        if (nbt.containsKey(TAG_UNIQUE_ID)) {
            this.uniqueId = nbt.getLong(TAG_UNIQUE_ID);
            return;
        }

        this.uniqueId = UUID.randomUUID().getMostSignificantBits();
    }

    @Override
    public void onFall(double fallDistance) {
        var event = new EntityFallEvent(thisEntity, fallDistance);
        if (!event.call()) {
            this.fallDistance = 0;
            return;
        }

        var blockUnder = getBlockStateStandingOn();
        blockUnder.getBehavior().onEntityFallOn(thisEntity, blockUnder);

        this.manager.callEvent(new CEntityFallEvent(event.getFallDistance()));
        this.fallDistance = 0;
    }

    @Override
    public Map<EffectType, EffectInstance> getAllEffects() {
        return Collections.unmodifiableMap(effects);
    }

    @Override
    public boolean hasEffect(EffectType effectType) {
        return effects.containsKey(effectType);
    }

    @Override
    public int getEffectLevel(EffectType effectType) {
        var effect = effects.get(effectType);
        return effect == null ? 0 : effect.getLevel();
    }

    @Override
    public boolean addEffect(EffectInstance effectInstance) {
        if (!canApplyEffect(effectInstance.getType())) return false;

        var event = new EntityEffectAddEvent(thisEntity, effectInstance);
        if (!event.call()) return false;

        effectInstance = event.getEffect();

        var old = effects.put(effectInstance.getType(), effectInstance);
        if (old != null) old.getType().onRemove(thisEntity, old);

        effectInstance.getType().onAdd(thisEntity, effectInstance);

        var packet = new MobEffectPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setEffectId(effectInstance.getType().getId());
        packet.setAmplifier(effectInstance.getAmplifier());
        packet.setParticles(effectInstance.isVisible());
        packet.setDuration(effectInstance.getDuration());
        packet.setEvent(old == null ? MobEffectPacket.Event.ADD : MobEffectPacket.Event.MODIFY);
        sendMobEffectPacket(packet);

        if (old == null) syncVisibleEffects();
        return true;
    }

    @Override
    public void removeEffect(EffectType effectType) {
        var removed = effects.get(effectType);
        if (removed == null) return;

        var event = new EntityEffectRemoveEvent(thisEntity, removed);
        if (!event.call()) return;

        effects.remove(effectType);

        effectType.onRemove(thisEntity, removed);

        var packet = new MobEffectPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setEffectId(effectType.getId());
        packet.setEvent(MobEffectPacket.Event.REMOVE);
        sendMobEffectPacket(packet);

        syncVisibleEffects();
    }

    protected void sendMobEffectPacket(MobEffectPacket packet) {
        sendPacketToViewers(packet);
    }

    @Override
    public void removeAllEffects() {
        // Prevent ConcurrentModificationException
        for (EffectType effectType : this.effects.keySet().toArray(EffectType[]::new)) {
            removeEffect(effectType);
        }
    }

    protected void syncVisibleEffects() {
        long visibleEffects = 0;
        for (var effect : this.effects.values()) {
            if (!effect.isVisible()) {
                continue;
            }

            visibleEffects = (visibleEffects << 7) | ((long) effect.getType().getId() << 1) | (effect.isAmbient() ? 1 : 0);
        }

        setAndSendEntityData(EntityDataTypes.VISIBLE_MOB_EFFECTS, visibleEffects);
    }

    @Override
    public String getCommandSenderName() {
        return getDisplayName();
    }

    @Override
    public CommandOriginData getCommandOriginData() {
        return ENTITY_COMMAND_ORIGIN_DATA;
    }

    @Override
    public Location3dc getCommandExecuteLocation() {
        return getLocation();
    }

    @Override
    public boolean isEntity() {
        return true;
    }

    @Override
    public Entity asEntity() {
        return thisEntity;
    }

    @Override
    public void sendText(String text) {
        // Do nothing
    }

    @Override
    public void sendTr(String key, boolean forceTranslatedByClient, Object... args) {
        // Do nothing
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        // Do nothing
    }

    @Override
    public boolean addTag(String tag) {
        return tags.add(tag);
    }

    @Override
    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    @Override
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    @Override
    @UnmodifiableView
    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}
