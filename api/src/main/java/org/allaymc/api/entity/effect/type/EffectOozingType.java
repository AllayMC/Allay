package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author IWareQ
 */
public class EffectOozingType extends AbstractEffectType {
    public EffectOozingType() {
        super(34, new Identifier("minecraft:oozing"), true);
    }
}
