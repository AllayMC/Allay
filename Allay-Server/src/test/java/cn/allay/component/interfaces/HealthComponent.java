package cn.allay.component.interfaces;

import cn.allay.api.component.annotation.Inject;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface HealthComponent {
    @Inject
    int getHealth();

    @Inject
    void setHealth(int health);

    @Inject
    int getMaxHealth();

    @Inject
    boolean isDead();
}
