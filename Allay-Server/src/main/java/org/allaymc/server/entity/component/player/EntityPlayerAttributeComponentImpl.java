package org.allaymc.server.entity.component.player;

import com.google.common.base.Preconditions;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerExperienceChangeEvent;
import org.allaymc.api.server.Server;
import org.allaymc.server.entity.component.common.EntityAttributeComponentImpl;

import java.util.List;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class EntityPlayerAttributeComponentImpl extends EntityAttributeComponentImpl implements EntityPlayerAttributeComponent {

    @ComponentedObject
    private EntityPlayer player;

    public EntityPlayerAttributeComponentImpl(List<AttributeType> attributeTypes) {
        super(attributeTypes);
    }

    @Override
    public int getExperienceLevel() {
        return (int) getAttributeValue(AttributeType.PLAYER_LEVEL);
    }

    @Override
    public void setExperienceLevel(int value) {
        Preconditions.checkArgument(value >= 0 && value <= 24791);
        setAttribute(AttributeType.PLAYER_LEVEL, value);
    }

    @Override
    public float getExperience() {
        return getAttributeValue(AttributeType.PLAYER_EXPERIENCE);
    }

    @Override
    public void setExperience(float value) {
        Preconditions.checkArgument(value >= 0 && value <= 1);

        var event = new PlayerExperienceChangeEvent(player, getAttributeValue(AttributeType.PLAYER_EXPERIENCE), value);
        Server.getInstance().getEventBus().callEvent(event);
        if (event.isCancelled()) {
            return;
        }

        setAttribute(AttributeType.PLAYER_EXPERIENCE, value);
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
