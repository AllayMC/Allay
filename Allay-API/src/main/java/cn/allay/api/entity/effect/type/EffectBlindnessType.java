package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectBlindnessType extends AbstractEffectType {
    public static final EffectBlindnessType BLINDNESS_TYPE = new EffectBlindnessType();
    private EffectBlindnessType() {
        super(15, new Identifier("minecraft:blindness"), new Color(191, 192, 192), true);
    }
}
