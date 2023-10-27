package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectResistanceType extends AbstractEffectType {
    public static final EffectResistanceType RESISTANCE_TYPE = new EffectResistanceType();
    private EffectResistanceType() {
        super(11, VanillaEffectIds.RESISTANCE, new Color(153, 69, 58));
    }
}
