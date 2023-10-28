package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectStrengthType extends AbstractEffectType {
    public static final EffectStrengthType STRENGTH_TYPE = new EffectStrengthType();
    private EffectStrengthType() {
        super(5, new Identifier("minecraft:strength"), new Color(147, 36, 35));
    }
}
