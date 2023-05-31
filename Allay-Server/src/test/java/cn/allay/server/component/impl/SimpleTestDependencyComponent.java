package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.interfaces.ComponentImpl;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.interfaces.TestDependencyComponent;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleTestDependencyComponent implements ComponentImpl, TestDependencyComponent {

    private static final Identifier IDENTIFIER = new Identifier("minecraft:test_component");


    @Dependency(namespaceId = "minecraft:name_component")
    protected ComponentImpl nameComponent;

    @Dependency(namespaceId = "minecraft:health_component")
    protected ComponentImpl healthComponent;

    @Dependency(namespaceId = "minecraft:attack_component")
    protected ComponentImpl attackComponent;

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }

    @Impl
    @Override
    public ComponentImpl getNameComponent() {
        return nameComponent;
    }

    @Impl
    @Override
    public ComponentImpl getHealthComponent() {
        return healthComponent;
    }

    @Impl
    @Override
    public ComponentImpl getAttackComponent() {
        return attackComponent;
    }
}
