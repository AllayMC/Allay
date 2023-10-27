package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectFireResistanceType extends AbstractEffectType {
    private EffectFireResistanceType() {
        super(12, VanillaEffectIds.FIRE_RESISTANCE, new Color(228, 154, 58));
    }
}
