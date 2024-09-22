package org.allaymc.api.entity.component.player;

import com.google.common.collect.Lists;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;

import java.util.Arrays;

/**
 * @author daoge_cmd
 */
public interface EntityPlayerAttributeComponent extends EntityAttributeComponent {

    /**
     * Return an array of basic player attributes.
     *
     * @return array of basic player attributes.
     */
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

    /**
     * Calculates the required experience for a given level.
     *
     * @param level the level.
     *
     * @return the required experience.
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

    /**
     * Get the current experience level.
     *
     * @return the experience level.
     */
    int getExperienceLevel();

    /**
     * Set the experience level.
     *
     * @param value the new experience level.
     */
    void setExperienceLevel(int value);

    /**
     * Get the current experience progress.
     *
     * @return the experience progress.
     */
    float getExperienceProgress();

    /**
     * Set the experience progress.
     *
     * @param value the new experience progress.
     */
    void setExperienceProgress(float value);

    /**
     * Adds experience to the player.
     *
     * @param addition the amount of experience to add.
     */
    default void addExperience(int addition) {
        var currentLevel = getExperienceLevel();
        var requiredExpCurrentLevel = calculateRequireExperience(currentLevel);
        var total = getExperienceProgress() * requiredExpCurrentLevel + addition;

        while (total >= requiredExpCurrentLevel) {
            total -= requiredExpCurrentLevel;
            currentLevel++;
            requiredExpCurrentLevel = calculateRequireExperience(currentLevel);
        }

        setExperienceProgress(total / requiredExpCurrentLevel);
        setExperienceLevel(currentLevel);
    }

    /**
     * Get the required experience for the current level.
     *
     * @return the required experience.
     */
    default int getRequireExperienceForCurrentLevel() {
        return calculateRequireExperience(getExperienceLevel());
    }

    /**
     * Get the experience in the current level.
     *
     * @return the experience in the current level.
     */
    default int getExperienceInCurrentLevel() {
        return (int) (getExperienceProgress() * getRequireExperienceForCurrentLevel());
    }

    /**
     * Resets the food data.
     */
    default void resetFoodData() {
        setFoodLevel((int) AttributeType.PLAYER_HUNGER.getDefaultValue());
        setFoodSaturationLevel(AttributeType.PLAYER_SATURATION.getDefaultValue());
        setFoodExhaustionLevel(AttributeType.PLAYER_EXHAUSTION.getDefaultValue());
    }

    /**
     * Get the current food level.
     *
     * @return the food level.
     */
    default int getFoodLevel() {
        return (int) getAttributeValue(AttributeType.PLAYER_HUNGER);
    }

    /**
     * Set the food level.
     *
     * @param value the new food level.
     */
    void setFoodLevel(int value);

    /**
     * Get the current food saturation level.
     *
     * @return the food saturation level.
     */
    default float getFoodSaturationLevel() {
        return getAttributeValue(AttributeType.PLAYER_SATURATION);
    }

    /**
     * Set the food saturation level.
     *
     * @param value the new food saturation level.
     */
    default void setFoodSaturationLevel(float value) {
        value = Math.max(0, Math.min(value, (int) AttributeType.PLAYER_SATURATION.getMaxValue()));
        setAttributeValue(AttributeType.PLAYER_SATURATION, value);
    }

    /**
     * Get the current food exhaustion level.
     *
     * @return the food exhaustion level.
     */
    default float getFoodExhaustionLevel() {
        return getAttributeValue(AttributeType.PLAYER_EXHAUSTION);
    }

    /**
     * Set the food exhaustion level.
     *
     * @param value the new food exhaustion level.
     */
    default void setFoodExhaustionLevel(float value) {
        value = Math.max(0, Math.min(value, (int) AttributeType.PLAYER_EXHAUSTION.getMaxValue()));
        setAttributeValue(AttributeType.PLAYER_EXHAUSTION, value);
    }

    /**
     * Reduces the player's exhaustion level.
     *
     * @param level the amount of exhaustion to reduce by.
     */
    void exhaust(float level);

    /**
     * Increases the player's saturation level.
     *
     * @param food       the amount of food to add.
     * @param saturation the amount of saturation to add.
     */
    void saturate(int food, float saturation);

    /**
     * Check if the player can eat.
     *
     * @return {@code true} if the player can eat, {@code false} otherwise.
     */
    boolean canEat();

    /**
     * Set the food tick timer.
     *
     * @param foodTickTimer the new food tick timer.
     */
    void setFoodTickTimer(int foodTickTimer);

    /**
     * Send the attributes to the client.
     */
    void sendAttributesToClient();
}
