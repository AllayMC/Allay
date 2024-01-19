package org.allaymc.api.entity.component.common;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.damage.DamageContainer;

/**
 * Allay Project 2024/1/12
 *
 * @author daoge_cmd
 */
public interface EntityDamageComponent extends EntityComponent {
    boolean attack(DamageContainer damage);

    default boolean attack(float damage) {
        return attack(DamageContainer.simpleAttack(damage));
    }

    default boolean attack(Entity attacker, float damage) {
        return attack(DamageContainer.entityAttack(attacker, damage));
    }

    boolean canAttack(DamageContainer damage);

    DamageContainer getLastDamage();

    long getLastDamageTime();

    boolean hasFallDamage();
}
