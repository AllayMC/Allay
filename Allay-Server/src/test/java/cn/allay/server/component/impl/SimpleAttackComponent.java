package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.interfaces.Component;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.interfaces.AttackComponent;
import cn.allay.server.component.interfaces.HealthComponent;

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
