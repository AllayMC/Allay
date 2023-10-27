package cn.allay.server.entity.effect;

import cn.allay.api.entity.effect.EffectInstance;
import cn.allay.api.entity.effect.EffectType;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public abstract class AbstractEffectType implements EffectType {
    @Override
    public EffectInstance createInstance(int amplifier, int duration, boolean visible) {
        return new AllayEffectInstance(this, amplifier, duration, visible);
    }
}
