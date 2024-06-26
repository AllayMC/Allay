package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectBlindnessType extends AbstractEffectType {
    public static final EffectBlindnessType BLINDNESS_TYPE = new EffectBlindnessType();

    private EffectBlindnessType() {
        super(15, new Identifier("minecraft:blindness"), new Color(31, 31, 35), true);
    }
}
