package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectMiningFatigueType extends AbstractEffectType {
    public static final EffectMiningFatigueType MINING_FATIGUE_TYPE = new EffectMiningFatigueType();
    private EffectMiningFatigueType() {
        super(4, new Identifier("minecraft:mining_fatigue"), new Color(74, 66, 23), true);
    }
}
