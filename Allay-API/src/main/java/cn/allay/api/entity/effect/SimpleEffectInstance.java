package cn.allay.api.entity.effect;

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
public final class SimpleEffectInstance implements EffectInstance {
    private final EffectType type;
    private int amplifier;
    private int duration;
    private boolean visible;
}
