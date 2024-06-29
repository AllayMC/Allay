package org.allaymc.api.entity.component.player;

import org.allaymc.api.entity.component.EntityComponent;

/**
 * Allay Project 28/06/2024
 *
 * @author IWareQ
 */
public interface EntityPlayerHungerComponent extends EntityComponent {
    default void tick0() {}

    void exhaust(float level);

    int getFoodLevel();

    void setFoodLevel(int foodLevel);

    int getFoodSaturationLevel();

    void setFoodSaturationLevel(int saturationLevel);

    float getFoodTickTimer();

    void setFoodTickTimer(float foodTickTimer);

    float getFoodExhaustionLevel();

    void setFoodExhaustionLevel(float foodExhaustionLevel);
}
