package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectResistanceType extends AbstractEffectType {
    public static final EffectResistanceType RESISTANCE_TYPE = new EffectResistanceType();

    private EffectResistanceType() {
        super(11, new Identifier("minecraft:resistance"), new Color(145, 70, 240));
    }
}
