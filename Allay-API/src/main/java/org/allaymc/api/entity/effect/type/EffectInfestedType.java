package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author IWareQ
 */
public class EffectInfestedType extends AbstractEffectType {
    public EffectInfestedType() {
        super(35, new Identifier("minecraft:infested"), true);
    }
}
