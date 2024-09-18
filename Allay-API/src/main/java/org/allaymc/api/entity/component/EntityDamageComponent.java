package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;

/**
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

    boolean canBeAttacked(DamageContainer damage);

    DamageContainer getLastDamage();

    long getLastDamageTime();

    boolean hasFallDamage();
}
