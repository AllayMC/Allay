package org.allaymc.api.entity;

import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.registry.EntityTypeRegistry;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.world.World;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Objects;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public final class EntityHelper {
    public static Entity fromNBT(World world, NbtMap nbt) {
        var identifier = new Identifier(nbt.getString("identifier"));
        var entityType = Objects.requireNonNull(EntityTypeRegistry.getRegistry().get(identifier), "Unknown entity type " + identifier);
        return entityType.createEntity(SimpleEntityInitInfo.builder().world(world).nbt(nbt).build());
    }
}
