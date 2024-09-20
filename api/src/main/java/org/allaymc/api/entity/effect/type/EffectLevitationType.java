package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectLevitationType extends AbstractEffectType {
    public EffectLevitationType() {
        super(24, new Identifier("minecraft:levitation"), true);
    }
}
