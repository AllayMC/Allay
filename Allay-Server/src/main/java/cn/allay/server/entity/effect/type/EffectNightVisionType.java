package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectNightVisionType extends AbstractEffectType {
    public static final EffectNightVisionType NIGHT_VISION_TYPE = new EffectNightVisionType();
    private EffectNightVisionType() {
        super(16, VanillaEffectIds.NIGHT_VISION, new Color(0, 0, 139));
    }
}
