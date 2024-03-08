package org.allaymc.api.item.enchantment;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.registry.DoubleKeyMappedRegistry;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public interface EnchantmentRegistry extends DoubleKeyMappedRegistry<Short, Identifier, EnchantmentType> {
    ApiInstanceHolder<EnchantmentRegistry> REGISTRY = ApiInstanceHolder.create();

    static EnchantmentRegistry getRegistry() {
        return REGISTRY.get();
    }
}
