package cn.allay.component.impl;

import cn.allay.component.annotation.Impl;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.HealthComponent;
import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleHealthComponent implements HealthComponent, ComponentImpl {

    private static final Identifier IDENTIFIER = new Identifier("minecraft:health_component");

    protected final int maxHealth;
    protected int health;

    public SimpleHealthComponent(int maxHealth) {
        this(maxHealth, maxHealth);
    }

    public SimpleHealthComponent(int maxHealth, int health) {
        this.maxHealth = maxHealth;
        this.health = health;
    }

    @Impl
    @Override
    public int getHealth() {
        return health;
    }

    @Impl
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Impl
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Impl
    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
