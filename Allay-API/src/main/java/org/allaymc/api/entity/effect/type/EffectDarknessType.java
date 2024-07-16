package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectDarknessType extends AbstractEffectType {
    public static final EffectDarknessType DARKNESS = new EffectDarknessType();

    private EffectDarknessType() {
        super(30, new Identifier("minecraft:darkness"), true);
    }
}
