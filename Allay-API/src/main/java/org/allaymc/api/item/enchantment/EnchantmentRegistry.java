package org.allaymc.api.item.enchantment;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.registry.DoubleKeyMappedRegistry;
import org.allaymc.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public interface EnchantmentRegistry extends DoubleKeyMappedRegistry<Short, Identifier, EnchantmentType> {
    ApiInstanceHolder<EnchantmentRegistry> REGISTRY = ApiInstanceHolder.of();

    static EnchantmentRegistry getRegistry() {
        return REGISTRY.get();
    }
}
