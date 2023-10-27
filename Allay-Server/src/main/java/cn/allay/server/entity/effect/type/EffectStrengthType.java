package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectStrengthType extends AbstractEffectType {
    private EffectStrengthType() {
        super(5, VanillaEffectIds.STRENGTH, new Color(147, 36, 35));
    }
}
