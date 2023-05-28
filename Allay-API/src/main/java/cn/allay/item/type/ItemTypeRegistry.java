package cn.allay.item.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.identifier.Identifier;
import cn.allay.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemTypeRegistry extends MappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> {

    ApiInstanceHolder<ItemTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static ItemTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
