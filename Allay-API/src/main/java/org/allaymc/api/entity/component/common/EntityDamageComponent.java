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
    void attack(DamageContainer damage);

    default void attack(float damage) {
        attack(DamageContainer.simpleAttack(damage));
    }

    default void attack(Entity attacker, float damage) {
        attack(DamageContainer.entityAttack(attacker, damage));
    }
}
