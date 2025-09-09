package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author IWareQ
 */
public class EffectTrialOmenType extends AbstractEffectType {
    public EffectTrialOmenType() {
        super(31, new Identifier("minecraft:trial_omen"), new Color(0x16a6a6));
    }
}
