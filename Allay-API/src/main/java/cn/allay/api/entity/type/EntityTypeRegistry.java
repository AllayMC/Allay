package cn.allay.api.entity.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/20 <br>
 * Allay Project <br>
 */
public interface EntityTypeRegistry extends MappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> {
    ApiInstanceHolder<EntityTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static EntityTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
