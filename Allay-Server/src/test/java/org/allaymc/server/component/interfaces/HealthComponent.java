package org.allaymc.server.component.interfaces;

import org.allaymc.api.component.interfaces.Component;

/**
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface HealthComponent extends Component {
    int getHealth();

    void setHealth(int health);

    int getMaxHealth();

    boolean isDead();
}
