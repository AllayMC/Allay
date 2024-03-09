package org.allaymc.server.component.impl;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.TestDependencyComponent;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public class SimpleTestDependencyComponent implements TestDependencyComponent {


    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_component");


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
