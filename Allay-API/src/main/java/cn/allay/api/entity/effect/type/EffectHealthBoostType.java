package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectHealthBoostType extends AbstractEffectType {
    public static final EffectHealthBoostType HEALTH_BOOST_TYPE = new EffectHealthBoostType();
    private EffectHealthBoostType() {
        super(21, new Identifier("minecraft:health_boost"), new Color(248, 125, 35));
    }
}
