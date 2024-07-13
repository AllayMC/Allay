package org.allaymc.api.entity.component.player;

import org.allaymc.api.entity.component.EntityComponent;

/**
 * Allay Project 28/06/2024
 *
 * @author IWareQ
 */
public interface EntityPlayerHungerComponent extends EntityComponent {
    void tickHunger();

    void exhaust(float level);

    void saturate(int food, float saturation);

    boolean canEat();

    int getFoodLevel();

    void setFoodLevel(int foodLevel);

    float getFoodSaturationLevel();

    void setFoodSaturationLevel(float saturationLevel);

    float getFoodExhaustionLevel();

    void setFoodExhaustionLevel(float foodExhaustionLevel);

    int getFoodTickTimer();

    void setFoodTickTimer(int foodTickTimer);
}
