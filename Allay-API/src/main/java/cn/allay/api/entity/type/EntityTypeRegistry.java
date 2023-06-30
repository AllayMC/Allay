package cn.allay.api.entity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.MappedRegistry;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Map;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityTypeRegistry extends MappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> {
    ApiInstanceHolder<EntityTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static EntityTypeRegistry getRegistry() {
        return REGISTRY.get();
    }

    NbtMap getAvailableEntityIdentifierTag();
}
