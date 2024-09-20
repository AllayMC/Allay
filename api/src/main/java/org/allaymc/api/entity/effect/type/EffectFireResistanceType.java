package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectFireResistanceType extends AbstractEffectType {
    public EffectFireResistanceType() {
        super(12, new Identifier("minecraft:fire_resistance"));
    }
}
