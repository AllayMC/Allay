package cn.allay.inject.impl;

import cn.allay.inject.annotation.Dependency;
import cn.allay.inject.annotation.Impl;
import cn.allay.inject.interfaces.AttackComponent;
import cn.allay.inject.interfaces.ComponentImpl;
import cn.allay.inject.interfaces.HealthComponent;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleAttackComponent implements AttackComponent, ComponentImpl {

    @Dependency
    protected HealthComponent healthComponent;

    @Impl
    @Override
    public void attack(int amount) {
        healthComponent.setHealth(healthComponent.getHealth() - amount);
    }

    @Override
    public String getNamespaceId() {
        return "minecraft:attack_component";
    }
}
