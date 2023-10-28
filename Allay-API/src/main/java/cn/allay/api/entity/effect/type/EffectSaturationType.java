package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectSaturationType extends AbstractEffectType {
    public static final EffectSaturationType SATURATION_TYPE = new EffectSaturationType();
    private EffectSaturationType() {
        super(23, new Identifier("minecraft:saturation"), new Color(255, 0, 255));
    }
}
