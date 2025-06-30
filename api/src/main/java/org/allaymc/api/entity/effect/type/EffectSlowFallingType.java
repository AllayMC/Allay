package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectSlowFallingType extends AbstractEffectType {
    EffectSlowFallingType() {
        super(27, new Identifier("minecraft:slow_falling"));
    }
}
