package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectPoisonType extends AbstractEffectType {
    public static final EffectPoisonType POISON_TYPE = new EffectPoisonType();
    private EffectPoisonType() {
        super(19, new Identifier("minecraft:poison"), new Color(78, 147, 49), true);
    }
}
