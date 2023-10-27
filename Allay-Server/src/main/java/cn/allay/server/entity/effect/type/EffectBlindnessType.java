package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectBlindnessType extends AbstractEffectType {
    private EffectBlindnessType() {
        super(15, VanillaEffectIds.BLINDNESS, new Color(191, 192, 192), true);
    }
}
