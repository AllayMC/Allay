package org.allaymc.api.entity.effect;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.utils.Identifier;

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
     * @param amplifier The amplifier of the effect.
     * @param duration  The duration of the effect in ticks.
     * @param ambient   Whether the effect is ambient. Note that ambient 0 is level 1, and ambient 1 is level 2.
     * @param visible   Whether the effect is visible.
     *
     * @return A new instance of this effect.
     */
    EffectInstance createInstance(int amplifier, int duration, boolean ambient, boolean visible);

    /**
     * Called every tick while the effect is active.
     *
     * @param entity         The entity the effect is on.
     * @param effectInstance The instance of the effect.
     */
    default void onTick(Entity entity, EffectInstance effectInstance) {}

    /**
     * Called when the effect is added to an entity.
     *
     * @param entity         The entity the effect is on.
     * @param effectInstance The instance of the effect.
     */
    default void onAdd(Entity entity, EffectInstance effectInstance) {}

    /**
     * Called when the effect is removed from an entity.
     *
     * @param entity         The entity the effect is on.
     * @param effectInstance The instance of the effect.
     */
    default void onRemove(Entity entity, EffectInstance effectInstance) {}

    /**
     * Called when the entity owning the effect is damaged.
     *
     * @param entity         The entity the effect is on.
     * @param effectInstance The instance of the effect.
     * @param lastDamage     The last damage taken by the entity.
     */
    default void onEntityDamage(Entity entity, EffectInstance effectInstance, DamageContainer lastDamage) {}

    /**
     * Called when the entity owning the effect dies.
     *
     * @param entity         The entity the effect is on.
     * @param effectInstance The instance of the effect.
     */
    default void onEntityDies(Entity entity, EffectInstance effectInstance) {}

    /**
     * Gets the unique identifier of this effect type.
     *
     * @return The unique identifier of this effect type.
     */
    int getId();

    /**
     * Gets the identifier of this effect type.
     *
     * @return The identifier of this effect type.
     */
    Identifier getIdentifier();

    /**
     * Whether this effect is considered "bad" or not.
     *
     * @return Whether this effect is considered "bad" or not.
     */
    default boolean isBad() {
        return false;
    }
}
