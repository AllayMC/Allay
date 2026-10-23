package org.allaymc.api.entity.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.property.type.EntityPropertyType;
import org.allaymc.api.utils.identifier.Identified;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * {@code EntityType} identifies an entity definition and creates instances
 * with its registered components and property metadata. Creating an entity
 * does not insert it into a dimension; callers configure initialization
 * information and use the dimension's entity manager to spawn the resulting
 * object.
 *
 * @author daoge_cmd
 */
public interface EntityType<T extends Entity> extends Identified {
    /**
     * Creates an unspawned instance using the supplied initialization
     * information. The server factory assigns this type to {@code info} before
     * construction, and the constructed entity still requires placement and
     * insertion through an entity manager.
     */
    T createEntity(EntityInitInfo info);

    /**
     * Creates an unspawned instance from empty initialization NBT and no
     * selected dimension. The caller must establish a usable location before
     * spawning the result; this overload does not insert it into a world.
     */
    default T createEntity() {
        return this.createEntity(EntityInitInfo.builder().build());
    }

    /**
     * Returns an unmodifiable view of this type's name-to-property
     * definitions. The server retains the builder's backing map rather than
     * copying it into the type, and individual property definitions keep their
     * own value-list contracts.
     */
    @UnmodifiableView
    Map<String, EntityPropertyType<?>> getProperties();

    /**
     * Returns whether this entity definition contains the supplied property
     * name.
     */
    default boolean hasProperty(String name) {
        return getProperties().containsKey(name);
    }

    /**
     * Returns whether this entity definition contains the supplied property's
     * name.
     */
    default boolean hasProperty(EntityPropertyType<?> propertyType) {
        return getProperties().containsKey(propertyType.getName());
    }
}
