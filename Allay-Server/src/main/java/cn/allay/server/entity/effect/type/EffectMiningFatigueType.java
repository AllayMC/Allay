package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectMiningFatigueType extends AbstractEffectType {
    private EffectMiningFatigueType() {
        super(4, VanillaEffectIds.MINING_FATIGUE, new Color(74, 66, 23), true);
    }
}
