package cn.allay.component.impl;

import cn.allay.component.annotation.Dependency;
import cn.allay.component.annotation.Impl;
import cn.allay.component.api.ComponentImpl;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class TestDependencyComponent implements ComponentImpl, cn.allay.component.interfaces.TestDependencyComponent {

    @Dependency(namespaceId = "minecraft:name_component")
    protected ComponentImpl nameComponent;

    @Dependency(namespaceId = "minecraft:health_component")
    protected ComponentImpl healthComponent;

    @Dependency(namespaceId = "minecraft:attack_component")
    protected ComponentImpl attackComponent;

    @Override
    public String getNamespaceId() {
        return "minecraft:test_component";
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
