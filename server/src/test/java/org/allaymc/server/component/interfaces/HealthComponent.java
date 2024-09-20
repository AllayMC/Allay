package org.allaymc.server.component.interfaces;

import org.allaymc.api.component.interfaces.Component;

/**
 * @author daoge_cmd
 */
public interface HealthComponent extends Component {
    int getHealth();

    void setHealth(int health);

    int getMaxHealth();

    boolean isDead();
}
