package org.allaymc.api.entity.effect.type;

import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectFireResistanceType extends AbstractEffectType {
    public static final EffectFireResistanceType FIRE_RESISTANCE_TYPE = new EffectFireResistanceType();
    private EffectFireResistanceType() {
        super(12, new Identifier("minecraft:fire_resistance"), new Color(228, 154, 58));
    }
}
