package org.allaymc.api.entity.component.player;

import org.allaymc.api.entity.component.common.EntityAttributeComponent;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public interface EntityPlayerAttributeComponent extends EntityAttributeComponent {

    int getExperienceLevel();

    void setExperienceLevel(int value);

    float getExperience();

    void setExperience(float value);

    int getHunger();

    void setHunger(int value);

    float getSaturation();

    void setSaturation(float value);

    float getExhaustion();

    void setExhaustion(float value);
}
