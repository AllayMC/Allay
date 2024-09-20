package org.allaymc.api.entity;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public final class EntityHelper {
    /**
     * Creates a new entity from a NBT map.
     *
     * @param dimension The dimension the entity is in.
     * @param nbt       The NBT map to create the entity from.
     *
     * @return The new entity.
     */
    public static Entity fromNBT(Dimension dimension, NbtMap nbt) {
        var identifier = new Identifier(nbt.getString("identifier"));
        var entityType = Registries.ENTITIES.get(identifier);
        if (entityType == null) {
            log.warn("Unknown entity type {}", identifier);
            return null;
        }
        return entityType.createEntity(EntityInitInfo.builder().dimension(dimension).nbt(nbt).build());
    }
}
