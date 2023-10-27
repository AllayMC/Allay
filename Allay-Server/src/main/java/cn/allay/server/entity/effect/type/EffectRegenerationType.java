package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectRegenerationType extends AbstractEffectType {
    private EffectRegenerationType() {
        super(10, VanillaEffectIds.REGENERATION, new Color(205, 92, 171));
    }
}
