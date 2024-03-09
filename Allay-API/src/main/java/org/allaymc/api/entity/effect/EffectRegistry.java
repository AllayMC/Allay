package org.allaymc.api.entity.effect;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.registry.DoubleKeyMappedRegistry;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public interface EffectRegistry extends DoubleKeyMappedRegistry<Integer, Identifier, EffectType> {
    ApiInstanceHolder<EffectRegistry> REGISTRY = ApiInstanceHolder.create();

    static EffectRegistry getRegistry() {
        return REGISTRY.get();
    }
}
