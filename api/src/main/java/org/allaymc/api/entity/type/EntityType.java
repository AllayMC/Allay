package org.allaymc.api.entity.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.utils.Identified;

/**
 * @author daoge_cmd
 */
public interface EntityType<T extends Entity> extends Identified {
    /**
     * Creates a new instance of this entity type with the given initialization information.
     *
     * @param info The initialization information for the entity.
     *
     * @return A new instance of this entity type.
     */
    T createEntity(EntityInitInfo info);

    /**
     * Creates a new instance of this entity type with default initialization information.
     *
     * @return A new instance of this entity type.
     */
    default T createEntity() {
        return this.createEntity(EntityInitInfo.builder().build());
    }
}
