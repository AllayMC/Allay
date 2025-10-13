package org.allaymc.api.entity.component;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * @author daoge_cmd
 */
public interface EntityPhysicsComponent extends EntityComponent {

    /**
     * Represents the default knockback value.
     */
    double DEFAULT_KNOCKBACK = 0.4;

    /**
     * Apply the entity's motion. Similar to {@link #updateMotion(boolean)}, this method
     * is used by physics engine, and allow entity to customize how its motion being applied.
     * <p>
     * This method is also required to be safe called in multithread environment.
     *
     * @return {@code true} if the entity's position is updated, {@code false} otherwise.
     */
    @ApiStatus.OverrideOnly
    boolean applyMotion();

    /**
     * Update the entity's motion. This method is used by physics engine, and allow
     * entity to customize how its motion being updated.
     * <p>
     * This method should be safe to be called in multithread environment since physics
     * engine may use parallel stream to handle all the entities.
     *
     * @param hasLiquidMotion whether this entity's motion is affected by liquid
     * @return the updated motion
     */
    @ApiStatus.OverrideOnly
    Vector3d updateMotion(boolean hasLiquidMotion);

    /**
     * Get the motion of this entity.
     *
     * @return the motion of this entity
     */
    Vector3dc getMotion();

    /**
     * Set the motion of this entity.
     *
     * @param motion the motion to set
     * @return {@code true} if motion is applied (not cancelled), {@code false} otherwise.
     */
    boolean setMotion(Vector3dc motion);

    /**
     * Get the last motion of this entity.
     *
     * @return the last motion of this entity
     */
    Vector3dc getLastMotion();

    /**
     * Set the motion of this entity.
     *
     * @param mx the motion x to set
     * @param my the motion y to set
     * @param mz the motion z to set
     */
    default void setMotion(double mx, double my, double mz) {
        setMotion(new Vector3d(mx, my, mz));
    }

    /**
     * Add the motion to this entity.
     *
     * @param add the motion to add
     */
    default void addMotion(Vector3dc add) {
        setMotion(getMotion().add(add, new Vector3d()));
    }

    /**
     * Add the motion to this entity.
     *
     * @param mx the motion x to add
     * @param my the motion y to add
     * @param mz the motion z to add
     */
    default void addMotion(double mx, double my, double mz) {
        setMotion(getMotion().add(mx, my, mz, new Vector3d()));
    }

    /**
     * Check if the entity has entity collision motion.
     *
     * @return {@code true} if the entity has entity collision motion.
     */
    boolean computeEntityCollisionMotion();

    /**
     * Check if the entity has block collision motion.
     * <p>
     * If return {@code true}, the physics engine will calculate a specific motion for the entity when
     * it is sticking in blocks to move the entity out of the blocks. When performing the above calculations,
     * the entity's {@link #applyMotion()} and {@link #updateMotion(boolean)} methods will not be called.
     * <p>
     * If return {@code false}, the entity's {@link #applyMotion()} and {@link #updateMotion(boolean)} methods
     * will always being called even if the entity is sticking in blocks.
     *
     * @return {@code true} if the entity has block collision motion.
     */
    boolean computeBlockCollisionMotion();

    /**
     * Check if the entity has liquid motion.
     *
     * @return {@code true} if the entity has liquid motion.
     */
    boolean computeLiquidMotion();

    /**
     * Check whether the entity's movement should be computed server-side.
     *
     * @return {@code true} if the entity's movement should be computed server-side, otherwise {@code false}.
     */
    default boolean computeMovementServerSide() {
        return true;
    }

    /**
     * Get the step height of this entity.
     *
     * @return the step height of this entity
     */
    default double getStepHeight() {
        return 0.0;
    }

    /**
     * Get the gravity of this entity.
     *
     * @return the gravity of this entity
     */
    default double getGravity() {
        return 0.08;
    }

    /**
     * Get the drag factor when on ground of this entity.
     * <p>
     * This value represents the percentage of velocity lost by the entity per tick on the
     * x and z axis. The bigger this value, the faster the entity stops.
     *
     * @return the drag factor when on ground of this entity
     */
    default double getDragFactorOnGround() {
        return 0.09;
    }

    /**
     * Get the drag factor when in air of this entity.
     * <p>
     * This value is similar to {@link #getDragFactorOnGround}, however this value
     * will be used to reduce motion along x-axis and z-axis when the entity is not on ground.
     * This value will always be used for motion along y-axis.
     *
     * @return the drag factor when in air of this entity
     */
    default double getDragFactorInAir() {
        return 0.02;
    }

    /**
     * Get the push speed reduction of this entity.
     *
     * @return the push speed reduction of this entity
     */
    default double getPushSpeedReduction() {
        return 1;
    }

    /**
     * Check if the entity has gravity.
     *
     * @return {@code true} if the entity has gravity, otherwise {@code false}.
     */
    boolean hasGravity();

    /**
     * Set if the entity has gravity.
     *
     * @param hasGravity {@code true} if the entity has gravity, otherwise {@code false}
     */
    void setHasGravity(boolean hasGravity);

    /**
     * Check if the entity is on the ground.
     *
     * @return {@code true} if the entity is on the ground, otherwise {@code false}.
     */
    boolean isOnGround();

    /**
     * Get the fall distance of this entity.
     *
     * @return the fall distance of this entity
     */
    double getFallDistance();

    /**
     * Called when the entity falls.
     */
    @ApiStatus.OverrideOnly
    void onFall(double fallDistance);

    /**
     * @see #knockback(Vector3dc, double, double, Vector3dc, boolean)
     */
    default void knockback(Vector3dc source) {
        knockback(source, DEFAULT_KNOCKBACK);
    }

    /**
     * @see #knockback(Vector3dc, double, double, Vector3dc, boolean)
     */
    default void knockback(Vector3dc source, double kb) {
        knockback(source, kb, kb);
    }

    /**
     * @see #knockback(Vector3dc, double, double, Vector3dc, boolean)
     */
    default void knockback(Vector3dc source, double kb, double kby) {
        knockback(source, kb, kby, new Vector3d());
    }

    /**
     * @see #knockback(Vector3dc, double, double, Vector3dc, boolean)
     */
    default void knockback(Vector3dc source, double kb, double kby, Vector3dc additionalMotion) {
        knockback(source, kb, kby, additionalMotion, false);
    }

    /**
     * Knockback the entity with specified kb value.
     *
     * @param source                    the source of the knockback
     * @param kb                        the knockback strength to apply
     * @param kby                       the knockback strength in y-axis
     * @param additionalMotion          the additional motion that will be appiled to the entity
     * @param ignoreKnockbackResistance {@code true} if the knockback resistance should be ignored
     */
    void knockback(Vector3dc source, double kb, double kby, Vector3dc additionalMotion, boolean ignoreKnockbackResistance);

    /**
     * Retrieve the knockback resistance of the entity.
     *
     * @return the knockback resistance value of this entity, represented as a float
     */
    float getKnockbackResistance();

    /**
     * Sets the knockback resistance of the entity. Knockback resistance determines
     * how resistant the entity is to being pushed or knocked back due to external forces,
     * such as attacks or collisions. Higher values provide increased resistance while
     * lower values allow for easier displacement.
     *
     * @param knockbackResistance the knockback resistance value to set. Must be a float
     *                            value between 0.0 (no resistance) and 1.0 (full resistance).
     */
    void setKnockbackResistance(@Range(from = 0, to = 1) float knockbackResistance);

    /**
     * Get the block which the entity is standing on.
     *
     * @return the block which the entity is standing on, or air if the entity is not standing on any block (and the pos will be {@code null})
     */
    Block getBlockStateStandingOn();

    /**
     * @see #canStandSafely(int, int, int, Dimension)
     */
    default boolean canStandSafely(Position3ic pos) {
        return canStandSafely(pos.x(), pos.y(), pos.z(), pos.dimension());
    }

    /**
     * Check if the specified position is a safe standing position.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return {@code true} if the specified position is a safe standing position, otherwise {@code false}.
     */
    boolean canStandSafely(int x, int y, int z, Dimension dimension);

    /**
     * Check if the entity can critical attack.
     *
     * @return {@code true} if the entity can critical attack, otherwise {@code false}.
     */
    boolean canCriticalAttack();
}
