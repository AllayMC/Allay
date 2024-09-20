package org.allaymc.server.component.impl;

import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.interfaces.AttackComponent;
import org.allaymc.server.component.interfaces.HealthComponent;

/**
 * @author daoge_cmd
 */
public class SimpleAttackComponent implements AttackComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:attack_component");

    @Dependency
    protected HealthComponent healthComponent;

    @Override
    public void attack(int amount) {
        healthComponent.setHealth(healthComponent.getHealth() - amount);
    }
}
