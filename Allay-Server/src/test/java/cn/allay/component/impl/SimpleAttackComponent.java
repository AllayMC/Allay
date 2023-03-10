package cn.allay.component.impl;

import cn.allay.component.annotation.Dependency;
import cn.allay.component.annotation.Impl;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.AttackComponent;
import cn.allay.component.interfaces.HealthComponent;

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
