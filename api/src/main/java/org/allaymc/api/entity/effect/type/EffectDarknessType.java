package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectDarknessType extends AbstractEffectType {
    EffectDarknessType() {
        super(30, new Identifier("minecraft:darkness"), true);
    }
}
