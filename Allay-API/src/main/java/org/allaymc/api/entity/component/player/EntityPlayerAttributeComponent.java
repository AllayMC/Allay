package org.allaymc.api.entity.component.player;

import com.google.common.collect.Lists;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;

import java.util.Arrays;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public interface EntityPlayerAttributeComponent extends EntityAttributeComponent {

    static AttributeType[] basicPlayerAttributes() {
        var list = Lists.newArrayList(
                AttributeType.PLAYER_HUNGER,
                AttributeType.PLAYER_SATURATION,
                AttributeType.PLAYER_EXHAUSTION,
                AttributeType.PLAYER_LEVEL,
                AttributeType.PLAYER_EXPERIENCE
        );
        list.addAll(Arrays.asList(EntityAttributeComponent.basicEntityAttributes()));
        return list.toArray(AttributeType[]::new);
    }

    int getExperienceLevel();

    void setExperienceLevel(int value);

    float getExperienceProgress();

    void setExperienceProgress(float value);

    default void addExperience(int addition) {
        int currentLevel = getExperienceLevel();
        int requiredExpCurrentLevel = calculateRequireExperience(currentLevel);
        float total = getExperienceProgress() * requiredExpCurrentLevel + addition;

        while (total >= requiredExpCurrentLevel) {
            total -= requiredExpCurrentLevel;
            currentLevel++;
            requiredExpCurrentLevel = calculateRequireExperience(currentLevel);
        }

        setExperienceProgress(total / requiredExpCurrentLevel);
        setExperienceLevel(currentLevel);
    }

    /**
     * Calculate experience required for the level
     * @param level level
     * @return required experience
     */
    static int calculateRequireExperience(int level) {
        if (level >= 30) {
            return 112 + (level - 30) * 9;
        } else if (level >= 15) {
            return 37 + (level - 15) * 5;
        } else {
            return 7 + (level << 1);
        }
    }

    int getHunger();

    void setHunger(int value);

    float getSaturation();

    void setSaturation(float value);

    float getExhaustion();

    void setExhaustion(float value);
}
