package org.allaymc.api.entity.interfaces.player;

import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public interface EntityPlayerAttributeComponent extends EntityAttributeComponent {

    default void setLevel(@Range(from = 0, to = 24791) int value) {
        setAttribute(AttributeType.PLAYER_LEVEL, value);
    }

    default int getLevel() {
        return (int) getAttributeValue(AttributeType.PLAYER_LEVEL);
    }

    default void setExperience(@Range(from = 0, to = 1) float value) {
        setAttribute(AttributeType.PLAYER_EXPERIENCE, value);
    }

    default float getExperience() {
        return getAttributeValue(AttributeType.PLAYER_EXPERIENCE);
    }

    default void setHunger(@Range(from = 0, to = 20) int value) {
        setAttribute(AttributeType.PLAYER_HUNGER, value);
    }

    default int getHunger() {
        return (int) getAttributeValue(AttributeType.PLAYER_HUNGER);
    }

    default void setSaturation(@Range(from = 0, to = 20) float value) {
        setAttribute(AttributeType.PLAYER_SATURATION, value);
    }

    default float getSaturation() {
        return getAttributeValue(AttributeType.PLAYER_SATURATION);
    }

    default void setExhaustion(@Range(from = 0, to = 5) float value) {
        setAttribute(AttributeType.PLAYER_EXHAUSTION, value);
    }

    default float getExhaustion() {
        return getAttributeValue(AttributeType.PLAYER_EXHAUSTION);
    }
}
