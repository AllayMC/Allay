package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectBlindnessType extends AbstractEffectType {
    public static final EffectBlindnessType BLINDNESS = new EffectBlindnessType();

    private EffectBlindnessType() {
        super(15, new Identifier("minecraft:blindness"), true);
    }
}
