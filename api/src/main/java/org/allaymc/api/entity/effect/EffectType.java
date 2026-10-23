package org.allaymc.api.entity.effect;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.awt.*;

/**
 * {@code EffectType} identifies an effect, creates its mutable instances, and
 * supplies lifecycle callbacks for living entities. The living component
 * invokes callbacks while managing stored instances; creating an instance alone
 * does not attach it or invoke effects. Metadata also provides registry keys,
 * particle color, and harmful-effect classification.
 *
 * @author daoge_cmd
 */
public interface EffectType {
    /**
     * Creates a nonambient, visible instance with a duration of fifteen game
     * ticks. {@code amplifier} is zero-based, and construction alone does not
     * attach the instance to any entity.
     */
    default EffectInstance createInstance(int amplifier) {
        return createInstance(amplifier, 15);
    }

    /**
     * Creates a nonambient instance with visible particles and the supplied
     * tick duration. {@code amplifier} is zero-based; implementations determine
     * validation, and callers attach the returned instance through the entity
     * effect API.
     */
    default EffectInstance createInstance(int amplifier, int duration) {
        return createInstance(amplifier, duration, true);
    }

    /**
     * Creates a nonambient instance using the supplied duration and particle
     * visibility. {@code amplifier} is zero-based, duration uses game ticks,
     * and construction does not invoke any entity lifecycle callback.
     */
    default EffectInstance createInstance(int amplifier, int duration, boolean visible) {
        return createInstance(amplifier, duration, false, visible);
    }

    /**
     * Creates an instance with the supplied strength, remaining ticks, and
     * particle settings. {@code amplifier} zero represents level one; the
     * returned instance is not attached until a caller adds it to an entity.
     */
    EffectInstance createInstance(int amplifier, int duration, boolean ambient, boolean visible);

    /**
     * Handles one active-effect tick after the living component decrements
     * duration. This callback also runs on the tick reaching zero before
     * removal is requested. A cancelled removal allows later ticks with
     * nonpositive duration, and the default implementation performs no work.
     */
    @ApiStatus.OverrideOnly
    default void onTick(EntityLiving entity, EffectInstance effectInstance) {
    }

    /**
     * Handles an accepted addition when this effect type was previously absent.
     * The default implementation performs no work; replacing an already stored
     * instance does not invoke this callback in the living component.
     */
    @ApiStatus.OverrideOnly
    default void onAdd(EntityLiving entity, EffectInstance effectInstance) {
    }

    /**
     * Handles accepted removal after the living component removes the stored
     * instance. Cancelled removal and same-type replacement do not invoke this
     * callback, and the default implementation performs no cleanup.
     */
    @ApiStatus.OverrideOnly
    default void onRemove(EntityLiving entity, EffectInstance effectInstance) {
    }

    /**
     * Handles the owning entity's damage-processing phase using the supplied
     * damage container. Implementations can adjust that container before health
     * damage is applied; the default implementation leaves it unchanged.
     */
    @ApiStatus.OverrideOnly
    default void onEntityDamage(EntityLiving entity, EffectInstance effectInstance, DamageContainer lastDamage) {
    }

    /**
     * Handles the owning entity's death before its base state becomes dead.
     * Stored effects remain present during this callback, and the default
     * implementation performs no death-related side effects.
     */
    @ApiStatus.OverrideOnly
    default void onEntityDies(EntityLiving entity, EffectInstance effectInstance) {
    }

    /**
     * Returns the numeric registry identifier associated with this effect type.
     */
    int getId();

    /**
     * Returns the namespaced registry identifier associated with this effect
     * type.
     */
    Identifier getIdentifier();

    /**
     * Returns the particle color configured for this registered effect type.
     */
    Color getColor();

    /**
     * Returns whether this effect is classified as harmful by its metadata. The
     * default is {@code false}; classification does not itself implement
     * damage, movement changes, or any lifecycle callback.
     */
    default boolean isBad() {
        return false;
    }
}
