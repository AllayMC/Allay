package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public interface EntityLivingComponent extends EntityComponent {
    /**
     * Attack this entity with the given damage container.
     *
     * @param damage         the damage container
     * @param ignoreCoolDown {@code true} to ignore the attack cool down, {@code false} otherwise
     * @return {@code true} if the entity was damaged, {@code false} otherwise.
     */
    boolean attack(DamageContainer damage, boolean ignoreCoolDown);

    /**
     * @see #attack(DamageContainer, boolean)
     */
    default boolean attack(DamageContainer damage) {
        return attack(damage, false);
    }

    /**
     * Attack this entity with the given damage.
     *
     * @param damage the damage
     * @return {@code true} if the entity was damaged, {@code false} otherwise.
     */
    default boolean attack(float damage) {
        return attack(DamageContainer.simpleAttack(damage));
    }

    /**
     * Attack this entity with the given attacker and damage.
     *
     * @param attacker the attacker
     * @param damage   the damage
     * @return {@code true} if the entity was damaged, {@code false} otherwise.
     */
    default boolean attack(Entity attacker, float damage) {
        return attack(DamageContainer.entityAttack(attacker, damage));
    }

    /**
     * Check if this entity can be attacked with the given damage container.
     *
     * @param damage the damage container
     * @return {@code true} if the entity can be attacked, {@code false} otherwise.
     */
    boolean canBeAttacked(DamageContainer damage);

    /**
     * Get the last damage that was dealt to this entity.
     *
     * @return the last damage container
     */
    DamageContainer getLastDamage();

    /**
     * Get the time when the last damage was dealt to this entity.
     *
     * @return the time in milliseconds
     */
    long getLastDamageTime();

    /**
     * Check if the entity has fall damage.
     *
     * @return {@code true} if the entity has fall damage, {@code false} otherwise.
     */
    boolean hasFallDamage();

    /**
     * Check if the entity has fire damage.
     *
     * @return {@code true} if the entity has fire damage, {@code false} otherwise.
     */
    boolean hasFireDamage();

    /**
     * Check if the entity has drowning damage.
     *
     * @return {@code true} if the entity has drowning damage, {@code false} otherwise.
     */
    boolean hasDrowningDamage();

    /**
     * Check if this entity can against fire damage even if it does not have fire resistance
     * effect. For example, netherite items. When return {@code true}, the entity will not be
     * able to be set on fire and method {@link #setOnFireTicks(int)} will always return {@code false}.
     *
     * @return {@code true} if the entity is fireproof, {@code false} otherwise.
     */
    default boolean isFireproof() {
        // TODO: netherite item
        return false;
    }

    /**
     * Get the on fire ticks of the entity.
     *
     * @return the on fire ticks, or zero if the entity is not on fire
     */
    int getOnFireTicks();

    /**
     * Set the on fire ticks of the entity.
     *
     * @param onFireTicks the on fire ticks
     * @return {@code true} if the on fire ticks was set, {@code false} if the entity is fireproof.
     */
    boolean setOnFireTicks(int onFireTicks);

    /**
     * Check if the entity is on fire.
     *
     * @return {@code true} if the entity is on fire, {@code false} otherwise.
     */
    default boolean isOnFire() {
        return getOnFireTicks() > 0;
    }

    /**
     * Gets the current number of air supply ticks remaining for this entity.
     * This value determines how long the entity can stay underwater before taking drowning damage.
     *
     * @return The current air supply ticks
     */
    int getAirSupplyTicks();

    /**
     * Sets the current number of air supply ticks for this entity.
     * Setting this to 0 will cause the entity to start taking drowning damage if underwater.
     *
     * @param ticks The number of air supply ticks to set
     */
    void setAirSupplyTicks(int ticks);

    /**
     * Gets the maximum number of air supply ticks this entity can have.
     * This represents the total time the entity can stay underwater before running out of air.
     *
     * @return The maximum air supply ticks
     */
    int getAirSupplyMaxTicks();

    /**
     * Sets the maximum number of air supply ticks this entity can have.
     * This affects how long the entity can stay underwater before needing to surface for air.
     *
     * @param ticks The maximum number of air supply ticks to set
     */
    void setAirSupplyMaxTicks(int ticks);

    /**
     * Checks if the entity can breathe.
     *
     * @return {@code true} if the entity can breathe, {@code false} otherwise.
     */
    boolean canBreathe();

    /**
     * Gets an unmodifiable view of the current effects applied to the entity.
     * <p>
     * Each entry in the returned map represents an active effect and its corresponding instance.
     *
     * @return an unmodifiable map containing the effects applied to the entity, where the keys are the effect types
     * and the values are the corresponding effect instances
     */
    @UnmodifiableView
    Map<EffectType, EffectInstance> getEffects();

    /**
     * Check if the entity has the specified effect.
     *
     * @param effectType the effect type to check
     * @return {@code true} if the entity has the specified effect, otherwise {@code false}.
     */
    boolean hasEffect(EffectType effectType);

    /**
     * Get the effect level of the specified effect.
     *
     * @param effectType the effect type to get
     * @return the effect level of the specified effect
     */
    int getEffectLevel(EffectType effectType);

    /**
     * Add the specified effect to the entity.
     *
     * @param effectInstance the effect instance to add
     * @return {@code true} if the effect is added successfully, otherwise {@code false}.
     */
    boolean addEffect(EffectInstance effectInstance);

    /**
     * Remove the specified effect from the entity.
     *
     * @param effectType the effect type to remove
     */
    void removeEffect(EffectType effectType);

    /**
     * Remove all effects from the entity.
     */
    void removeAllEffects();

    /**
     * Check if the specific effect can apply on the entity.
     *
     * @param effectType the specific effect
     * @return {@code true} if the specific effect can apply on the entity, otherwise {@code false}.
     */
    default boolean canApplyEffect(EffectType effectType) {
        return true;
    }
}
