package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityFallEvent;
import org.allaymc.api.eventbus.event.entity.EntitySetMotionEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.*;
import org.jetbrains.annotations.Range;
import org.joml.RoundingMode;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static org.allaymc.api.utils.AllayNBTUtils.readVector3f;
import static org.allaymc.server.world.physics.AllayEntityPhysicsEngine.FAT_AABB_MARGIN;

/**
 * @author daoge_cmd
 */
public class EntityPhysicsComponentImpl implements EntityPhysicsComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_physics_component");

    protected static final String TAG_MOTION = "Motion";
    protected static final String TAG_ON_GROUND = "OnGround";
    protected static final String TAG_KNOCKBACK_RESISTANCE = "KnockbackResistance";

    // Constants used in physics calculation
    protected static final Pair<Double, Boolean> EMPTY_DOUBLE_BOOLEAN_PAIR = new Pair<>(0.0, false);
    protected static final double STEPPING_OFFSET = 0.05;

    @ComponentObject
    protected Entity thisEntity;
    @Dependency
    protected EntityBaseComponentImpl baseComponent;
    @Dependency(optional = true)
    protected EntityLivingComponent livingComponent;
    @Manager
    protected ComponentManager manager;

    @Getter
    protected Vector3d motion;
    @Getter
    protected Vector3d lastMotion;
    @Getter
    protected boolean onGround;
    @Getter
    @Accessors(fluent = true)
    protected boolean hasGravity;
    @Getter
    protected double fallDistance;
    @Getter
    protected float knockbackResistance;

    public EntityPhysicsComponentImpl() {
        this.motion = new Vector3d();
        this.lastMotion = new Vector3d();
        this.onGround = true;
        this.hasGravity = true;
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();

        if (nbt.containsKey(TAG_MOTION)) {
            var motion = readVector3f(nbt, TAG_MOTION);
            this.motion.set(motion);
        }

        nbt.listenForBoolean(TAG_ON_GROUND, onGround -> this.onGround = onGround);
        nbt.listenForFloat(TAG_KNOCKBACK_RESISTANCE, knockbackResistance -> this.knockbackResistance = knockbackResistance);
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        builder.putBoolean(TAG_ON_GROUND, onGround);
        AllayNBTUtils.writeVector3f(builder, TAG_MOTION, (float) motion.x, (float) motion.y, (float) motion.z);
        builder.putFloat(TAG_KNOCKBACK_RESISTANCE, knockbackResistance);
    }

    @EventHandler
    protected void onBeforeTeleport(CEntityBeforeTeleportEvent event) {
        this.fallDistance = 0;
        this.setMotion(0, 0, 0);
    }

    @EventHandler
    protected void onSetLocation(CEntitySetLocationEvent event) {
        if (event.isCalculateFallDistance() && !this.onGround) {
            if (this.fallDistance < 0) {
                // Entity start falling
                this.fallDistance = 0;
            }

            var location = thisEntity.getLocation();
            var newLocation = event.getLocation();
            // fall distance < 0 -> move up
            // fall distance > 0 -> move down
            this.fallDistance -= newLocation.y() - location.y();

            tryResetFallDistance(newLocation);
        }
    }

    /// Try to reset the entity's fall distance. This usually happens if it falls into a block
    /// (e.g., water and slime block) that can reset fall distance.
    protected void tryResetFallDistance(Location3dc location) {
        var blockState0 = location.dimension().getBlockState(location);
        var blockState1 = location.dimension().getBlockState(location, 1);
        var newEntityAABB = thisEntity.getAABB().translate(location, new AABBd());

        if (!blockState0.getBlockStateData().hasCollision() &&
            blockState1.getBehavior().canResetFallDamage() &&
            blockState1.getBlockStateData().computeOffsetShape(MathUtils.floor(location)).intersectsAABB(newEntityAABB)) {
            this.fallDistance = 0;
        }

        if (blockState0.getBehavior().canResetFallDamage() &&
            blockState0.getBlockStateData().computeOffsetShape(MathUtils.floor(location)).intersectsAABB(newEntityAABB)) {
            this.fallDistance = 0;
        }
    }

    @Override
    public boolean applyMotion() {
        if (motion.lengthSquared() == 0) {
            return false;
        }

        var location = thisEntity.getLocation();
        var pos = new Location3d(location);
        var mx = motion.x();
        var my = motion.y();
        var mz = motion.z();
        var aabb = new AABBd(thisEntity.getOffsetAABB());

        // First move along the Y axis
        var yResult = moveAlongAxisAndStopWhenCollision(aabb, my, pos, Axis.Y);
        my = yResult.left();
        var isOnGround = yResult.right();

        var stepHeight = this.getStepHeight();
        if (abs(mx) >= abs(mz)) {
            // First handle the X axis, then handle the Z axis
            mx = applyMotion0(stepHeight, pos, mx, aabb, isOnGround, Axis.X);
            mz = applyMotion0(stepHeight, pos, mz, aabb, isOnGround, Axis.Z);
        } else {
            mz = applyMotion0(stepHeight, pos, mz, aabb, isOnGround, Axis.Z);
            mx = applyMotion0(stepHeight, pos, mx, aabb, isOnGround, Axis.X);
        }

        this.setMotion(new Vector3d(mx, my, mz));
        if (!pos.equals(location) && thisEntity.trySetLocation(pos)) {
            // Update onGround status after updated entity location
            // to make sure that some block (for example: water) can reset
            // entity's fallDistance before onFall() called
            this.setOnGround(isOnGround);
            return true;
        }
        return false;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        if (onGround) {
            this.onFall(this.fallDistance);
        }
    }

    /// Applies motion to the object's position along the specified axis, considering potential collisions and intersections with other objects.
    ///
    /// @param stepHeight     The step height the object can overcome
    /// @param pos            The current position of the object
    /// @param motion         The component of the object's movement velocity along the specified axis (X or Z)
    /// @param aabb           The Axis-Aligned Bounding Box (AABB) of the object
    /// @param enableStepping Flag indicating whether the object can step over obstacles
    /// @param axis           The axis along which the motion is applied (X or Z)
    /// @return The remaining component of the object's movement velocity along the specified axis after considering possible collisions and intersections
    private double applyMotion0(double stepHeight, Location3d pos, double motion, AABBd aabb, boolean enableStepping, Axis axis) {
        if (motion == 0) {
            return motion;
        }

        var resultAABB = new AABBd(aabb);
        var resultPos = new Vector3d(pos);

        // The first time directly moves
        var result = moveAlongAxisAndStopWhenCollision(resultAABB, motion, resultPos, axis);
        if (Boolean.TRUE.equals(result.right())) {
            // There is a collision, try to step over
            // Calculate the remaining speed
            motion -= axis.getComponent(resultPos) - axis.getComponent(pos);
            if (enableStepping && tryStepping(resultPos, resultAABB, stepHeight, motion > 0, axis == Axis.X)) {
                result = moveAlongAxisAndStopWhenCollision(resultAABB, motion, resultPos, axis);
            }
        }

        motion = result.left();

        aabb.set(resultAABB);
        pos.set(resultPos);
        return motion;
    }

    /// Moves an axis-aligned bounding box (AABB) along a specified axis direction and stops when a collision occurs.
    ///
    /// @param aabb     The axis-aligned bounding box to move
    /// @param motion   The distance to move along the specified axis
    /// @param recorder The vector to record the movement along the axis
    /// @param axis     The axis along which to move the AABB. Use 0 for the X-axis, 1 for the Y-axis, and 2 for the Z-axis
    /// @return A pair containing the remaining movement distance along the axis after collision detection (Double)
    /// and a boolean indicating whether a collision occurred (Boolean) or whether the entity will be on ground (if axis == Y).
    /// If no movement was specified (motion = 0), an empty pair is returned.
    /// @throws IllegalArgumentException if an invalid axis is provided
    private Pair<Double, Boolean> moveAlongAxisAndStopWhenCollision(AABBd aabb, double motion, Vector3d recorder, Axis axis) {
        if (motion == 0) {
            return EMPTY_DOUBLE_BOOLEAN_PAIR;
        }

        // `extAABBInAxis` is the extended aabb in the axis which is used to check for block collision,
        // and the direction of the axis is determined by the sign of `motion`
        var extAABBInAxis = new AABBd(aabb);

        // Move towards the negative(motion < 0) or positive(motion > 0) axis direction
        var shouldTowardsNegative = motion < 0;

        var length = axis.getLength(aabb);
        if (shouldTowardsNegative) {
            axis.setMin(extAABBInAxis, axis.getMin(extAABBInAxis) + motion);
            axis.setMax(extAABBInAxis, axis.getMax(extAABBInAxis) - length);
        } else {
            axis.setMin(extAABBInAxis, axis.getMin(extAABBInAxis) + length);
            axis.setMax(extAABBInAxis, axis.getMax(extAABBInAxis) + motion);
        }

        // Do not use dimension.isAABBInDimension(extendX|Y|Z) because entity should be able to move even if y > maxHeight
        if (notValidEntityArea(extAABBInAxis)) {
            return EMPTY_DOUBLE_BOOLEAN_PAIR;
        }

        // `deltaInAxis` is the actual movement distance along the axis direction, and if there is no collision, this distance is equal to `motion`
        var deltaInAxis = motion;
        var collision = false;

        var blocks = thisEntity.getDimension().getCollidingBlockStates(extAABBInAxis);
        if (blocks != null) {
            // There is a collision if `blocks` is not null
            if (axis == Axis.Y) {
                // When the axis is Y, `collision` indicates whether the entity will be on the ground
                collision = shouldTowardsNegative;
            } else {
                collision = true;
            }

            var extAABBStartCoordinate = shouldTowardsNegative ? axis.getMax(extAABBInAxis) : axis.getMin(extAABBInAxis);
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
            axis.translate(aabb, deltaInAxis, aabb);
            // Update the coordinates
            axis.setComponent(recorder, axis.getComponent(recorder) + deltaInAxis);
        }

        return new Pair<>(motion, collision);
    }

    private boolean notValidEntityArea(AABBd extendAABB) {
        var dimension = thisEntity.getDimension();
        return !(extendAABB.minY >= dimension.getDimensionInfo().minHeight()) &&
               !dimension.getChunkManager().isChunkLoaded((int) extendAABB.minX >> 4, (int) extendAABB.minZ >> 4) &&
               !dimension.getChunkManager().isChunkLoaded((int) extendAABB.maxX >> 4, (int) extendAABB.maxZ >> 4);
    }

    private boolean tryStepping(Vector3d pos, AABBd aabb, double stepHeight, boolean positive, boolean xAxis) {
        if (stepHeight == 0) {
            return false;
        }

        var offset = positive ? STEPPING_OFFSET : -STEPPING_OFFSET;
        var offsetAABB = aabb.translate(xAxis ? offset : 0, 0, xAxis ? 0 : offset, new AABBd());
        var recorder = new Vector3d();

        moveAlongAxisAndStopWhenCollision(offsetAABB, stepHeight, recorder, Axis.Y);
        moveAlongAxisAndStopWhenCollision(offsetAABB, -stepHeight, recorder, Axis.Y);

        if (recorder.y == 0 || thisEntity.getDimension().getCollidingBlockStates(offsetAABB) != null) {
            return false;
        } else {
            aabb.set(offsetAABB.translate(xAxis ? -offset : 0, 0, xAxis ? 0 : -offset));
            pos.add(recorder);
            return true;
        }
    }

    private double computeCollisionCoordinate(AABBdc entityAABB, AABBdc extAABBInAxis, BlockState[][][] blocks, Axis axis, boolean shouldTowardNegative) {
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

                        double current = shouldTowardNegative ? axis.getMax(solid) : axis.getMin(solid);
                        if (shouldTowardNegative ? (current > coordinate) : (current < coordinate)) {
                            coordinate = current;
                        }
                    }
                }
            }
        }

        return coordinate;
    }

    @Override
    public Vector3d updateMotion(boolean hasLiquidMotion) {
        var blockStateStandingOn = getBlockStateStandingOn();
        var isOnGround = blockStateStandingOn.getBlockType() != BlockTypes.AIR;
        var slipperinessMultiplier = 1.0;
        // Entity that has liquid motion won't be affected by the friction of the block it stands on
        if (!hasLiquidMotion && isOnGround) {
            slipperinessMultiplier = blockStateStandingOn.getBlockStateData().friction();
        }

        var horizontalFactor = slipperinessMultiplier * (1 - (isOnGround ? getDragFactorOnGround() : getDragFactorInAir()));
        return new Vector3d(
                motion.x() * horizontalFactor,
                (motion.y() - (hasGravity() ? getGravity() : 0f)) * (1 - getDragFactorInAir()),
                motion.z() * horizontalFactor
        );
    }

    @Override
    public boolean setMotion(Vector3dc motion) {
        if (MathUtils.hasNaN(motion)) {
            throw new IllegalArgumentException("Trying to set the motion of entity " + thisEntity.getRuntimeId() + " to a new motion which contains NaN: " + motion);
        }

        var event = new EntitySetMotionEvent(thisEntity, motion);
        if (event.call()) {
            this.lastMotion = this.motion;
            this.motion = new Vector3d(event.getMotion());
            return true;
        }

        return false;
    }

    @Override
    public void knockback(Vector3dc source, double kb, double kby, Vector3dc additionalMotion, boolean ignoreKnockbackResistance) {
        setMotion(calculateKnockbackMotion(source, kb, kby, additionalMotion, ignoreKnockbackResistance));
    }

    @Override
    public void setKnockbackResistance(@Range(from = 0, to = 1) float knockbackResistance) {
        this.knockbackResistance = Math.clamp(knockbackResistance, 0, 1);
    }

    protected Vector3d calculateKnockbackMotion(Vector3dc source, double kb, double kby, Vector3dc additionalMotion, boolean ignoreKnockbackResistance) {
        if (!ignoreKnockbackResistance) {
            if (this.knockbackResistance > 0) {
                var factor = 1 - this.knockbackResistance;
                kb *= factor;
                kby *= factor;
                additionalMotion = additionalMotion.mul(factor, new Vector3d());
            }
        }

        Vector3d vec;
        var location = thisEntity.getLocation();
        if (location.distanceSquared(source) <= 0.0001 /* 0.01 * 0.01 */) {
            // Generate a random kb direction if distance <= 0.01m
            var rand = ThreadLocalRandom.current();
            var rx = rand.nextDouble(1) - 0.5;
            var rz = rand.nextDouble(1) - 0.5;
            vec = MathUtils.normalizeIfNotZero(new Vector3d(rx, 0, rz)).mul(kb);
        } else {
            vec = MathUtils.normalizeIfNotZero(location.sub(source, new Vector3d()).setComponent(1, 0)).mul(kb);
        }
        vec.y = kby;
        return motion.mul(0.5, new Vector3d()).add(vec).add(additionalMotion);
    }

    @Override
    public void onFall(double fallDistance) {
        var event = new EntityFallEvent(thisEntity, fallDistance);
        if (!event.call()) {
            this.fallDistance = 0;
            return;
        }

        this.manager.callEvent(new CEntityFallEvent(event.getFallDistance()));
        var blockUnder = getBlockStateStandingOn();
        blockUnder.getBehavior().onEntityFallOn(thisEntity, blockUnder);
        this.fallDistance = 0;
    }

    @Override
    public Block getBlockStateStandingOn() {
        var loc = thisEntity.getLocation();
        var air = BlockTypes.AIR.getDefaultState();
        var dimension = thisEntity.getDimension();
        if (!isOnGround()) {
            return new Block(air, new Position3i(loc, RoundingMode.FLOOR, dimension));
        }

        var currentBlockState = dimension.getBlockState(loc.x(), loc.y(), loc.z());
        if (currentBlockState != air) {
            return new Block(
                    currentBlockState,
                    new Position3i((int) Math.floor(loc.x()), (int) Math.floor(loc.y()), (int) Math.floor(loc.z()), dimension)
            );
        } else {
            return new Block(
                    dimension.getBlockState(loc.x(), loc.y() - 1, loc.z()),
                    new Position3i((int) Math.floor(loc.x()), (int) Math.floor(loc.y() - 1), (int) Math.floor(loc.z()), dimension)
            );
        }
    }

    @Override
    public boolean canStandSafely(int x, int y, int z, Dimension dimension) {
        var blockUnder = dimension.getBlockState(x, y - 1, z);
        if (!blockUnder.getBlockStateData().isSolid()) {
            return false;
        }
        var aabb = thisEntity.getAABB().translate(x + 0.5, y + 0.5, z + 0.5, new AABBd());
        return dimension.getCollidingBlockStates(aabb) == null;
    }

    @Override
    public boolean canCriticalAttack() {
        if (livingComponent != null &&
            (livingComponent.hasEffect(EffectTypes.BLINDNESS) ||
             livingComponent.hasEffect(EffectTypes.SLOW_FALLING))
        ) {
            return false;
        }

        return !isOnGround() && getMotion().y() < 0;
    }

    @Override
    public void setHasGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
        this.baseComponent.broadcastState();
    }

    @Override
    public boolean computeEntityCollisionMotion() {
        return thisEntity.hasEntityCollision();
    }

    @Override
    public boolean computeBlockCollisionMotion() {
        return true;
    }

    @Override
    public boolean computeLiquidMotion() {
        return true;
    }

    private enum Axis {
        X {
            @Override
            double getMin(AABBdc aabb) {
                return aabb.minX();
            }

            @Override
            double getMax(AABBdc aabb) {
                return aabb.maxX();
            }

            @Override
            double getLength(AABBdc aabb) {
                return aabb.lengthX();
            }

            @Override
            double getComponent(Vector3dc vec) {
                return vec.x();
            }

            @Override
            void setComponent(Vector3d vec, double value) {
                vec.setComponent(0, value);
            }

            @Override
            void translate(AABBd aabb, double delta, AABBd dest) {
                aabb.translate(delta, 0, 0, dest);
            }

            @Override
            void setMin(AABBd aabb, double value) {
                aabb.minX = value;
            }

            @Override
            void setMax(AABBd aabb, double value) {
                aabb.maxX = value;
            }
        },
        Y {
            @Override
            double getMin(AABBdc aabb) {
                return aabb.minY();
            }

            @Override
            double getMax(AABBdc aabb) {
                return aabb.maxY();
            }

            @Override
            double getLength(AABBdc aabb) {
                return aabb.lengthY();
            }

            @Override
            double getComponent(Vector3dc vec) {
                return vec.y();
            }

            @Override
            void setComponent(Vector3d vec, double value) {
                vec.setComponent(1, value);
            }

            @Override
            void translate(AABBd aabb, double delta, AABBd dest) {
                aabb.translate(0, delta, 0, dest);
            }

            @Override
            void setMin(AABBd aabb, double value) {
                aabb.minY = value;
            }

            @Override
            void setMax(AABBd aabb, double value) {
                aabb.maxY = value;
            }
        },
        Z {
            @Override
            double getMin(AABBdc aabb) {
                return aabb.minZ();
            }

            @Override
            double getMax(AABBdc aabb) {
                return aabb.maxZ();
            }

            @Override
            double getLength(AABBdc aabb) {
                return aabb.lengthZ();
            }

            @Override
            double getComponent(Vector3dc vec) {
                return vec.z();
            }

            @Override
            void setComponent(Vector3d vec, double value) {
                vec.setComponent(2, value);
            }

            @Override
            void translate(AABBd aabb, double delta, AABBd dest) {
                aabb.translate(0, 0, delta, dest);
            }

            @Override
            void setMin(AABBd aabb, double value) {
                aabb.minZ = value;
            }

            @Override
            void setMax(AABBd aabb, double value) {
                aabb.maxZ = value;
            }
        };

        abstract double getMin(AABBdc aabb);

        abstract double getMax(AABBdc aabb);

        abstract double getLength(AABBdc aabb);

        abstract double getComponent(Vector3dc vec);

        abstract void setComponent(Vector3d vec, double value);

        abstract void translate(AABBd aabb, double delta, AABBd dest);

        abstract void setMin(AABBd aabb, double value);

        abstract void setMax(AABBd aabb, double value);
    }
}
