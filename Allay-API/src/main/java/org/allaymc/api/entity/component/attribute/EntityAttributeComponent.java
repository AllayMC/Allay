package org.allaymc.api.entity.component.attribute;

import org.allaymc.api.entity.component.EntityComponent;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Collection;
import java.util.List;

/**
 * @author daoge_cmd
 */
public interface EntityAttributeComponent extends EntityComponent {

    /**
     * Get basic entity attributes
     *
     * @return basic entity attributes
     */
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

    /**
     * Default tick method
     */
    default void tick() {}

    /**
     * Add attribute to entity
     *
     * @param attributeType attribute type
     */
    void addAttribute(AttributeType attributeType);

    /**
     * Get all entity attributes
     *
     * @return entity attributes
     */
    Collection<Attribute> getAttributes();

    /**
     * Get attribute by type
     *
     * @param attributeType attribute type
     *
     * @return attribute, or {@code null} if type is not supported
     */
    Attribute getAttribute(AttributeType attributeType);

    /**
     * Set attribute to entity
     *
     * @param attribute attribute
     */
    void setAttribute(Attribute attribute);

    /**
     * Get attribute value by type
     *
     * @param attributeType attribute type
     *
     * @return attribute value
     */
    float getAttributeValue(AttributeType attributeType);

    /**
     * Set attribute value by type
     *
     * @param attributeType attribute type
     * @param value         attribute value
     */
    void setAttributeValue(AttributeType attributeType, float value);

    /**
     * Check if entity supports health attribute
     *
     * @return {@code true} if entity supports health attribute, {@code false} otherwise
     */
    default boolean supportHealth() {
        return getAttribute(AttributeType.HEALTH) != null;
    }

    /**
     * Get entity health
     *
     * @return entity health
     */
    default float getHealth() {
        return getAttributeValue(AttributeType.HEALTH);
    }

    /**
     * Set entity health
     *
     * @param value entity health
     */
    void setHealth(float value);

    /**
     * Get entity max health
     *
     * @return entity max health
     */
    default float getMaxHealth() {
        return this.getAttribute(AttributeType.HEALTH).getMaxValue();
    }

    /**
     * Set entity max health
     *
     * @param value entity max health
     */
    default void setMaxHealth(float value) {
        // TODO: Event
        var maxHealth = this.getAttribute(AttributeType.HEALTH);
        maxHealth.setMaxValue(value);
        this.setAttribute(maxHealth);
    }

    /**
     * Reset entity health to max health
     */
    default void resetHealth() {
        setHealth(getMaxHealth());
    }

    /**
     * Check if entity supports absorption attribute
     *
     * @return {@code true} if entity supports absorption attribute, {@code false} otherwise
     */
    default boolean supportAbsorption() {
        return getAttribute(AttributeType.ABSORPTION) != null;
    }

    /**
     * Get entity absorption
     *
     * @return entity absorption
     */
    default float getAbsorption() {
        return getAttributeValue(AttributeType.ABSORPTION);
    }

    /**
     * Set entity absorption
     *
     * @param absorption entity absorption
     */
    default void setAbsorption(float absorption) {
        setAttributeValue(AttributeType.ABSORPTION, absorption);
    }

    /**
     * Save all entity attributes to NBT format
     *
     * @return entity attributes in NBT format
     */
    default List<NbtMap> saveAttributes() {
        return getAttributes().stream().map(Attribute::toNBT).toList();
    }
}
