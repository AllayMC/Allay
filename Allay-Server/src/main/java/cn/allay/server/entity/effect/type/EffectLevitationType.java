package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectLevitationType extends AbstractEffectType {
    private EffectLevitationType() {
        super(24, VanillaEffectIds.LEVITATION, new Color(206, 255, 255), true);
    }
}
