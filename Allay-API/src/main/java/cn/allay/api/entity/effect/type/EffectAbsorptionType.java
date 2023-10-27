package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectAbsorptionType extends AbstractEffectType {

    public static final EffectAbsorptionType ABSORPTION_TYPE = new EffectAbsorptionType();
    private EffectAbsorptionType() {
        super(22, new Identifier("minecraft:absorption"), new Color(36, 107, 251));
    }
}
