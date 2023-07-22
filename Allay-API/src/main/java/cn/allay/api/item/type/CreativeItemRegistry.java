package cn.allay.api.item.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.item.ItemStack;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public interface CreativeItemRegistry extends MappedRegistry<Integer, ItemStack, Map<Integer, ItemStack>> {
    ApiInstanceHolder<CreativeItemRegistry> REGISTRY = ApiInstanceHolder.of();

    static CreativeItemRegistry getRegistry() {
        return REGISTRY.get();
    }
}
