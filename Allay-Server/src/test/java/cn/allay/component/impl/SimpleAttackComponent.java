package cn.allay.component.impl;

import cn.allay.component.annotation.Dependency;
import cn.allay.component.annotation.Impl;
import cn.allay.component.interfaces.AttackComponent;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.HealthComponent;
import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleAttackComponent implements AttackComponent, ComponentImpl {

    private static final Identifier IDENTIFIER = new Identifier("minecraft:attack_component");

    @Dependency
    protected HealthComponent healthComponent;

    @Impl
    @Override
    public void attack(int amount) {
        healthComponent.setHealth(healthComponent.getHealth() - amount);
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
