package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectBlindnessType extends AbstractEffectType {
    public EffectBlindnessType() {
        super(15, new Identifier("minecraft:blindness"), true);
    }
}
