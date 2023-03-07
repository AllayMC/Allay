package cn.allay.inject.impl;

import cn.allay.inject.annotation.Impl;
import cn.allay.inject.interfaces.ComponentImpl;
import cn.allay.inject.interfaces.HealthComponent;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleHealthComponent implements HealthComponent, ComponentImpl {

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
    public int getMaxHealth() {
        return maxHealth;
    }

    @Impl
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Impl
    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public String getNamespaceId() {
        return "minecraft:health_component";
    }
}
