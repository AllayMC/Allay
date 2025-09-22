package org.allaymc.api.entity.effect;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public interface EffectType {
    /**
     * @see #createInstance(int, int, boolean, boolean)
     */
    default EffectInstance createInstance(int amplifier) {
        return createInstance(amplifier, 15);
    }

    /**
     * @see #createInstance(int, int, boolean, boolean)
     */
    default EffectInstance createInstance(int amplifier, int duration) {
        return createInstance(amplifier, duration, true);
    }

    /**
     * @see #createInstance(int, int, boolean, boolean)
     */
    default EffectInstance createInstance(int amplifier, int duration, boolean visible) {
        return createInstance(amplifier, duration, false, visible);
    }

    /**
     * Creates a new instance of this effect with the given amplifier, duration, ambient and visibility.
     *
     * @param amplifier The amplifier of the effect. Note that amplifier 0 is level 1, and ambient 1 is level 2
     * @param duration  The duration of the effect in ticks
     * @param ambient   Whether the effect is ambient
     * @param visible   Whether the effect is visible
     * @return A new instance of this effect
     */
    EffectInstance createInstance(int amplifier, int duration, boolean ambient, boolean visible);

    /**
     * Called every tick while the effect is active.
     *
     * @param entity         The entity the effect is on
     * @param effectInstance The instance of the effect
     */
    @ApiStatus.OverrideOnly
    default void onTick(EntityLiving entity, EffectInstance effectInstance) {
    }

    /**
     * Called when the effect is added to an entity.
     *
     * @param entity         The entity the effect is on
     * @param effectInstance The instance of the effect
     */
    @ApiStatus.OverrideOnly
    default void onAdd(EntityLiving entity, EffectInstance effectInstance) {
    }

    /**
     * Called when the effect is removed from an entity.
     *
     * @param entity         The entity the effect is on
     * @param effectInstance The instance of the effect
     */
    @ApiStatus.OverrideOnly
    default void onRemove(EntityLiving entity, EffectInstance effectInstance) {
    }

    /**
     * Called when the entity owning the effect is damaged.
     *
     * @param entity         The entity the effect is on
     * @param effectInstance The instance of the effect
     * @param lastDamage     The last damage taken by the entity
     */
    @ApiStatus.OverrideOnly
    default void onEntityDamage(EntityLiving entity, EffectInstance effectInstance, DamageContainer lastDamage) {
    }

    /**
     * Called when the entity owning the effect dies.
     *
     * @param entity         The entity the effect is on
     * @param effectInstance The instance of the effect
     */
    @ApiStatus.OverrideOnly
    default void onEntityDies(EntityLiving entity, EffectInstance effectInstance) {
    }

    /**
     * Gets the unique identifier of this effect type.
     *
     * @return The unique identifier of this effect type
     */
    int getId();

    /**
     * Gets the identifier of this effect type.
     *
     * @return The identifier of this effect type
     */
    Identifier getIdentifier();

    /**
     * Gets the color of this effect type.
     *
     * @return the color of this effect type
     */
    Color getColor();

    /**
     * Whether this effect is considered "bad" or not.
     *
     * @return Whether this effect is considered "bad" or not
     */
    default boolean isBad() {
        return false;
    }
}
