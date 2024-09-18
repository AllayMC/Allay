package org.allaymc.api.entity.component.attribute;

import org.allaymc.api.entity.component.EntityComponent;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Collection;
import java.util.List;

/**
 * @author daoge_cmd
 */
public interface EntityAttributeComponent extends EntityComponent {

    static AttributeType[] basicEntityAttributes() {
        return new AttributeType[]{
                AttributeType.ABSORPTION,
                AttributeType.KNOCKBACK_RESISTANCE,
                AttributeType.HEALTH,
                AttributeType.MOVEMENT_SPEED,
                AttributeType.UNDER_WATER_MOVEMENT_SPEED,
                AttributeType.LAVA_MOVEMENT_SPEED
        };
    }

    default void tick() {}

    void addAttribute(AttributeType attributeType);

    Collection<Attribute> getAttributes();

    /**
     * Get attribute by type
     *
     * @param attributeType attribute type
     *
     * @return attribute, or null if type is not supported
     */
    Attribute getAttribute(AttributeType attributeType);

    void setAttribute(Attribute attribute);

    float getAttributeValue(AttributeType attributeType);

    void setAttributeValue(AttributeType attributeType, float value);

    default boolean supportHealth() {
        return getAttribute(AttributeType.HEALTH) != null;
    }

    default float getHealth() {
        return getAttributeValue(AttributeType.HEALTH);
    }

    void setHealth(float value);

    default float getMaxHealth() {
        return this.getAttribute(AttributeType.HEALTH).getMaxValue();
    }

    default void setMaxHealth(float value) {
        // TODO: Event
        var maxHealth = this.getAttribute(AttributeType.HEALTH);
        maxHealth.setMaxValue(value);
        this.setAttribute(maxHealth);
    }

    default void resetHealth() {
        setHealth(getMaxHealth());
    }

    default boolean supportAbsorption() {
        return getAttribute(AttributeType.ABSORPTION) != null;
    }

    default float getAbsorption() {
        return getAttributeValue(AttributeType.ABSORPTION);
    }

    default void setAbsorption(float absorption) {
        setAttributeValue(AttributeType.ABSORPTION, absorption);
    }

    default List<NbtMap> saveAttributes() {
        return getAttributes().stream().map(Attribute::toNBT).toList();
    }
}
