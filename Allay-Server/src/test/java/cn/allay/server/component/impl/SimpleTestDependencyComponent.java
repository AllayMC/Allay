package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.interfaces.Component;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.interfaces.TestDependencyComponent;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public class SimpleTestDependencyComponent implements Component, TestDependencyComponent {


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
