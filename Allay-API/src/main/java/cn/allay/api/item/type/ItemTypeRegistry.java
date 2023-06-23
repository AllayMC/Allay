package cn.allay.api.item.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemTypeRegistry extends MappedRegistry<Identifier, ItemType<?>, Map<Identifier, ItemType<?>>> {

    ApiInstanceHolder<ItemTypeRegistry> REGISTRY = ApiInstanceHolder.of();

    static ItemTypeRegistry getRegistry() {
        return REGISTRY.get();
    }
}
