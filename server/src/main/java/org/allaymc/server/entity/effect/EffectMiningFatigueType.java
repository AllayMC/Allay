package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectMiningFatigueType extends AbstractEffectType {
    public EffectMiningFatigueType() {
        super(4, new Identifier("minecraft:mining_fatigue"), new Color(0x4a4217), true);
    }
}
