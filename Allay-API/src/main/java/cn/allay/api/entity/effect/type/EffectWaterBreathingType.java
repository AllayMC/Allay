package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectWaterBreathingType extends AbstractEffectType {
    public static final EffectWaterBreathingType WATER_BREATHING_TYPE = new EffectWaterBreathingType();
    private EffectWaterBreathingType() {
        super(13, new Identifier("minecraft:water_breathing"), new Color(46, 82, 153));
    }
}
