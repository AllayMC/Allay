package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.api.entity.effect.EffectRegistry;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectAbsorptionType extends AbstractEffectType {

    public static final EffectAbsorptionType ABSORPTION_TYPE = new EffectAbsorptionType();
    private EffectAbsorptionType() {
        super(22, VanillaEffectIds.ABSORPTION, new Color(36, 107, 251));
    }
}
