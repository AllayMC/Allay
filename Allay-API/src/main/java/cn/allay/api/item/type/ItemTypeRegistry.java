package cn.allay.api.item.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemTypeRegistry extends MappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> {

    ApiInstanceHolder<ItemTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static ItemTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
