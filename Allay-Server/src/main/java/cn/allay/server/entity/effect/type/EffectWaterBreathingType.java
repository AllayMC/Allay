package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectWaterBreathingType extends AbstractEffectType {
    private EffectWaterBreathingType() {
        super(13, VanillaEffectIds.WATER_BREATHING, new Color(46, 82, 153));
    }
}
