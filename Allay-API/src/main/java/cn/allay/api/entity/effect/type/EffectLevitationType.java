package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectLevitationType extends AbstractEffectType {
    public static final EffectLevitationType LEVITATION_TYPE = new EffectLevitationType();
    private EffectLevitationType() {
        super(24, new Identifier("minecraft:levitation"), new Color(206, 255, 255), true);
    }
}
