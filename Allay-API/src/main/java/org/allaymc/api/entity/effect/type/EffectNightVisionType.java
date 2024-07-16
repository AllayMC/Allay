package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectNightVisionType extends AbstractEffectType {
    public EffectNightVisionType() {
        super(16, new Identifier("minecraft:night_vision"));
    }
}
