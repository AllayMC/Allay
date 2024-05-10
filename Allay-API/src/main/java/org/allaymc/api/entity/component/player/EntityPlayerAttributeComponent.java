package org.allaymc.api.entity.component.player;

import com.google.common.base.Preconditions;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public interface EntityPlayerAttributeComponent extends EntityAttributeComponent {

    default int getExperienceLevel() {
        return (int) getAttributeValue(AttributeType.PLAYER_LEVEL);
    }

    default void setExperienceLevel(int value) {
        Preconditions.checkArgument(value >= 0 && value <= 24791);
        setAttribute(AttributeType.PLAYER_LEVEL, value);
    }

    default float getExperience() {
        return getAttributeValue(AttributeType.PLAYER_EXPERIENCE);
    }

    default void setExperience(float value) {
        Preconditions.checkArgument(value >= 0 && value <= 1);
        setAttribute(AttributeType.PLAYER_EXPERIENCE, value);
    }

    default int getHunger() {
        return (int) getAttributeValue(AttributeType.PLAYER_HUNGER);
    }

    default void setHunger(int value) {
        Preconditions.checkArgument(value >= 0 && value <= 20);
        setAttribute(AttributeType.PLAYER_HUNGER, value);
    }

    default float getSaturation() {
        return getAttributeValue(AttributeType.PLAYER_SATURATION);
    }

    default void setSaturation(float value) {
        Preconditions.checkArgument(value >= 0 && value <= 20);
        setAttribute(AttributeType.PLAYER_SATURATION, value);
    }

    default float getExhaustion() {
        return getAttributeValue(AttributeType.PLAYER_EXHAUSTION);
    }

    default void setExhaustion(float value) {
        Preconditions.checkArgument(value >= 0 && value <= 5);
        setAttribute(AttributeType.PLAYER_EXHAUSTION, value);
    }
}
