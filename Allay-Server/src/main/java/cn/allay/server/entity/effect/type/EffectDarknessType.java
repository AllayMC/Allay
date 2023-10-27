package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectDarknessType extends AbstractEffectType {
    public static final EffectDarknessType DARKNESS_TYPE = new EffectDarknessType();
    private EffectDarknessType() {
        super(30, VanillaEffectIds.DARKNESS, new Color(41, 39, 33), true);
    }
}
