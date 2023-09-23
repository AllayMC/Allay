package cn.allay.server.component.interfaces;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface HealthComponent {
    int getHealth();

    void setHealth(int health);

    int getMaxHealth();

    boolean isDead();
}
