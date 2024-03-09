package org.allaymc.api.entity.registry;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.registry.MappedRegistry;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Map;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityTypeRegistry extends MappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> {
    ApiInstanceHolder<EntityTypeRegistry> REGISTRY = ApiInstanceHolder.create();

    static EntityTypeRegistry getRegistry() {
        return REGISTRY.get();
    }

    NbtMap getAvailableEntityIdentifierTag();
}
