package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityBaseComponent;
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
 * {@code EntityPhysicsComponentImpl} calculates entity motion against block
 * shapes and carries landing work from parallel movement into a sequential
 * callback. Movement resolves the vertical axis first, then the larger
 * horizontal component; stepping can retry a blocked horizontal move after
 * testing a raised box. The resulting position is still subject to the
 * entity's normal movement event.
 *
 * <p> {@code pendingOnGround} holds the landing result only after a location
 * update succeeds. The physics engine joins movement workers before
 * {@code afterApplyMotion} commits that result and invokes fall callbacks,
 * keeping block landing effects out of the parallel movement pass. Motion
 * proposals, liquid updates, and knockback retain their own cancellable setter
 * path. Callers must preserve these phase boundaries; the component's mutable
 * vectors and fields do not provide an independent concurrent transaction.
 *
 * @author daoge_cmd
 */
public class EntityPhysicsComponentImpl implements EntityPhysicsComponent {

    /**
     * {@code IDENTIFIER} selects the common physics provider during entity
     * type assembly, allowing specialized physics components to replace it
     * under the same component identity.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_physics_component");

    // TAG_MOTION, TAG_ON_GROUND, and TAG_KNOCKBACK_RESISTANCE identify the
    // physical state shared by the entity load and save event handlers.

    /**
     * NBT key for the stored velocity vector.
     */
    protected static final String TAG_MOTION = "Motion";
    /**
     * NBT key for the committed ground-state flag.
     */
    protected static final String TAG_ON_GROUND = "OnGround";
    /**
     * NBT key for the knockback resistance factor.
     */
    protected static final String TAG_KNOCKBACK_RESISTANCE = "KnockbackResistance";

    /**
     * {@code EMPTY_DOUBLE_BOOLEAN_PAIR} reports zero retained velocity and no
     * collision when an axis has no motion or the swept-area guard rejects it.
     */
    protected static final Pair<Double, Boolean> EMPTY_DOUBLE_BOOLEAN_PAIR = new Pair<>(0.0, false);
    /**
     * {@code STEPPING_OFFSET} shifts the candidate box five hundredths of a
     * block into an obstacle before testing whether a raised step can fit.
     */
    protected static final double STEPPING_OFFSET = 0.05;

    /**
     * Injected entity supplying the current location and collision box.
     */
    @ComponentObject
    protected Entity thisEntity;
    /**
     * Base component used to broadcast changed gravity state.
     */
    @Dependency
    protected EntityBaseComponent baseComponent;
    /**
     * Optional living state supplying critical-attack effect restrictions.
     */
    @Dependency(optional = true)
    protected EntityLivingComponent livingComponent;
    /**
     * Injected manager dispatching internal fall callbacks.
     */
    @Manager
    protected ComponentManager manager;

    // motion and lastMotion share identity across accepted replacements: the old
    // current vector becomes lastMotion, while motion receives a new copied vector.

    /**
     * {@code motion} holds current velocity in blocks per tick. The generated
     * getter exposes the actual mutable vector, so callers need a copy for
     * independent edits.
     */
    @Getter
    protected Vector3d motion;
    /**
     * {@code lastMotion} retains the previous vector when {@code setMotion} accepts a
     * replacement. The generated getter exposes that same mutable object
     * rather than a defensive copy.
     */
    @Getter
    protected Vector3d lastMotion;
    /**
     * Committed ground-contact flag used by landing and support queries.
     */
    @Getter
    protected boolean onGround;
    /**
     * Whether ordinary motion updates apply gravitational acceleration.
     */
    @Getter
    @Accessors(fluent = true)
    protected boolean hasGravity;
    /**
     * Accumulated vertical fall distance measured in blocks.
     */
    @Getter
    protected double fallDistance;
    /**
     * Resistance factor scaling knockback contributions when resistance is
     * honored.
     */
    @Getter
    protected float knockbackResistance;
    /**
     * {@code pendingOnGround} carries a successful movement's ground result to
     * {@code afterApplyMotion}. {@code null} means no pending result; the
     * engine's worker join establishes the required handoff before sequential
     * callbacks.
     */
    protected Boolean pendingOnGround;

    /**
     * Creates separate zero-valued current and previous motion vectors with
     * ground contact and gravity enabled. The initial ground flag has not been
     * derived from collision geometry, and construction does not dispatch
     * landing callbacks or a motion event.
     */
    public EntityPhysicsComponentImpl() {
        this.motion = new Vector3d();
        this.lastMotion = new Vector3d();
        this.onGround = true;
        this.hasGravity = true;
    }

    /**
     * Loads stored motion, ground state, and resistance when their expected
     * NBT entries exist. Motion updates the current vector directly, and
     * loaded resistance bypasses the public setter's clamp; missing entries
     * preserve the current fields.
     */
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

    /**
     * Writes ground state, motion, and resistance to the shared NBT builder.
     * Velocity components narrow to floats for the stored vector, while
     * pending landing state and accumulated fall distance are omitted.
     */
    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        builder.putBoolean(TAG_ON_GROUND, onGround);
        // Persist motion with float components because the paired loader reads
        // the entity Motion tag through the float-vector NBT representation.
        AllayNBTUtils.writeVector3f(builder, TAG_MOTION, (float) motion.x, (float) motion.y, (float) motion.z);
        builder.putFloat(TAG_KNOCKBACK_RESISTANCE, knockbackResistance);
    }

    /**
     * Resets accumulated fall distance and requests zero motion before an
     * accepted teleport. Motion clearing still uses the cancellable setter, so
     * a listener can refuse that proposal even though fall distance was
     * already reset.
     */
    @EventHandler
    protected void onBeforeTeleport(CEntityBeforeTeleportEvent event) {
        this.fallDistance = 0;
        this.setMotion(0, 0, 0);
    }

    /**
     * Updates airborne fall distance before the base component copies a new
     * location. The event must request fall calculation; upward movement can
     * make the distance negative, and later downward movement restarts
     * accumulation from zero before block-based reset checks.
     */
    @EventHandler
    protected void onSetLocation(CEntitySetLocationEvent event) {
        if (event.isCalculateFallDistance() && !this.onGround) {
            if (this.fallDistance < 0) {
                this.fallDistance = 0;
            }

            var location = thisEntity.getLocation();
            var newLocation = event.getLocation();
            // Subtract the signed vertical displacement so downward movement adds
            // to fall distance while upward movement records a negative interval.
            this.fallDistance -= newLocation.y() - location.y();

            tryResetFallDistance(newLocation);
        }
    }

    /**
     * Resets falling distance when the proposed entity box intersects a
     * fall-resetting block shape. The second block layer is considered only
     * when the primary block has no collision, allowing layered reset behavior
     * without treating every nearby liquid as contact.
     */
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

    /**
     * Resolves current velocity against block collision boxes and attempts the
     * resulting location update. Zero velocity returns without movement.
     * Vertical clipping determines the proposed ground state before horizontal
     * axes are processed, and the larger horizontal component moves first to
     * determine stepping opportunities.
     *
     * <p> The clipped velocity is proposed through {@code setMotion} before
     * the location proposal. Only a successful changed location stores
     * {@code pendingOnGround}; {@code afterApplyMotion} later commits it after
     * the engine joins workers. Listener cancellation or an unchanged position
     * can therefore leave velocity and location decisions different, and this
     * method does not roll back a previously accepted motion proposal.
     */
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

        // Resolve vertical support before horizontal stepping because a blocked
        // downward move determines whether this pass may try stepping over obstacles.
        var yResult = moveAlongAxisAndStopWhenCollision(aabb, my, pos, Axis.Y);
        my = yResult.left();
        var isOnGround = yResult.right();

        var stepHeight = this.getStepHeight();
        if (abs(mx) >= abs(mz)) {
            mx = applyMotion0(stepHeight, pos, mx, aabb, isOnGround, Axis.X);
            mz = applyMotion0(stepHeight, pos, mz, aabb, isOnGround, Axis.Z);
        } else {
            mz = applyMotion0(stepHeight, pos, mz, aabb, isOnGround, Axis.Z);
            mx = applyMotion0(stepHeight, pos, mx, aabb, isOnGround, Axis.X);
        }

        this.setMotion(new Vector3d(mx, my, mz));
        if (!pos.equals(location) && thisEntity.trySetLocation(pos)) {
            // Publish only the pending result here; the engine joins movement
            // workers before the sequential callback can run shared-world landing effects.
            this.pendingOnGround = isOnGround;
            return true;
        }
        return false;
    }

    /**
     * Commits a pending ground result after parallel movement has completed. A
     * non-{@code null} result can invoke landing callbacks through
     * {@code setOnGround}; normal completion then clears it so a later pass
     * does not replay that result.
     */
    @Override
    public void afterApplyMotion() {
        if (pendingOnGround != null) {
            this.setOnGround(pendingOnGround);
            pendingOnGround = null;
        }
    }

    /**
     * Stores the ground-contact flag and invokes landing handling whenever the
     * supplied value is {@code true}. Callers must use the sequential physics
     * phase for possible world callbacks; repeated {@code true} assignments
     * can invoke another landing check.
     */
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        if (onGround) {
            this.onFall(this.fallDistance);
        }
    }

    /**
     * Applies one horizontal component using temporary position and box state.
     * A collision first consumes the distance already traveled, then an
     * enabled step attempt can raise the box before the remaining horizontal
     * distance is retried. Zero motion leaves the supplied state unchanged.
     *
     * <p> The final temporary box and position are copied back whether
     * stepping succeeds or the original collision remains. The returned
     * component is the velocity retained after collision, rather than the
     * distance moved, allowing the caller to build its next stored motion
     * proposal separately from the new position.
     */
    private double applyMotion0(double stepHeight, Location3d pos, double motion, AABBd aabb, boolean enableStepping, Axis axis) {
        if (motion == 0) {
            return motion;
        }

        var resultAABB = new AABBd(aabb);
        var resultPos = new Vector3d(pos);

        var result = moveAlongAxisAndStopWhenCollision(resultAABB, motion, resultPos, axis);
        if (result.right()) {
            // Retry only the untraveled horizontal distance after a successful step,
            // preventing the initial clipped movement from being counted a second time.
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

    /**
     * Moves the supplied box and recorder along one axis while retaining
     * collision information for the caller. A zero component or rejected swept
     * area returns the shared empty result. Otherwise the helper queries the
     * swept extension and selects the nearest collision boundary that does not
     * already intersect the entity box.
     *
     * <p> A reported block collision clears retained velocity for that axis.
     * The traveled distance leaves {@code FAT_AABB_MARGIN} before a selected
     * boundary, and the vertical collision flag means downward support rather
     * than an upward ceiling hit. The box and recorder change together when
     * the resulting displacement is nonzero, while the returned numeric value
     * represents retained velocity rather than traveled distance.
     */
    private Pair<Double, Boolean> moveAlongAxisAndStopWhenCollision(AABBd aabb, double motion, Vector3d recorder, Axis axis) {
        if (motion == 0) {
            return EMPTY_DOUBLE_BOOLEAN_PAIR;
        }

        // Query only the box extension in the travel direction; the original
        // entity volume is handled separately when existing intersections are excluded.
        var extAABBInAxis = new AABBd(aabb);

        var shouldTowardsNegative = motion < 0;

        var length = axis.getLength(aabb);
        if (shouldTowardsNegative) {
            axis.setMin(extAABBInAxis, axis.getMin(extAABBInAxis) + motion);
            axis.setMax(extAABBInAxis, axis.getMax(extAABBInAxis) - length);
        } else {
            axis.setMin(extAABBInAxis, axis.getMin(extAABBInAxis) + length);
            axis.setMax(extAABBInAxis, axis.getMax(extAABBInAxis) + motion);
        }

        // Keep the specialized swept-area guard instead of a full height-bound
        // check, because entity movement above the dimension maximum is still permitted.
        if (notValidEntityArea(extAABBInAxis)) {
            return EMPTY_DOUBLE_BOOLEAN_PAIR;
        }

        var deltaInAxis = motion;
        var collision = false;

        var blocks = thisEntity.getDimension().getCollidingBlockStates(extAABBInAxis);
        if (blocks != null) {
            if (axis == Axis.Y) {
                // Only a downward vertical collision marks ground support; a ceiling
                // hit must stop velocity without scheduling a landing callback.
                collision = shouldTowardsNegative;
            } else {
                collision = true;
            }

            var extAABBStartCoordinate = shouldTowardsNegative ? axis.getMax(extAABBInAxis) : axis.getMin(extAABBInAxis);
            var collisionCoordinate = computeCollisionCoordinate(aabb, extAABBInAxis, blocks, axis, shouldTowardsNegative);
            // The extreme sentinel remains when all candidate shapes already
            // intersect the entity, so only a selected new boundary replaces displacement.
            if (abs(collisionCoordinate) != Double.MAX_VALUE) {
                deltaInAxis = abs(extAABBStartCoordinate - collisionCoordinate);
            }

            // Leave the physics margin before block faces so subsequent broad-phase
            // queries do not treat a just-clipped entity as penetrating that surface.
            if (deltaInAxis < FAT_AABB_MARGIN) {
                deltaInAxis = 0;
            } else {
                deltaInAxis -= FAT_AABB_MARGIN;
            }

            if (shouldTowardsNegative) {
                deltaInAxis = -deltaInAxis;
            }

            motion = 0;
        }

        if (deltaInAxis != 0) {
            axis.translate(aabb, deltaInAxis, aabb);
            axis.setComponent(recorder, axis.getComponent(recorder) + deltaInAxis);
        }

        return new Pair<>(motion, collision);
    }

    /**
     * Returns whether the swept box fails this component's restricted area
     * guard. Rejection requires its minimum height to be below the dimension
     * floor and both endpoint chunks to be unloaded; other out-of-range areas
     * are not rejected here.
     */
    private boolean notValidEntityArea(AABBd extendAABB) {
        var dimension = thisEntity.getDimension();
        return !(extendAABB.minY >= dimension.getDimensionType().getMinHeight()) &&
               !dimension.getChunkManager().isChunkLoaded((int) extendAABB.minX >> 4, (int) extendAABB.minZ >> 4) &&
               !dimension.getChunkManager().isChunkLoaded((int) extendAABB.maxX >> 4, (int) extendAABB.maxZ >> 4);
    }

    /**
     * Tests whether the supplied horizontal collision state can pass the
     * signed vertical step probe. A zero height disables the attempt. The
     * helper offsets the candidate box toward travel, clips upward and
     * downward probes, and accepts only a nonzero net vertical displacement
     * with no remaining block collision.
     *
     * <p> On success the temporary horizontal offset is removed before the box
     * and position are updated together. Failure leaves the caller's box and
     * position unchanged, allowing the original clipped horizontal result to
     * remain the fallback without partially applying a failed step.
     */
    private boolean tryStepping(Vector3d pos, AABBd aabb, double stepHeight, boolean positive, boolean xAxis) {
        if (stepHeight == 0) {
            return false;
        }

        // Shift slightly into the obstacle before vertical probes so a raised
        // candidate must fit where the next horizontal movement would actually enter.
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

    /**
     * Selects the nearest new block-shape boundary along the travel direction.
     * The nested block array is interpreted relative to the swept box's
     * floored coordinates, including the extra lower Y search row. Shapes
     * already intersecting the original entity box are excluded from boundary
     * selection.
     *
     * <p> Each remaining solid must intersect the swept extension before its
     * near face can compete. The result is the greatest near face for negative
     * travel or the least for positive travel, while an extreme signed
     * sentinel remains when no candidate qualifies. Callers must preserve that
     * sentinel distinction before converting it into a displacement.
     */
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

                    // Restore the lower Y search row used by getCollidingBlockStates
                    // so each array entry is associated with its actual block position.
                    var shape = blockState.getBlockStateData().computeOffsetCollisionShape(floor(extAABBInAxis.minX()) + ox, floor(extAABBInAxis.minY()) - 1 + oy, floor(extAABBInAxis.minZ()) + oz);
                    if (shape.intersectsAABB(entityAABB)) {
                        // Existing penetration cannot define a new stopping boundary;
                        // leave those shapes to the engine's separate escape-motion handling.
                        continue;
                    }

                    var solids = shape.getSolids();
                    for (var solid : solids) {
                        if (!solid.intersectsAABB(extAABBInAxis)) {
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

    /**
     * Calculates a new velocity using liquid state, support, drag, and
     * optional gravity. Liquid contact with liquid physics enabled delegates
     * to its dedicated buoyancy calculation. Otherwise a support block selects
     * ground drag, and surface friction applies only outside liquid.
     *
     * <p> The horizontal components share the selected retention factor, while
     * vertical velocity uses air drag after gravity. The method returns a new
     * vector without storing it; the engine's later motion setter can still
     * modify or reject that proposal independently of the movement already
     * applied this tick.
     */
    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        if (liquidState.inLiquid() && computeLiquidPhysics()) {
            return updateMotionInLiquid(liquidState);
        }

        var blockStateStandingOn = getBlockStateStandingOn();
        var isOnGround = blockStateStandingOn.getBlockType() != BlockTypes.AIR;
        var slipperinessMultiplier = 1.0;
        // Skip support-block friction during liquid contact even when an entity
        // disables liquid buoyancy, keeping that surface multiplier out of submerged motion.
        if (!liquidState.inLiquid() && isOnGround) {
            slipperinessMultiplier = blockStateStandingOn.getBlockStateData().friction();
        }

        var horizontalFactor = slipperinessMultiplier * (1 - (isOnGround ? getDragFactorOnGround() : getDragFactorInAir()));
        return new Vector3d(
                motion.x() * horizontalFactor,
                (motion.y() - (hasGravity() ? getGravity() : 0f)) * (1 - getDragFactorInAir()),
                motion.z() * horizontalFactor
        );
    }

    /**
     * Returns a new velocity after applying liquid-specific buoyancy, gravity,
     * and drag. Water parameters take precedence when the sample reports both
     * water and lava; the method does not merge the two liquids' settings.
     *
     * <p> Horizontal motion retains {@code 1 - dragFactor}, and the same
     * retention scales vertical motion after gravity is subtracted and
     * buoyancy is added. The calculation reads the current velocity but does
     * not store its result, leaving event-mediated assignment to the engine's
     * later motion update.
     */
    protected Vector3d updateMotionInLiquid(LiquidState liquidState) {
        var dragFactor = liquidState.inWater() ? getWaterDragFactor() : getLavaDragFactor();
        var buoyancy = liquidState.inWater() ? getWaterBuoyancy() : getLavaBuoyancy();
        var retain = 1.0 - dragFactor;
        var gravity = hasGravity() ? getGravity() : 0.0;

        return new Vector3d(
                motion.x() * retain,
                (motion.y() - gravity + buoyancy) * retain,
                motion.z() * retain
        );
    }

    /**
     * Attempts a velocity replacement after rejecting NaN components in the
     * initial argument. Cancellation preserves both velocity references;
     * acceptance keeps the old current vector as {@code lastMotion} and copies
     * the listener's vector without repeating the NaN check.
     */
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

    /**
     * Calculates knockback from current motion and submits the resulting
     * vector through {@code setMotion}. The setter's cancellation result is
     * discarded, so callers cannot infer accepted movement merely from normal
     * completion of this operation.
     */
    @Override
    public void knockback(Vector3dc knockbackSource, double knockback, double knockbackVertical, Vector3dc knockbackAdditional, boolean ignoreKnockbackResistance) {
        setMotion(calculateKnockbackMotion(knockbackSource, knockback, knockbackVertical, knockbackAdditional, ignoreKnockbackResistance));
    }

    /**
     * Clamps ordinary resistance values to the inclusive range {@code [0, 1]}
     * before storing them. NaN remains NaN through the clamp, and this setter
     * does not change current motion or broadcast metadata.
     */
    @Override
    public void setKnockbackResistance(@Range(from = 0, to = 1) float knockbackResistance) {
        this.knockbackResistance = Math.clamp(knockbackResistance, 0, 1);
    }

    /**
     * Builds a knockback vector from source direction, strengths, additional
     * motion, and current velocity. Unless resistance is ignored, positive
     * stored resistance scales all three knockback contributions before
     * direction selection. An almost coincident source uses a random
     * horizontal direction instead of normalizing a nearly zero displacement.
     *
     * <p> The chosen horizontal impulse receives the requested vertical
     * strength, then combines with half of current velocity and the additional
     * vector. The method returns a fresh result and leaves assignment to its
     * caller, preserving the separate cancellation decision in
     * {@code setMotion}.
     */
    protected Vector3d calculateKnockbackMotion(Vector3dc knockbackSource, double knockback, double knockbackVertical, Vector3dc knockbackAdditional, boolean ignoreKnockbackResistance) {
        if (!ignoreKnockbackResistance) {
            if (this.knockbackResistance > 0) {
                var factor = 1 - this.knockbackResistance;
                knockback *= factor;
                knockbackVertical *= factor;
                knockbackAdditional = knockbackAdditional.mul(factor, new Vector3d());
            }
        }

        Vector3d vec;
        var location = thisEntity.getLocation();
        if (location.distanceSquared(knockbackSource) <= 0.0001 /* 0.01 * 0.01 */) {
            // Nearly coincident positions have no stable source direction; choose
            // a horizontal impulse so the tiny separation does not dictate normalization.
            var rand = ThreadLocalRandom.current();
            var rx = rand.nextDouble(1) - 0.5;
            var rz = rand.nextDouble(1) - 0.5;
            vec = MathUtils.normalizeIfNotZero(new Vector3d(rx, 0, rz)).mul(knockback);
        } else {
            vec = MathUtils.normalizeIfNotZero(location.sub(knockbackSource, new Vector3d()).setComponent(1, 0)).mul(knockback);
        }
        vec.y = knockbackVertical;
        return motion.mul(0.5, new Vector3d()).add(vec).add(knockbackAdditional);
    }

    /**
     * Dispatches a landing proposal before internal fall handlers and the
     * standing block callback. Cancellation resets accumulated distance
     * immediately; normal completion resets it after those callbacks, while an
     * intervening exception can leave the previous distance stored.
     */
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

    /**
     * Clears accumulated fall distance without running landing callbacks or
     * damage calculations.
     */
    @Override
    public void resetFallDistance() {
        this.fallDistance = 0;
    }

    /**
     * Returns a positioned support block using the committed ground flag.
     * Airborne entities receive air at their floored current position;
     * grounded entities prefer the feet block unless it is air, then query the
     * block one level below.
     */
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

    /**
     * Checks for collidable support below candidate feet and collision-free
     * space for this entity's box. The box is raised by
     * {@code FAT_AABB_MARGIN} to avoid treating support contact as
     * penetration; no location update or space reservation occurs.
     */
    @Override
    public boolean canStandSafely(double x, double y, double z, Dimension dimension) {
        int blockX = (int) Math.floor(x);
        int groundY = (int) Math.ceil(y) - 1;
        int blockZ = (int) Math.floor(z);
        var blockUnder = dimension.getBlockState(blockX, groundY, blockZ);
        if (!blockUnder.getBlockStateData().hasCollision()) {
            return false;
        }
        // Lift the trial box by the physics margin so touching the supporting
        // surface does not make an otherwise clear standing position fail the query.
        var aabb = thisEntity.getAABB().translate(x, y + FAT_AABB_MARGIN, z, new AABBd());
        return dimension.getCollidingBlockStates(aabb) == null;
    }

    /**
     * Returns whether the entity is airborne and descending without the
     * blocking living effects. Blindness or slow falling rejects critical
     * eligibility when a living component exists; entities without one use the
     * motion and ground checks alone.
     */
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

    /**
     * Stores the gravity flag before broadcasting updated entity metadata. The
     * existing motion and fall distance remain unchanged, and a viewer failure
     * does not restore the previous gravity flag.
     */
    @Override
    public void setHasGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
        this.baseComponent.broadcastState();
    }

    /**
     * Returns the owning entity's current entity-collision capability for
     * force calculation.
     */
    @Override
    public boolean computeEntityCollisionMotion() {
        return thisEntity.hasEntityCollision();
    }

    /**
     * {@code Axis} selects matching vector coordinates and box faces for
     * collision calculations.
     */
    private enum Axis {
        /**
         * Horizontal X coordinate and the matching box faces.
         */
        X {
            /**
             * Returns the supplied box's minimum X face coordinate in blocks.
             */
            @Override
            double getMin(AABBdc aabb) {
                return aabb.minX();
            }

            /**
             * Returns the supplied box's maximum X face coordinate in blocks.
             */
            @Override
            double getMax(AABBdc aabb) {
                return aabb.maxX();
            }

            /**
             * Returns the supplied box's extent along the X axis in blocks.
             */
            @Override
            double getLength(AABBdc aabb) {
                return aabb.lengthX();
            }

            /**
             * Returns the supplied vector's X coordinate for this axis
             * operation.
             */
            @Override
            double getComponent(Vector3dc vec) {
                return vec.x();
            }

            /**
             * Replaces the supplied vector's X coordinate with the requested
             * value.
             */
            @Override
            void setComponent(Vector3d vec, double value) {
                vec.setComponent(0, value);
            }

            /**
             * Translates the supplied box along X and writes the result into
             * {@code dest}.
             */
            @Override
            void translate(AABBd aabb, double delta, AABBd dest) {
                aabb.translate(delta, 0, 0, dest);
            }

            /**
             * Replaces the supplied box's minimum X coordinate with the
             * requested value.
             */
            @Override
            void setMin(AABBd aabb, double value) {
                aabb.minX = value;
            }

            /**
             * Replaces the supplied box's maximum X coordinate with the
             * requested value.
             */
            @Override
            void setMax(AABBd aabb, double value) {
                aabb.maxX = value;
            }
        },
        /**
         * Vertical Y coordinate and the matching box faces.
         */
        Y {
            /**
             * Returns the supplied box's minimum Y face coordinate in blocks.
             */
            @Override
            double getMin(AABBdc aabb) {
                return aabb.minY();
            }

            /**
             * Returns the supplied box's maximum Y face coordinate in blocks.
             */
            @Override
            double getMax(AABBdc aabb) {
                return aabb.maxY();
            }

            /**
             * Returns the supplied box's extent along the Y axis in blocks.
             */
            @Override
            double getLength(AABBdc aabb) {
                return aabb.lengthY();
            }

            /**
             * Returns the supplied vector's Y coordinate for this axis
             * operation.
             */
            @Override
            double getComponent(Vector3dc vec) {
                return vec.y();
            }

            /**
             * Replaces the supplied vector's Y coordinate with the requested
             * value.
             */
            @Override
            void setComponent(Vector3d vec, double value) {
                vec.setComponent(1, value);
            }

            /**
             * Translates the supplied box along Y and writes the result into
             * {@code dest}.
             */
            @Override
            void translate(AABBd aabb, double delta, AABBd dest) {
                aabb.translate(0, delta, 0, dest);
            }

            /**
             * Replaces the supplied box's minimum Y coordinate with the
             * requested value.
             */
            @Override
            void setMin(AABBd aabb, double value) {
                aabb.minY = value;
            }

            /**
             * Replaces the supplied box's maximum Y coordinate with the
             * requested value.
             */
            @Override
            void setMax(AABBd aabb, double value) {
                aabb.maxY = value;
            }
        },
        /**
         * Horizontal Z coordinate and the matching box faces.
         */
        Z {
            /**
             * Returns the supplied box's minimum Z face coordinate in blocks.
             */
            @Override
            double getMin(AABBdc aabb) {
                return aabb.minZ();
            }

            /**
             * Returns the supplied box's maximum Z face coordinate in blocks.
             */
            @Override
            double getMax(AABBdc aabb) {
                return aabb.maxZ();
            }

            /**
             * Returns the supplied box's extent along the Z axis in blocks.
             */
            @Override
            double getLength(AABBdc aabb) {
                return aabb.lengthZ();
            }

            /**
             * Returns the supplied vector's Z coordinate for this axis
             * operation.
             */
            @Override
            double getComponent(Vector3dc vec) {
                return vec.z();
            }

            /**
             * Replaces the supplied vector's Z coordinate with the requested
             * value.
             */
            @Override
            void setComponent(Vector3d vec, double value) {
                vec.setComponent(2, value);
            }

            /**
             * Translates the supplied box along Z and writes the result into
             * {@code dest}.
             */
            @Override
            void translate(AABBd aabb, double delta, AABBd dest) {
                aabb.translate(0, 0, delta, dest);
            }

            /**
             * Replaces the supplied box's minimum Z coordinate with the
             * requested value.
             */
            @Override
            void setMin(AABBd aabb, double value) {
                aabb.minZ = value;
            }

            /**
             * Replaces the supplied box's maximum Z coordinate with the
             * requested value.
             */
            @Override
            void setMax(AABBd aabb, double value) {
                aabb.maxZ = value;
            }
        };

        /**
         * Returns the selected minimum face coordinate from the supplied box.
         */
        abstract double getMin(AABBdc aabb);

        /**
         * Returns the selected maximum face coordinate from the supplied box.
         */
        abstract double getMax(AABBdc aabb);

        /**
         * Returns the supplied box's extent along the selected axis.
         */
        abstract double getLength(AABBdc aabb);

        /**
         * Returns the supplied vector's coordinate along the selected axis.
         */
        abstract double getComponent(Vector3dc vec);

        /**
         * Replaces the supplied vector's coordinate along the selected axis.
         */
        abstract void setComponent(Vector3d vec, double value);

        /**
         * Translates the supplied box along this axis into the destination
         * box.
         */
        abstract void translate(AABBd aabb, double delta, AABBd dest);

        /**
         * Replaces the supplied box's minimum coordinate along the selected
         * axis.
         */
        abstract void setMin(AABBd aabb, double value);

        /**
         * Replaces the supplied box's maximum coordinate along the selected
         * axis.
         */
        abstract void setMax(AABBd aabb, double value);
    }
}
