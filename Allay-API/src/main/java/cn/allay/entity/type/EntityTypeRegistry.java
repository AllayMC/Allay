package cn.allay.entity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.identifier.Identifier;
import cn.allay.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/20 <br>
 * Allay Project <br>
 */
public interface EntityTypeRegistry extends MappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> {
    ApiInstanceHolder<EntityTypeRegistry> REGISTRY = ApiInstanceHolder.create();

    static EntityTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
