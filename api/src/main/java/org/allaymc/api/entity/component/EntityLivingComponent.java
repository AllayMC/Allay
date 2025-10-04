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

    int DEFAULT_MAX_AIR_SUPPLY = 300;
    int DEFAULT_MAX_HEALTH = 20;

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
     * Check if this entity can be attacked with the given damage container. Notes that this method
     * will not check if the entity is alive or if is in damage cool down.
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
     * Check if the entity has void damage. Void damage will be applied to the entity every second
     * when its y coordinate is less than {@code minHeight - 18}.
     *
     * @return {@code true} if the entity has void damage, {@code false} otherwise.
     */
    boolean hasVoidDamage();

    /**
     * Check if this entity can against fire damage even if it does not have a fire resistance effect.
     * For example, netherite items. When return {@code true}, the entity will not be able to be set on
     * fire and method {@link #setOnFireTicks(int)} will always return {@code false}.
     *
     * @return {@code true} if the entity is fireproof, {@code false} otherwise.
     */
    boolean isFireproof();

    /**
     * Get the on fire ticks of the entity.
     *
     * @return the on fire ticks, or zero if the entity is not on fire or is fireproof.
     * @see #isFireproof()
     */
    int getOnFireTicks();

    /**
     * Set the on fire ticks of the entity. This method will return {@code false} if the entity is fireproof.
     *
     * @param onFireTicks the on fire ticks
     * @return {@code true} if the on fire ticks were set, {@code false} if the entity is fireproof.
     * @see #isFireproof()
     */
    boolean setOnFireTicks(int onFireTicks);

    /**
     * Extinguishes the entity by resetting its "on fire" ticks to {@code 0}.
     */
    default void extinguish() {
        setOnFireTicks(0);
    }

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

    /**
     * Gets the damage absorption value of this entity. This value represents how much
     * damage the entity can absorb before taking actual damage.
     *
     * @return the damage absorption value as a floating-point number
     */
    float getAbsorption();

    /**
     * Sets the damage absorption value for this entity.
     *
     * @param absorption the damage absorption value to set
     */
    void setAbsorption(float absorption);

    /**
     * Retrieves the current health of the entity.
     *
     * @return the current health of the entity as a floating-point number
     */
    float getHealth();

    /**
     * Sets the health of the entity.
     *
     * @param health the health value to set for the entity
     */
    void setHealth(float health);

    /**
     * Resets the health of the entity to its maximum value.
     * This method sets the current health of the entity to the value returned by {@link #getMaxHealth()}.
     */
    default void resetHealth() {
        setHealth(getMaxHealth());
    }

    /**
     * Kills the entity by setting its health to zero.
     */
    default void kill() {
        setHealth(0);
    }

    /**
     * Returns the maximum health value of the entity.
     *
     * @return the maximum health as a floating-point number
     */
    float getMaxHealth();

    /**
     * Sets the maximum health value for the entity.
     *
     * @param maxHealth the maximum health value to set, must be a positive floating-point number
     */
    void setMaxHealth(float maxHealth);
}
