package org.allaymc.api.entity.component.attribute;

import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityComponent;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Collection;
import java.util.List;

/**
 * @author daoge_cmd
 */
public interface EntityAttributeComponent extends EntityComponent {

    /**
     * Get basic entity attributes.
     *
     * @return basic entity attributes.
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
     * Add attribute to entity.
     *
     * @param attributeType attribute type
     */
    void addAttribute(AttributeType attributeType);

    /**
     * Get all entity attributes.
     *
     * @return entity attributes.
     */
    Collection<Attribute> getAttributes();

    /**
     * Get attribute by type.
     *
     * @param attributeType attribute type
     *
     * @return attribute, or {@code null} if type is not supported.
     */
    Attribute getAttribute(AttributeType attributeType);

    /**
     * Set attribute to entity.
     *
     * @param attribute attribute.
     */
    void setAttribute(Attribute attribute);

    /**
     * Get attribute value by type.
     *
     * @param attributeType attribute type.
     *
     * @return attribute value.
     */
    float getAttributeValue(AttributeType attributeType);

    /**
     * Set attribute value by type.
     *
     * @param attributeType attribute type.
     * @param value         attribute value.
     */
    void setAttributeValue(AttributeType attributeType, float value);

    /**
     * Check if entity support specified attribute type.
     *
     * @param attributeType the attribute type to check.
     *
     * @return {@code true} if entity supports the specified attribute type, {@code false} otherwise.
     */
    default boolean supportAttribute(AttributeType attributeType) {
        return getAttribute(attributeType) != null;
    }

    /**
     * Get entity health.
     *
     * @return entity health.
     */
    default float getHealth() {
        return getAttributeValue(AttributeType.HEALTH);
    }

    /**
     * Set entity health.
     *
     * @param value entity health.
     */
    void setHealth(float value);

    /**
     * Get entity max health.
     *
     * @return entity max health.
     */
    default float getMaxHealth() {
        return this.getAttribute(AttributeType.HEALTH).getMaxValue();
    }

    /**
     * Set entity max health.
     *
     * @param value entity max health.
     */
    default void setMaxHealth(float value) {
        // TODO: Event
        var maxHealth = this.getAttribute(AttributeType.HEALTH);
        maxHealth.setMaxValue(value);
        this.setAttribute(maxHealth);
    }

    /**
     * Reset entity health to max health.
     */
    default void resetHealth() {
        setHealth(getMaxHealth());
    }

    /**
     * Kill the entity.
     * <p>
     * Compared to {@link EntityBaseComponent#despawn()} method, this method will set the health
     * of this entity to zero, rather than remove the entity directly.
     */
    default void kill() {
        setHealth(0);
    }

    /**
     * Get entity absorption.
     *
     * @return entity absorption.
     */
    default float getAbsorption() {
        return getAttributeValue(AttributeType.ABSORPTION);
    }

    /**
     * Set entity absorption.
     *
     * @param absorption entity absorption
     */
    default void setAbsorption(float absorption) {
        setAttributeValue(AttributeType.ABSORPTION, absorption);
    }

    /**
     * Save all entity attributes to NBT format.
     *
     * @return entity attributes in NBT format.
     */
    default List<NbtMap> saveAttributes() {
        return getAttributes().stream().map(Attribute::toNBT).toList();
    }
}
