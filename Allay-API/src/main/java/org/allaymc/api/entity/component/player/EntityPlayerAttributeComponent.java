package org.allaymc.api.entity.component.player;

import com.google.common.collect.Lists;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;

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
                AttributeType.PLAYER_EXPERIENCE_LEVEL,
                AttributeType.PLAYER_EXPERIENCE_PROGRESS
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

    default int getRequireExperienceForCurrentLevel() {
        return calculateRequireExperience(getExperienceLevel());
    }

    default int getExperienceInCurrentLevel() {
        return (int) (getExperienceProgress() * getRequireExperienceForCurrentLevel());
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

    default void resetFoodData() {
        setFoodLevel((int) AttributeType.PLAYER_HUNGER.getDefaultValue());
        setFoodSaturationLevel(AttributeType.PLAYER_SATURATION.getDefaultValue());
        setFoodExhaustionLevel(AttributeType.PLAYER_EXHAUSTION.getDefaultValue());
    }

    default int getFoodLevel() {
        return (int) getAttributeValue(AttributeType.PLAYER_HUNGER);
    }

    void setFoodLevel(int value);

    default float getFoodSaturationLevel() {
        return getAttributeValue(AttributeType.PLAYER_SATURATION);
    }

    default void setFoodSaturationLevel(float value) {
        value = Math.max(0, Math.min(value, (int) AttributeType.PLAYER_SATURATION.getMaxValue()));
        setAttributeValue(AttributeType.PLAYER_SATURATION, value);
    }

    default float getFoodExhaustionLevel() {
        return getAttributeValue(AttributeType.PLAYER_EXHAUSTION);
    }

    default void setFoodExhaustionLevel(float value) {
        value = Math.max(0, Math.min(value, (int) AttributeType.PLAYER_EXHAUSTION.getMaxValue()));
        setAttributeValue(AttributeType.PLAYER_EXHAUSTION, value);
    }

    void exhaust(float level);

    void saturate(int food, float saturation);

    boolean canEat();

    void setFoodTickTimer(int foodTickTimer);

    void sendAttributesToClient();
}
