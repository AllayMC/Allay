package org.allaymc.server.entity.component.player;

import com.google.common.base.Preconditions;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerExperienceLevelChangeEvent;
import org.allaymc.api.eventbus.event.player.PlayerExperienceProgressChangeEvent;
import org.allaymc.server.entity.component.common.EntityAttributeComponentImpl;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class EntityPlayerAttributeComponentImpl extends EntityAttributeComponentImpl implements EntityPlayerAttributeComponent {

    @ComponentedObject
    private EntityPlayer thisPlayer;

    public EntityPlayerAttributeComponentImpl(AttributeType... attributeTypes) {
        super(attributeTypes);
    }

    @Override
    public int getExperienceLevel() {
        return (int) getAttributeValue(AttributeType.PLAYER_LEVEL);
    }

    @Override
    public void setExperienceLevel(int value) {
        var event = new PlayerExperienceLevelChangeEvent(thisPlayer, (int) getAttributeValue(AttributeType.PLAYER_LEVEL), value);
        event.call();
        if (event.isCancelled()) return;

        setAttribute(AttributeType.PLAYER_LEVEL, event.getNewExperienceLevel());
    }

    @Override
    public float getExperienceProgress() {
        return getAttributeValue(AttributeType.PLAYER_EXPERIENCE);
    }

    @Override
    public void setExperienceProgress(float value) {
        var event = new PlayerExperienceProgressChangeEvent(thisPlayer, getAttributeValue(AttributeType.PLAYER_EXPERIENCE), value);
        event.call();
        if (event.isCancelled()) return;

        setAttribute(AttributeType.PLAYER_EXPERIENCE, event.getNewExperienceProgress());
    }

    @Override
    public int getHunger() {
        return (int) getAttributeValue(AttributeType.PLAYER_HUNGER);
    }

    @Override
    public void setHunger(int value) {
        Preconditions.checkArgument(value >= 0 && value <= 20);
        setAttribute(AttributeType.PLAYER_HUNGER, value);
    }

    @Override
    public float getSaturation() {
        return getAttributeValue(AttributeType.PLAYER_SATURATION);
    }

    @Override
    public void setSaturation(float value) {
        Preconditions.checkArgument(value >= 0 && value <= 20);
        setAttribute(AttributeType.PLAYER_SATURATION, value);
    }

    @Override
    public float getExhaustion() {
        return getAttributeValue(AttributeType.PLAYER_EXHAUSTION);
    }

    @Override
    public void setExhaustion(float value) {
        Preconditions.checkArgument(value >= 0 && value <= 5);
        setAttribute(AttributeType.PLAYER_EXHAUSTION, value);
    }
}
