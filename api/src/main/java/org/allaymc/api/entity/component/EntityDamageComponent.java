package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;

/**
 * @author daoge_cmd
 */
public interface EntityDamageComponent extends EntityComponent {
    /**
     * Attack this entity with the given damage container.
     *
     * @param damage         the damage container.
     * @param ignoreCoolDown {@code true} to ignore the attack cool down, {@code false} otherwise.
     *
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
     * @param damage the damage.
     *
     * @return {@code true} if the entity was damaged, {@code false} otherwise.
     */
    default boolean attack(float damage) {
        return attack(DamageContainer.simpleAttack(damage));
    }

    /**
     * Attack this entity with the given attacker and damage.
     *
     * @param attacker the attacker.
     * @param damage   the damage.
     *
     * @return {@code true} if the entity was damaged, {@code false} otherwise.
     */
    default boolean attack(Entity attacker, float damage) {
        return attack(DamageContainer.entityAttack(attacker, damage));
    }

    /**
     * Check if this entity can be attacked with the given damage container.
     *
     * @param damage the damage container.
     *
     * @return {@code true} if the entity can be attacked, {@code false} otherwise.
     */
    boolean canBeAttacked(DamageContainer damage);

    /**
     * Get the last damage that was dealt to this entity.
     *
     * @return the last damage container.
     */
    DamageContainer getLastDamage();

    /**
     * Get the time when the last damage was dealt to this entity.
     *
     * @return the time in milliseconds.
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
     * Check if this entity can against fire damage even if
     * it does not have fire resistance effect.
     * <p>
     * For example, netherite items.
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
     * @return the on fire ticks, or zero if the entity is not on fire.
     */
    int getOnFireTicks();

    /**
     * Set the on fire ticks of the entity.
     *
     * @param onFireTicks the on fire ticks.
     *
     * @return {@code true} if the on fire ticks was set, {@code false} if the entity is fireproof or has fire resistance effect
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
}
