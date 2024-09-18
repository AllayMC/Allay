package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectResistanceType extends AbstractEffectType {
    public EffectResistanceType() {
        super(11, new Identifier("minecraft:resistance"));
    }
}
