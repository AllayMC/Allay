package org.allaymc.api.entity.effect.type;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

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
