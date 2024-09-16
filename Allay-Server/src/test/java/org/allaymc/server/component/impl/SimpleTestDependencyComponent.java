package org.allaymc.server.component.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.interfaces.TestDependencyComponent;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public class SimpleTestDependencyComponent implements TestDependencyComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:test_component");


    @Dependency(identifier = "minecraft:name_component")
    protected Component nameComponent;

    @Dependency(identifier = "minecraft:health_component")
    protected Component healthComponent;

    @Dependency(identifier = "minecraft:attack_component")
    protected Component attackComponent;

    @Override
    public Component getNameComponent() {
        return nameComponent;
    }

    @Override
    public Component getHealthComponent() {
        return healthComponent;
    }

    @Override
    public Component getAttackComponent() {
        return attackComponent;
    }
}
