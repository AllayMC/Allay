package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectSlowFallingType extends AbstractEffectType {
    public EffectSlowFallingType() {
        super(27, new Identifier("minecraft:slow_falling"));
    }
}
