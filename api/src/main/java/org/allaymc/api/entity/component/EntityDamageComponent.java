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
     * @param damage the damage container.
     *
     * @return {@code true} if the entity was damaged, {@code false} otherwise.
     */
    boolean attack(DamageContainer damage);

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
     * Check if this entity has fall damage.
     *
     * @return {@code true} if the entity has fall damage, {@code false} otherwise.
     */
    boolean hasFallDamage();
}
