package cn.allay.component.interfaces;

import cn.allay.component.annotation.ComponentInject;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface HealthComponent {
    @ComponentInject
    int getHealth();
    @ComponentInject
    int getMaxHealth();
    @ComponentInject
    void setHealth(int health);
    @ComponentInject
    boolean isDead();
}
