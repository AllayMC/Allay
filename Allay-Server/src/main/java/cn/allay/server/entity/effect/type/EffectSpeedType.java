package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectSpeedType extends AbstractEffectType {
    private EffectSpeedType() {
        super(1, VanillaEffectIds.SPEED, new Color(124, 175, 198));
    }
}
