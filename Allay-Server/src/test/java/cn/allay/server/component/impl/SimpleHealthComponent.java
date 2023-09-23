package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.interfaces.Component;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.interfaces.HealthComponent;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public class SimpleHealthComponent implements HealthComponent, Component {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:health_component");

    protected final int maxHealth;
    protected int health;

    public SimpleHealthComponent(int maxHealth) {
        this(maxHealth, maxHealth);
    }

    public SimpleHealthComponent(int maxHealth, int health) {
        this.maxHealth = maxHealth;
        this.health = health;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }
}
