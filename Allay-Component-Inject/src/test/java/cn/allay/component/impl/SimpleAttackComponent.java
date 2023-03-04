package cn.allay.component.impl;

import cn.allay.component.annotation.ComponentDependency;
import cn.allay.component.annotation.ComponentImpl;
import cn.allay.component.interfaces.AttackComponent;
import cn.allay.component.interfaces.HealthComponent;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleAttackComponent implements AttackComponent {

    @ComponentDependency
    protected HealthComponent healthComponent;

    @ComponentImpl
    @Override
    public void attack(int amount) {
        healthComponent.setHealth(healthComponent.getHealth() - amount);
    }
}
