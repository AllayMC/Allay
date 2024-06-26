package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 23/06/2024
 *
 * @author IWareQ
 */
public class EffectWeavingType extends AbstractEffectType {
    public static final EffectWeavingType WEAVING_OMEN_TYPE = new EffectWeavingType();

    private EffectWeavingType() {
        super(33, new Identifier("minecraft:weaving"), new Color(120, 105, 90), true);
    }
}
