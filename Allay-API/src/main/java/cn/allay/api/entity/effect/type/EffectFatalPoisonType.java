package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectFatalPoisonType extends AbstractEffectType {
    public static final EffectFatalPoisonType FATAL_POISON_TYPE = new EffectFatalPoisonType();
    private EffectFatalPoisonType() {
        super(25, new Identifier("minecraft:fatal_poison"), new Color(78, 147, 49), true);
    }
}
