package cn.allay.server.component.interfaces;

import cn.allay.api.component.annotation.Inject;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
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
