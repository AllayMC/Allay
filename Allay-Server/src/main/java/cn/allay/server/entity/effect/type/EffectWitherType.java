package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectWitherType extends AbstractEffectType {
    public static final EffectWitherType WITHER_TYPE = new EffectWitherType();
    private EffectWitherType() {
        super(20, VanillaEffectIds.WITHER, new Color(53, 42, 39), true);
    }
}