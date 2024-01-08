package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.identifier.Identifier;

import java.awt.Color;

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
