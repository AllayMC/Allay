package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 23/06/2024
 *
 * @author IWareQ
 */
public class EffectTrialOmenType extends AbstractEffectType {
    public static final EffectTrialOmenType TRIAL_OMEN_TYPE = new EffectTrialOmenType();

    private EffectTrialOmenType() {
        super(31, new Identifier("minecraft:trial_omen"));
    }
}
