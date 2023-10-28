package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectWitherType extends AbstractEffectType {
    public static final EffectWitherType WITHER_TYPE = new EffectWitherType();
    private EffectWitherType() {
        super(20, new Identifier("minecraft:wither"), new Color(53, 42, 39), true);
    }
}
