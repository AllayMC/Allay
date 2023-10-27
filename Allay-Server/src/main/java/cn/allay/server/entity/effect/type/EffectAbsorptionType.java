package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectAbsorptionType extends AbstractEffectType {
    private EffectAbsorptionType() {
        super(22, VanillaEffectIds.ABSORPTION, new Color(36, 107, 251));
    }
}
