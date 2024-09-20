package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author IWareQ
 */
public class EffectWeavingType extends AbstractEffectType {
    public EffectWeavingType() {
        super(33, new Identifier("minecraft:weaving"), true);
    }
}
