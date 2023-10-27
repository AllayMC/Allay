package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectSaturationType extends AbstractEffectType {
    public static final EffectSaturationType SATURATION_TYPE = new EffectSaturationType();
    private EffectSaturationType() {
        super(23, VanillaEffectIds.SATURATION, new Color(255, 0, 255));
    }
}
