package org.allaymc.server.component.impl;

import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.server.component.interfaces.AttackComponent;
import org.allaymc.server.component.interfaces.HealthComponent;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public class SimpleAttackComponent implements AttackComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:attack_component");

    @Dependency
    protected HealthComponent healthComponent;

    @Override
    public void attack(int amount) {
        healthComponent.setHealth(healthComponent.getHealth() - amount);
    }
}
