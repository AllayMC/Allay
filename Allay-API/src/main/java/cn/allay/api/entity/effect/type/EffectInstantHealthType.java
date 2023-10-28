package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantHealthType extends AbstractEffectType {
    public static final EffectInstantHealthType INSTANT_HEALTH_TYPE = new EffectInstantHealthType();
    private EffectInstantHealthType() {
        super(6, new Identifier("minecraft:instant_health"), new Color(248, 36, 35));
    }
}
