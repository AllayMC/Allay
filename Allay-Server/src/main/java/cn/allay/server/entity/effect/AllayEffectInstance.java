package cn.allay.server.entity.effect;

import cn.allay.api.entity.effect.EffectInstance;
import cn.allay.api.entity.effect.EffectType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@Setter
public class AllayEffectInstance implements EffectInstance {
    protected final EffectType type;
    protected int amplifier;
    protected int duration;
    protected boolean visible;
}
