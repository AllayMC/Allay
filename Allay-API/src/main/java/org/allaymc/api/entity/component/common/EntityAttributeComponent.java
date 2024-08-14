package org.allaymc.api.entity.component.common;

import org.allaymc.api.entity.attribute.Attribute;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.EntityComponent;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Collection;
import java.util.List;

/**
 * Allay Project 2023/7/9
 *
 * @author daoge_cmd
 */
public interface EntityAttributeComponent extends EntityComponent {

    static AttributeType[] basicEntityAttributes() {
        return new AttributeType[]{
                AttributeType.ABSORPTION,
                AttributeType.KNOCKBACK_RESISTANCE,
                AttributeType.HEALTH,
                AttributeType.MOVEMENT,
                AttributeType.FALL_DAMAGE
        };
    }

    default void tick() {}

    void addAttribute(AttributeType attributeType);

    Collection<Attribute> getAttributes();

    Attribute getAttribute(AttributeType attributeType);

    void setAttribute(AttributeType attributeType, float value);

    void setAttribute(Attribute attribute);

    float getAttributeValue(AttributeType attributeType);

    default float getHealth() {
        return getAttributeValue(AttributeType.HEALTH);
    }

    void setHealth(float value);

    default float getMaxHealth() {
        return this.getAttribute(AttributeType.HEALTH).getMaxValue();
    }

    void setMaxHealth(float value);

    default List<NbtMap> saveAttributes() {
        return getAttributes().stream().map(Attribute::toNBT).toList();
    }

    default void sendAttributesToClient() {}
}
