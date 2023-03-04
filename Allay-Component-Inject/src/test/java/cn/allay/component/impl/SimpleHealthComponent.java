package cn.allay.component.impl;

import cn.allay.component.annotation.ComponentImpl;
import cn.allay.component.interfaces.HealthComponent;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleHealthComponent implements HealthComponent {

    protected final int maxHealth;
    protected int health;

    public SimpleHealthComponent(int maxHealth) {
        this(maxHealth, maxHealth);
    }

    public SimpleHealthComponent(int maxHealth, int health) {
        this.maxHealth = maxHealth;
        this.health = health;
    }

    @ComponentImpl
    @Override
    public int getHealth() {
        return health;
    }

    @ComponentImpl
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @ComponentImpl
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @ComponentImpl
    @Override
    public boolean isDead() {
        return health <= 0;
    }
}
