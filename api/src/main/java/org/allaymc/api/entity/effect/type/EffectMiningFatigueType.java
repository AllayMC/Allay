package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectMiningFatigueType extends AbstractEffectType {
    public EffectMiningFatigueType() {
        super(4, new Identifier("minecraft:mining_fatigue"), true);
    }
}
