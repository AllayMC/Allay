package org.allaymc.api.entity.component;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * {@code EntityPhysicsComponent} exposes motion simulation, collision
 * response, gravity, landing, and knockback for an entity. The physics engine
 * can compute movement for multiple entities in parallel, then invokes
 * post-movement callbacks sequentially so implementations can defer operations
 * that change shared world state.
 *
 * @author daoge_cmd
 */
public interface EntityPhysicsComponent extends EntityComponent {

    /**
     * {@code DEFAULT_KNOCKBACK} supplies the horizontal and vertical strength
     * used by the simplest knockback overload before resistance and
     * existing-motion adjustments are applied.
     */
    double DEFAULT_KNOCKBACK = 0.4;

    /**
     * {@code LiquidState} records water and lava contact independently for a
     * physics update. Both flags may be set when the collision sample includes
     * both liquids, and {@code inLiquid} accepts either flag.
     *
     * @param inWater whether water contact was detected
     * @param inLava whether lava contact was detected
     */
    record LiquidState(boolean inWater, boolean inLava) {
        /**
         * {@code NONE} represents an update with neither water nor lava
         * contact, allowing physics callers to reuse a single immutable empty
         * liquid state.
         */
        public static final LiquidState NONE = new LiquidState(false, false);

        /**
         * Returns whether this update records contact with water or lava.
         */
        public boolean inLiquid() {
            return inWater || inLava;
        }
    }

    /**
     * Applies the entity's current velocity through its movement and collision
     * rules. The physics engine may call this operation concurrently for
     * different entities; implementations must defer shared-world callbacks to
     * the sequential {@code afterApplyMotion} phase and report whether
     * location changed.
     */
    @ApiStatus.OverrideOnly
    boolean applyMotion();

    /**
     * Runs deferred post-movement work after the engine joins parallel
     * movement calculations. The default performs no work; implementations can
     * use this sequential phase for landing callbacks that cannot safely
     * modify shared world state in parallel.
     */
    @ApiStatus.OverrideOnly
    default void afterApplyMotion() {
    }

    /**
     * Calculates the next velocity from current state and the supplied liquid
     * sample. The engine may call this operation concurrently for different
     * entities, and the returned vector is subsequently offered to the normal
     * motion setter.
     */
    @ApiStatus.OverrideOnly
    Vector3d updateMotion(LiquidState liquidState);

    /**
     * Returns the entity's currently stored velocity in blocks per tick. The
     * server exposes its mutable vector through this read-only interface, so
     * callers must copy it before retaining an independently editable value.
     */
    Vector3dc getMotion();

    /**
     * Attempts a velocity replacement through the cancellable motion event.
     * The server rejects NaN components in the initial argument, then copies
     * the accepted listener vector without repeating that validation;
     * cancellation preserves stored motion and last motion.
     */
    boolean setMotion(Vector3dc motion);

    /**
     * Returns the velocity object retained before the last accepted motion
     * replacement. This is shared storage rather than a fresh copy, and other
     * paths such as NBT loading can update current motion without creating a
     * new history entry.
     */
    Vector3dc getLastMotion();

    /**
     * Replaces velocity using the supplied components in blocks per tick. The
     * proposal uses the normal cancellable motion setter, and this convenience
     * overload discards its boolean acceptance result.
     */
    default void setMotion(double mx, double my, double mz) {
        setMotion(new Vector3d(mx, my, mz));
    }

    /**
     * Adds the supplied vector to current velocity in a fresh proposal. The
     * proposal uses the normal cancellable motion setter, and this convenience
     * overload discards its boolean acceptance result.
     */
    default void addMotion(Vector3dc add) {
        setMotion(getMotion().add(add, new Vector3d()));
    }

    /**
     * Adds the supplied components to current velocity in a fresh proposal.
     * The proposal uses the normal cancellable motion setter, and this
     * convenience overload discards its boolean acceptance result.
     */
    default void addMotion(double mx, double my, double mz) {
        setMotion(getMotion().add(mx, my, mz, new Vector3d()));
    }

    /**
     * Returns whether entity collision forces should be calculated for this
     * entity.
     */
    boolean computeEntityCollisionMotion();

    /**
     * Returns whether the engine should use its special escape motion for an
     * entity stuck in blocks. The default enables that path, which bypasses
     * ordinary {@code applyMotion} and {@code updateMotion} calculations while
     * escape handling is selected.
     */
    default boolean computeBlockCollisionMotion() {
        return true;
    }

    /**
     * Returns whether liquid flow forces are enabled, with the default
     * enabled.
     */
    default boolean computeLiquidMotion() {
        return true;
    }

    /**
     * Returns whether liquid buoyancy and drag are enabled, with the default
     * enabled.
     */
    default boolean computeLiquidPhysics() {
        return true;
    }

    /**
     * Returns the upward acceleration in water, defaulting to {@code 0.075}
     * blocks per tick squared. Specialized entity implementations can override
     * this value to change the physics engine's corresponding motion behavior.
     */
    default double getWaterBuoyancy() {
        return 0.075;
    }

    /**
     * Returns the fractional velocity loss used for water, defaulting to
     * {@code 0.2}. The server retains {@code 1 - factor} of the corresponding
     * velocity before additional surface factors are considered.
     */
    default double getWaterDragFactor() {
        return 0.2;
    }

    /**
     * Returns the upward acceleration in lava, defaulting to {@code 0.04}
     * blocks per tick squared. Specialized entity implementations can override
     * this value to change the physics engine's corresponding motion behavior.
     */
    default double getLavaBuoyancy() {
        return 0.04;
    }

    /**
     * Returns the fractional velocity loss used for lava, defaulting to
     * {@code 0.5}. The server retains {@code 1 - factor} of the corresponding
     * velocity before additional surface factors are considered.
     */
    default double getLavaDragFactor() {
        return 0.5;
    }

    /**
     * Returns whether server movement computation is enabled, with the default
     * enabled.
     */
    default boolean computeMovementServerSide() {
        return true;
    }

    /**
     * Returns the maximum attempted step height, defaulting to {@code 0.0}
     * blocks, disabling stepping. Specialized entity implementations can
     * override this value to change the physics engine's corresponding motion
     * behavior.
     */
    default double getStepHeight() {
        return 0.0;
    }

    /**
     * Returns the downward gravitational acceleration, defaulting to
     * {@code 0.08} blocks per tick squared. Specialized entity implementations
     * can override this value to change the physics engine's corresponding
     * motion behavior.
     */
    default double getGravity() {
        return 0.08;
    }

    /**
     * Returns the fractional velocity loss used for grounded horizontal
     * movement, defaulting to {@code 0.09}. The server retains
     * {@code 1 - factor} of the corresponding velocity before additional
     * surface factors are considered.
     */
    default double getDragFactorOnGround() {
        return 0.09;
    }

    /**
     * Returns the fractional velocity loss used for air movement and ordinary
     * vertical movement, defaulting to {@code 0.02}. The server retains
     * {@code 1 - factor} of the corresponding velocity before additional
     * surface factors are considered.
     */
    default double getDragFactorInAir() {
        return 0.02;
    }

    /**
     * Returns the multiplier applied to the engine's base entity-collision
     * push, defaulting to {@code 1}. Despite the historical reduction name,
     * the current engine multiplies push strength directly by this value.
     */
    default double getPushSpeedReduction() {
        return 1;
    }

    /**
     * Returns whether gravity contributes to this entity's motion updates.
     */
    boolean hasGravity();

    /**
     * Sets whether future motion updates apply gravity and broadcasts the
     * updated entity state. The setter changes the acceleration flag without
     * immediately replacing velocity or resetting accumulated fall distance.
     */
    void setHasGravity(boolean hasGravity);

    /**
     * Returns whether the last committed ground state marks the entity as
     * supported.
     */
    boolean isOnGround();

    /**
     * Returns accumulated vertical falling distance measured in blocks.
     */
    double getFallDistance();

    /**
     * Processes a landing using the accumulated distance in blocks. The server
     * dispatches a cancellable fall event before internal fall handlers and
     * the supporting block callback, then resets accumulated distance after
     * normal completion or cancellation.
     */
    @ApiStatus.OverrideOnly
    void onFall(double fallDistance);

    /**
     * Resets the accumulated vertical falling distance to zero blocks.
     */
    void resetFallDistance();

    /**
     * Requests knockback away from the supplied source position. Uses
     * {@code DEFAULT_KNOCKBACK} for both horizontal and vertical strength. The
     * request follows the full knockback overload and remains subject to the
     * normal cancellable motion setter.
     */
    default void knockback(Vector3dc knockbackSource) {
        knockback(knockbackSource, DEFAULT_KNOCKBACK);
    }

    /**
     * Requests knockback away from the supplied source position. Uses the
     * supplied strength for both horizontal and vertical knockback. The
     * request follows the full knockback overload and remains subject to the
     * normal cancellable motion setter.
     */
    default void knockback(Vector3dc knockbackSource, double knockback) {
        knockback(knockbackSource, knockback, knockback);
    }

    /**
     * Requests knockback away from the supplied source position. Uses separate
     * horizontal and vertical strengths with no additional impulse. The
     * request follows the full knockback overload and remains subject to the
     * normal cancellable motion setter.
     */
    default void knockback(Vector3dc knockbackSource, double knockback, double knockbackVertical) {
        knockback(knockbackSource, knockback, knockbackVertical, new Vector3d());
    }

    /**
     * Requests knockback away from the supplied source position. Includes the
     * supplied additional impulse and honors stored knockback resistance. The
     * request follows the full knockback overload and remains subject to the
     * normal cancellable motion setter.
     */
    default void knockback(Vector3dc knockbackSource, double knockback, double knockbackVertical, Vector3dc knockbackAdditional) {
        knockback(knockbackSource, knockback, knockbackVertical, knockbackAdditional, false);
    }

    /**
     * Calculates a knockback proposal from the source, strengths, additional
     * impulse, resistance setting, and current velocity. The server adds half
     * the previous velocity and submits the result through the cancellable
     * motion setter; this operation does not return its acceptance result.
     */
    void knockback(Vector3dc knockbackSource, double knockback, double knockbackVertical, Vector3dc knockbackAdditional, boolean ignoreKnockbackResistance);

    /**
     * Returns the resistance factor used when calculating knockback
     * contributions.
     */
    float getKnockbackResistance();

    /**
     * Sets the knockback resistance after clamping the supplied factor to
     * {@code [0, 1]}. Resistance scales the horizontal, vertical, and
     * additional knockback contributions unless the full knockback request
     * explicitly ignores it.
     */
    void setKnockbackResistance(@Range(from = 0, to = 1) float knockbackResistance);

    /**
     * Returns the block at the feet or directly below the entity according to
     * ground state. Airborne entities receive an air block with the floored
     * current position, rather than an air result with a missing position.
     */
    Block getBlockStateStandingOn();

    /**
     * Checks standing safety at the horizontal center of the supplied block
     * position. The feet use its exact integer height, and the position's
     * dimension supplies both the supporting block and collision query.
     */
    default boolean canStandSafely(Position3ic pos) {
        return canStandSafely(pos.x() + 0.5, pos.y(), pos.z() + 0.5, pos.dimension());
    }

    /**
     * Returns whether the candidate feet position has collidable support and
     * room for this entity's box. The query uses the supplied dimension and a
     * small vertical margin; it does not move the entity or reserve that
     * space.
     */
    boolean canStandSafely(double x, double y, double z, Dimension dimension);

    /**
     * Returns whether current motion and effects permit a critical attack.
     */
    boolean canCriticalAttack();
}
