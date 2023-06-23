package cn.allay.server.component.interfaces;

import cn.allay.api.component.annotation.Inject;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/4 <br>
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
