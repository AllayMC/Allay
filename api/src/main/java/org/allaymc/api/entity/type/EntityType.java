package org.allaymc.api.entity.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.property.type.EntityPropertyType;
import org.allaymc.api.utils.identifier.Identified;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public interface EntityType<T extends Entity> extends Identified {
    /**
     * Creates a new instance of this entity type with the given initialization information.
     *
     * @param info The initialization information for the entity
     *
     * @return A new instance of this entity type
     */
    T createEntity(EntityInitInfo info);

    /**
     * Creates a new instance of this entity type with default initialization information.
     *
     * @return A new instance of this entity type
     */
    default T createEntity() {
        return this.createEntity(EntityInitInfo.builder().build());
    }

    /**
     * Get the properties of this entity type.
     *
     * @return an unmodifiable view of the map of entity property types
     */
    @UnmodifiableView
    Map<String, EntityPropertyType<?>> getProperties();

    /**
     * Check if the entity type has the specified property.
     *
     * @param name the property name
     * @return {@code true} if the entity type has the property, {@code false} otherwise.
     */
    default boolean hasProperty(String name) {
        return getProperties().containsKey(name);
    }

    /**
     * Check if the entity type has the specified property.
     *
     * @param propertyType the property type
     * @return {@code true} if the entity type has the property, {@code false} otherwise.
     */
    default boolean hasProperty(EntityPropertyType<?> propertyType) {
        return getProperties().containsKey(propertyType.getName());
    }
}
