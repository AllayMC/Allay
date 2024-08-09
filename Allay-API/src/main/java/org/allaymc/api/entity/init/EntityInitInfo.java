package org.allaymc.api.entity.init;

import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityInitInfo extends ComponentInitInfo {
    Dimension dimension();

    NbtMap nbt();

    EntityType<? extends Entity> getEntityType();

    void setEntityType(EntityType<? extends Entity> entityType);
}
