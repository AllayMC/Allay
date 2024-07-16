package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 23/06/2024
 *
 * @author IWareQ
 */
public class EffectInfestedType extends AbstractEffectType {
    public static final EffectInfestedType INFESTED_TYPE = new EffectInfestedType();

    private EffectInfestedType() {
        super(35, new Identifier("minecraft:infested"), true);
    }
}
