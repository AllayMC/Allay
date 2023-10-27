package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectNightVisionType extends AbstractEffectType {
    public static final EffectNightVisionType NIGHT_VISION_TYPE = new EffectNightVisionType();
    private EffectNightVisionType() {
        super(16, new Identifier("minecraft:night_vision"), new Color(0, 0, 139));
    }
}
