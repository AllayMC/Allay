package cn.allay.api.entity.effect;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.DoubleKeyMappedRegistry;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public interface EffectRegistry extends DoubleKeyMappedRegistry<Integer, Identifier, Class<? extends Effect>> {
    ApiInstanceHolder<EffectRegistry> REGISTRY = ApiInstanceHolder.of();

    static EffectRegistry getRegistry() {
        return REGISTRY.get();
    }
}
