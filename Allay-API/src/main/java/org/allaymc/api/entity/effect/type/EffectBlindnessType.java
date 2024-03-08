package org.allaymc.api.entity.effect.type;

import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectBlindnessType extends AbstractEffectType {
    public static final EffectBlindnessType BLINDNESS_TYPE = new EffectBlindnessType();
    private EffectBlindnessType() {
        super(15, new Identifier("minecraft:blindness"), new Color(191, 192, 192), true);
    }
}
