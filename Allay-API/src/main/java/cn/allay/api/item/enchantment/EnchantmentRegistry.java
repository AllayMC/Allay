package cn.allay.api.item.enchantment;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public interface EnchantmentRegistry extends MappedRegistry<Short, EnchantmentType, Map<Short, EnchantmentType>> {
    ApiInstanceHolder<EnchantmentRegistry> REGISTRY = ApiInstanceHolder.of();

    static EnchantmentRegistry getRegistry() {
        return REGISTRY.get();
    }
}
